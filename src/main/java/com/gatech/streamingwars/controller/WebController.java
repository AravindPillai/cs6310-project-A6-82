package com.gatech.streamingwars.controller;

<<<<<<< Updated upstream
import com.gatech.streamingwars.model.AuditEntity;
import com.gatech.streamingwars.model.main.*;
import com.gatech.streamingwars.repository.*;
=======
import com.gatech.streamingwars.maindb.model.*;
import com.gatech.streamingwars.maindb.repository.*;
import com.gatech.streamingwars.service.MainDBService;
import com.gatech.streamingwars.service.UserService;
>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    AccountRepository repository;

    @Autowired
    DemographicRepository demographicRepository;

    @Autowired
    StudioRepository studioRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
<<<<<<< Updated upstream
=======
    DemographicRepository demographicRepository;

    @Autowired
>>>>>>> Stashed changes
    StreamingServiceRepository streamingServiceRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @RequestMapping("/")
    public  String index(Model model)
    {
      return "index.xhtml";
    }

    @RequestMapping("/login")
    public  String login(Model model)
    {
       return "index.xhtml";
    }

    @RequestMapping("/displayevents")
    public String displayEvents(Model model){
        clearModelAttributes(model);
        List<Event> listOfEvents = this.eventRepository.findAll();
        model.addAttribute("events", listOfEvents);
        return "displayevents.xhtml";
    }

    @RequestMapping("/displayoffers")
    public String displayOffers(Model model){
        clearModelAttributes(model);
        List<Transaction> listOfOffers = getAllOffers();
        model.addAttribute("transactions", listOfOffers);
        return "displayoffers.xhtml";
    }

    @RequestMapping("/offerppv")
    public  String createPpvOffer(Model model)
    {
        clearModelAttributes(model);
        Transaction transaction = new Transaction();
        model.addAttribute("transaction",transaction);
        return "offerppv.xhtml";
    }

    @PostMapping("/offerppv")
    public String createPpvOfferSubmit(@ModelAttribute Transaction transaction, Model model) {

        // Most important thing for this is that we need to be validate that the ppv itself exists before
        // committing the transaction

        Boolean isValid = true;
        String reasonForFailure = "";
        Event event = lookupEventByNameAndYear(transaction.getEventName(), transaction.getEventYear());

        if (event == null){
            isValid = false;
            reasonForFailure += "Event not found";
        } else if (event != null) {
            String eventType = event.getEventType();
            if (!(eventType.equalsIgnoreCase("ppv"))) {
                isValid = false;
                reasonForFailure += "Event type is not ppv";
            }
        }

        Transaction saved = null;
        if (isValid) {
            System.out.println("Event passed validation steps");
            // Get the studio from the event and then commit the transaction
            String studioShortName = event.getStudioShortName();

            transaction.setVendor(studioShortName);
            transaction.setTransactionCost(event.getEventLicensingFee());
            transaction.setEventType("ppv");

            //transaction ppv cost is passed in via the offerppv.xhtml post

            // this is an "offer" type
            transaction.setTransactionType("offer");

            saved = transactionRepository.save(transaction);
        }

        if(saved!=null) {
            model.addAttribute("successmessage", "Event Saved Successfully!");
            return "index.xhtml";
        }
        else {
            model.addAttribute("errormessage", String.format("Offering save Failed for the following reasons: %s, Please try again", reasonForFailure));
            model.addAttribute("transaction",transaction);
            return "offerppv.xhtml";
        }
    }

    @RequestMapping("/watchevent")
    public  String createWatchEvent(Model model)
    {
        clearModelAttributes(model);
        Transaction transaction = new Transaction();
        model.addAttribute("transaction",transaction);
        return "watchevent.xhtml";
    }

    @PostMapping("/watchevent")
    public String createWatchEventSubmit(@ModelAttribute Transaction transaction, Model model) {

        Boolean isValid = true;
        String reasonForFailure = "";

        //validate demo group
        DemographicGroup demographicGroupLookup = null;
        demographicGroupLookup = lookupDemographicGroupByShortName(transaction.getBuyer());
        if (demographicGroupLookup == null){
            isValid = false;
            reasonForFailure += "Demo Group Not not found";
        }

        // validate streaming service
        StreamingService streamingServiceLookup = null;
        streamingServiceLookup = lookupStreamByShortName(transaction.getVendor());
        if (streamingServiceLookup == null){
            isValid = false;
            reasonForFailure += "Streaming Service not found";
        }

        //validate eventname x year
        Event eventLookup = lookupEventByNameAndYear(transaction.getEventName(), transaction.getEventYear());
        if (eventLookup == null){
            isValid = false;
            reasonForFailure += "Event not found";
        }

        // validate that the streaming service is actually offering the event
        Transaction offerLookup = null;
        offerLookup = checkToSeeIfStreamingIsOfferingEvent(transaction.getVendor(), transaction.getEventName(), transaction.getEventYear());
        if (offerLookup == null){
            isValid = false;
            reasonForFailure += "Offering for streaming service and movie not found";
        }

        Transaction saved = null;
        if (isValid) {
            System.out.println("Passed all validation steps, proceeding with logic based on ppv or movie type");
            // Get the studio from the event and then commit the transaction

            String eventType = offerLookup.getEventType();

            if (eventType.equalsIgnoreCase("ppv")){
                System.out.println("proceeding with ppv transaction");

                int sizeOfDemogroup = demographicGroupLookup.getNumberOfAccounts();
                int ppvCost = offerLookup.getPpvCost();
                int totalCostOfTransaction = sizeOfDemogroup * ppvCost * transaction.getPercentage();
                int totalCostOfTransactionDiv100 = totalCostOfTransaction/100;
                transaction.setTransactionCost(totalCostOfTransactionDiv100);

            } else if (eventType.equalsIgnoreCase("movie")){

                //check to see if theres a subscription already
                Transaction existingSubscriptionWithGreatestPercentage = returnSubscriptionBetweenStreamAndDemoWithLargestPercentage(streamingServiceLookup.getShortName(), demographicGroupLookup.getShortName());

                int percentageForTransaction = transaction.getPercentage();

                if (existingSubscriptionWithGreatestPercentage != null){
                    percentageForTransaction -= existingSubscriptionWithGreatestPercentage.getPercentage();
                }

                if (percentageForTransaction < 0){
                    percentageForTransaction = 0;
                }

                int transactionCost = percentageForTransaction * streamingServiceLookup.getSubscriptionPrice() * demographicGroupLookup.getNumberOfAccounts();
                int transactionCostDiv100 = transactionCost/100;
                transaction.setTransactionCost(transactionCostDiv100);
            }

            // this is an "offer" type
            transaction.setTransactionType("watch");

            // set the transactionEventType
            transaction.setEventType(eventType);
            saved = transactionRepository.save(transaction);

//            // this is an "offer" type
//            transaction.setTransactionType("watch");
//            saved = transactionRepository.save(transaction);
        }

        if(saved!=null) {
            model.addAttribute("successmessage", "Watch Event Saved Successfully!");
            return "index.xhtml";
        }
        else {
            model.addAttribute("errormessage", String.format("Watch Event Creation Failed for the following reasons: %s, Please try again", reasonForFailure));
            model.addAttribute("transaction",transaction);
            return "watchevent.xhtml";
        }
    }

<<<<<<< Updated upstream
    @RequestMapping("/offermovie")
    public  String createMovieOffer(Model model)
    {
=======
    @RequestMapping("/displayevents")
    public String displayEvents(Model model){
        clearModelAttributes(model);
        List<Event> listOfEvents = this.eventRepository.findAll();
        model.addAttribute("events", listOfEvents);
        return "displayevents.xhtml";
    }

    @RequestMapping("/displayoffers")
    public String displayOffers(Model model){
        clearModelAttributes(model);
        List<Transaction> listOfOffers = getAllOffers();
        model.addAttribute("transactions", listOfOffers);
        return "displayoffers.xhtml";
    }

    @RequestMapping("/offerppv")
    public  String createPpvOffer(Model model)
    {
        clearModelAttributes(model);
        Transaction transaction = new Transaction();
        model.addAttribute("transaction",transaction);
        return "offerppv.xhtml";
    }

    @PostMapping("/offerppv")
    public String createPpvOfferSubmit(@ModelAttribute Transaction transaction, Model model) {

        // Most important thing for this is that we need to be validate that the ppv itself exists before
        // committing the transaction

        Boolean isValid = true;
        String reasonForFailure = "";
        Event event = lookupEventByNameAndYear(transaction.getEventName(), transaction.getEventYear());

        if (event == null){
            isValid = false;
            reasonForFailure += "Event not found";
        } else if (event != null) {
            String eventType = event.getEventType();
            if (!(eventType.equalsIgnoreCase("ppv"))) {
                isValid = false;
                reasonForFailure += "Event type is not ppv";
            }
        }

        Transaction saved = null;
        if (isValid) {
            System.out.println("Event passed validation steps");
            // Get the studio from the event and then commit the transaction
            String studioShortName = event.getStudioShortName();

            transaction.setVendor(studioShortName);
            transaction.setTransactionCost(event.getEventLicensingFee());
            transaction.setEventType("ppv");

            //transaction ppv cost is passed in via the offerppv.xhtml post

            // this is an "offer" type
            transaction.setTransactionType("offer");

            saved = transactionRepository.save(transaction);
        }

        if(saved!=null) {
            model.addAttribute("successmessage", "Event Saved Successfully!");
            return "index.xhtml";
        }
        else {
            model.addAttribute("errormessage", String.format("Offering save Failed for the following reasons: %s, Please try again", reasonForFailure));
            model.addAttribute("transaction",transaction);
            return "offerppv.xhtml";
        }
    }

    @RequestMapping("/watchevent")
    public  String createWatchEvent(Model model)
    {
        clearModelAttributes(model);
        Transaction transaction = new Transaction();
        model.addAttribute("transaction",transaction);
        return "watchevent.xhtml";
    }

    @PostMapping("/watchevent")
    public String createWatchEventSubmit(@ModelAttribute Transaction transaction, Model model) {

        Boolean isValid = true;
        String reasonForFailure = "";

        //validate demo group
        DemographicGroup demographicGroupLookup = null;
        demographicGroupLookup = lookupDemographicGroupByShortName(transaction.getBuyer());
        if (demographicGroupLookup == null){
            isValid = false;
            reasonForFailure += "Demo Group Not not found";
        }

        // validate streaming service
        StreamingService streamingServiceLookup = null;
        streamingServiceLookup = lookupStreamByShortName(transaction.getVendor());
        if (streamingServiceLookup == null){
            isValid = false;
            reasonForFailure += "Streaming Service not found";
        }

        //validate eventname x year
        Event eventLookup = lookupEventByNameAndYear(transaction.getEventName(), transaction.getEventYear());
        if (eventLookup == null){
            isValid = false;
            reasonForFailure += "Event not found";
        }

        // validate that the streaming service is actually offering the event
        Transaction offerLookup = null;
        offerLookup = checkToSeeIfStreamingIsOfferingEvent(transaction.getVendor(), transaction.getEventName(), transaction.getEventYear());
        if (offerLookup == null){
            isValid = false;
            reasonForFailure += "Offering for streaming service and movie not found";
        }

        Transaction saved = null;
        if (isValid) {
            System.out.println("Passed all validation steps, proceeding with logic based on ppv or movie type");
            // Get the studio from the event and then commit the transaction

            String eventType = offerLookup.getEventType();

            if (eventType.equalsIgnoreCase("ppv")){
                System.out.println("proceeding with ppv transaction");

                int sizeOfDemogroup = demographicGroupLookup.getNumberOfAccounts();
                int ppvCost = offerLookup.getPpvCost();
                int totalCostOfTransaction = sizeOfDemogroup * ppvCost * transaction.getPercentage();
                int totalCostOfTransactionDiv100 = totalCostOfTransaction/100;
                transaction.setTransactionCost(totalCostOfTransactionDiv100);

            } else if (eventType.equalsIgnoreCase("movie")){

                //check to see if theres a subscription already
                Transaction existingSubscriptionWithGreatestPercentage = returnSubscriptionBetweenStreamAndDemoWithLargestPercentage(streamingServiceLookup.getShortName(), demographicGroupLookup.getShortName());

                int percentageForTransaction = transaction.getPercentage();

                if (existingSubscriptionWithGreatestPercentage != null){
                    percentageForTransaction -= existingSubscriptionWithGreatestPercentage.getPercentage();
                }

                if (percentageForTransaction < 0){
                    percentageForTransaction = 0;
                }

                int transactionCost = percentageForTransaction * streamingServiceLookup.getSubscriptionPrice() * demographicGroupLookup.getNumberOfAccounts();
                int transactionCostDiv100 = transactionCost/100;
                transaction.setTransactionCost(transactionCostDiv100);
            }

            // this is an "offer" type
            transaction.setTransactionType("watch");

            // set the transactionEventType
            transaction.setEventType(eventType);
            saved = transactionRepository.save(transaction);

//            // this is an "offer" type
//            transaction.setTransactionType("watch");
//            saved = transactionRepository.save(transaction);
        }

        if(saved!=null) {
            model.addAttribute("successmessage", "Watch Event Saved Successfully!");
            return "index.xhtml";
        }
        else {
            model.addAttribute("errormessage", String.format("Watch Event Creation Failed for the following reasons: %s, Please try again", reasonForFailure));
            model.addAttribute("transaction",transaction);
            return "watchevent.xhtml";
        }
    }

    @RequestMapping("/offermovie")
    public  String createMovieOffer(Model model)
    {
        clearModelAttributes(model);
        Transaction transaction = new Transaction();
        model.addAttribute("transaction",transaction);
        return "offermovie.xhtml";
    }

    @PostMapping("/offermovie")
    public String createMovieOfferSubmit(@ModelAttribute Transaction transaction, Model model) {

        // Most important thing for this is that we need to be validate that the movie itself exists before
        // committing the transaction

        Boolean isValid = true;
        String reasonForFailure = "";
        Event event = lookupEventByNameAndYear(transaction.getEventName(), transaction.getEventYear());

        if (event == null){
            isValid = false;
            reasonForFailure += "Event not found";
        } else if (event != null) {
            String eventType = event.getEventType();
            if (!(eventType.equalsIgnoreCase("movie"))) {
                isValid = false;
                reasonForFailure += "Event type is not movie";
            }
        }

        Transaction saved = null;
        if (isValid) {
            System.out.println("Event passed validation steps");
            // Get the studio from the event and then commit the transaction
            String studioShortName = event.getStudioShortName();

            transaction.setVendor(studioShortName);
            transaction.setTransactionCost(event.getEventLicensingFee());
            transaction.setEventType("movie");

            // this is an "offer" type
            transaction.setTransactionType("offer");

            saved = transactionRepository.save(transaction);
        }

        if(saved!=null) {
            model.addAttribute("successmessage", "Event Saved Successfully!");
            return "index.xhtml";
        }
        else {
            model.addAttribute("errormessage", String.format("Offering save Failed for the following reasons: %s, Please try again", reasonForFailure));
            model.addAttribute("transaction",transaction);
            return "offermovie.xhtml";
        }
    }

    @RequestMapping("/registration")
    public  String registration(Model model) {
>>>>>>> Stashed changes
        clearModelAttributes(model);
        Transaction transaction = new Transaction();
        model.addAttribute("transaction",transaction);
        return "offermovie.xhtml";
    }

    @PostMapping("/offermovie")
    public String createMovieOfferSubmit(@ModelAttribute Transaction transaction, Model model) {

        // Most important thing for this is that we need to be validate that the movie itself exists before
        // committing the transaction

        Boolean isValid = true;
        String reasonForFailure = "";
        Event event = lookupEventByNameAndYear(transaction.getEventName(), transaction.getEventYear());

        if (event == null){
            isValid = false;
            reasonForFailure += "Event not found";
        } else if (event != null) {
            String eventType = event.getEventType();
            if (!(eventType.equalsIgnoreCase("movie"))) {
                isValid = false;
                reasonForFailure += "Event type is not movie";
            }
        }

        Transaction saved = null;
        if (isValid) {
            System.out.println("Event passed validation steps");
            // Get the studio from the event and then commit the transaction
            String studioShortName = event.getStudioShortName();

            transaction.setVendor(studioShortName);
            transaction.setTransactionCost(event.getEventLicensingFee());
            transaction.setEventType("movie");

            // this is an "offer" type
            transaction.setTransactionType("offer");

            saved = transactionRepository.save(transaction);
        }

        if(saved!=null) {
            model.addAttribute("successmessage", "Event Saved Successfully!");
            return "index.xhtml";
        }
        else {
            model.addAttribute("errormessage", String.format("Offering save Failed for the following reasons: %s, Please try again", reasonForFailure));
            model.addAttribute("transaction",transaction);
            return "offermovie.xhtml";
        }
    }

    @RequestMapping("/createevent")
    public  String createEvent(Model model)
    {
        clearModelAttributes(model);
        Event event = new Event();
        model.addAttribute("event",event);
        return "createevent.xhtml";
    }

    @PostMapping("/createevent")
    public String createEventSubmit(@ModelAttribute Event event, Model model) {

        // The most important thing about this is that, the Event must have a valid studio,
        //
        // Additionally, we'll need to create other validations around
        // the fields we enter-

        Boolean isValid = true;
        String reasonForFailure = "";
        Studio studioLookup = lookupStudioByShortName(event.getStudioShortName());
        if (studioLookup == null){
            isValid = false;
            reasonForFailure += "Studio not found";
        }

        String eventType = event.getEventType();
        if (!(eventType.equalsIgnoreCase("movie")||eventType.equalsIgnoreCase("ppv"))){
            isValid = false;
            reasonForFailure += "Event type is not movie or ppv";
        }

        Event saved = null;
        if (isValid) {
            System.out.println("passed validation steps");
            saved = eventRepository.save(event);
        }

        if(saved!=null) {
            model.addAttribute("successmessage", "Event Saved Successfully!");
            return "index.xhtml";
        }
        else {
            model.addAttribute("errormessage", String.format("Event save Failed for the following reasons: %s, Please try again", reasonForFailure));
            model.addAttribute("event",event);
            return "createevent.xhtml";
        }
    }

    @RequestMapping("/createdemo")
    public  String createDemo(Model model)
    {
        clearModelAttributes(model);
        DemographicGroup dGroup = new DemographicGroup();
        model.addAttribute("group",dGroup);
        return "createdemo.xhtml";
    }

    @PostMapping("/createdemo")
    public String createDemoSubmit(@ModelAttribute DemographicGroup group, Model model) {
        DemographicGroup saved = demographicRepository.save(group);
        if(saved!=null) {
            model.addAttribute("successmessage", "Demographic Group Saved Successfully!");
            return "index.xhtml";
        }
        else {
            model.addAttribute("errormessage", "Demographic Group Failed, Please try again");
            model.addAttribute("group",group);
            return "createdemo.xhtml";
        }
    }

<<<<<<< Updated upstream


=======
>>>>>>> Stashed changes
    @RequestMapping("/createstream")
    public  String createStreamingService(Model model)
    {
        clearModelAttributes(model);
        StreamingService streamingService = new StreamingService();
        model.addAttribute("streamingService",streamingService);
        return "createstream.xhtml";
    }

    @PostMapping("/createstream")
    public String createStreamingServiceSubmit(@ModelAttribute StreamingService streamingService, Model model) {

        StreamingService saved = streamingServiceRepository.save(streamingService);
        if(saved!=null) {
            model.addAttribute("successmessage", "Streaming Service Saved Successfully!");
            return "index.xhtml";
        }
        else {
            model.addAttribute("errormessage", "Streaming Service Saving Failed, Please try again");
            model.addAttribute("streamingService",streamingService);
            return "createstream.xhtml";
        }
    }

    @RequestMapping("/createstudio")
    public  String createStudio(Model model)
    {
        clearModelAttributes(model);
        Studio studio = new Studio();
        model.addAttribute("studio",studio);
        return "createstudio.xhtml";
    }

    @PostMapping("/createstudio")
    public String studioSubmit(@ModelAttribute Studio studio, Model model) {

        Studio saved = studioRepository.save(studio);
        if(saved!=null) {
            model.addAttribute("successmessage", "Studio Saved Successfully!");
            return "index.xhtml";
        }
        else {
            model.addAttribute("errormessage", "Studio Saving Failed, Please try again");
            model.addAttribute("studio",studio);
            return "createstudio.xhtml";
        }
    }

    @RequestMapping("/displaystudio")
    public  String displayStudio(Model model)
    {
        clearModelAttributes(model);
        Studio studio = new Studio();
        model.addAttribute("studio", studio);

        TransactionSummary transactionSummary = new TransactionSummary();
        model.addAttribute("transactionSummary", transactionSummary);

        return "displaystudio.xhtml";
    }

    @PostMapping("/displaystudio")
    public  String displayStudio(@ModelAttribute Studio studio, @ModelAttribute TransactionSummary transactionSummary, Model model)
    {
        // Lookup studio
        Studio studioLookup = lookupStudioByShortName(studio.getShortName());
        if(studioLookup != null){
            model.addAttribute("studio", studioLookup);

            // since the studio was found, do a lookup on the transactionSummaryDetails
            TransactionSummary transactionSummaryCalculated = calculateTransactionSummaryForStudio(studioLookup);
            model.addAttribute("transactionSummary", transactionSummaryCalculated);

            return "displaystudio.xhtml";
        } else {
            studio = new Studio();
            model.addAttribute("studio", studio);
            model.addAttribute("transactionSummary", transactionSummary);
            model.addAttribute("errormessage", "Studio lookup Failed, Please try again");
            System.out.println("Couldn't find the studio");
            return "displaystudio.xhtml";
        }
    }

    @RequestMapping("/displaystream")
    public  String displayStream(Model model)
    {
        clearModelAttributes(model);
        StreamingService stream = new StreamingService();
        model.addAttribute("stream", stream);
        return "displaystream.xhtml";
    }

    @PostMapping("/displaystream")
    public  String displayStream(@ModelAttribute StreamingService stream, Model model)
    {
        // Lookup Stream
        StreamingService streamLookup = lookupStreamByShortName(stream.getShortName());
        if(streamLookup != null){
            model.addAttribute("stream", streamLookup);
            return "displaystream.xhtml";
        } else {
            stream = new StreamingService();
            model.addAttribute("stream", stream);
            model.addAttribute("errormessage", "Streaming Service lookup Failed, Please try again");
            System.out.println("Couldn't find the stream");
            return "displaystream.xhtml";
        }
    }

    @RequestMapping("/displaydemo")
    public String displayDemo(Model model)
    {
        clearModelAttributes(model);
        DemographicGroup demo = new DemographicGroup();
        model.addAttribute("demo",demo);
        return "displaydemo.xhtml";
    }

    @PostMapping("/displaydemo")
    public String displayDemo(@ModelAttribute DemographicGroup group, Model model) {

        //lookup demogroup
        DemographicGroup demographicGroupLookup = null;

        demographicGroupLookup = lookupDemographicGroupByShortName(group.getShortName());

        if(demographicGroupLookup != null) {
            clearModelAttributes(model);
            model.addAttribute("demo", demographicGroupLookup);
            model.addAttribute("successmessage", "Demogroup lookup succeeded");
            System.out.println("Successfully found demogroup");
            return "displaydemo.xhtml";
        } else {
            clearModelAttributes(model);
            DemographicGroup newDemoGroup = new DemographicGroup();
            model.addAttribute("demo", newDemoGroup);
            model.addAttribute("errormessage", "Demogroup lookup Failed, Please try again");
            System.out.println("Couldn't find the demogroup");
            return "displaydemo.xhtml";
        }
    }

    @RequestMapping("/updatedemo")
    public String updateDemo(Model model)
    {
        clearModelAttributes(model);
        DemographicGroup demo = new DemographicGroup();
        model.addAttribute("demo",demo);
        return "updatedemo.xhtml";
    }

    @PostMapping("/updatedemo")
    public String updateDemo(@ModelAttribute DemographicGroup group, Model model) {

        //lookup demogroup
        DemographicGroup demographicGroupLookup = null;
        demographicGroupLookup = lookupDemographicGroupByShortName(group.getShortName());

        if(demographicGroupLookup != null) {
            clearModelAttributes(model);

            // ONLY IF we've found the demoGroup (which we have since we're in this if block) update the number of accounts on the demogroup
            demographicGroupLookup.setNumberOfAccounts(group.getNumberOfAccounts());
            demographicRepository.save(demographicGroupLookup);

            model.addAttribute("demo", demographicGroupLookup);
            model.addAttribute("successmessage", "Demogroup number of accounts update succeeded");
            System.out.println("Successfully updated the number of accounts");
            return "updatedemo.xhtml";
        } else {
            clearModelAttributes(model);
            DemographicGroup newDemoGroup = new DemographicGroup();
            model.addAttribute("demo", newDemoGroup);
            model.addAttribute("errormessage", "Demogroup lookup Failed, Please try again");
            System.out.println("Couldn't find the demogroup to update the number of accounts");
            return "updatedemo.xhtml";
        }
    }

    private DemographicGroup lookupDemographicGroupByShortName(String demographicGroupShortName){
        // looks up demographic group via the short name
        List<DemographicGroup> demographicGroupList = this.demographicRepository.findAll();
        DemographicGroup demographicGroupToSearchFor = null;
        for (DemographicGroup demographicGroup: demographicGroupList){
            if (demographicGroup.getShortName().equalsIgnoreCase(demographicGroupShortName)){
                demographicGroupToSearchFor = demographicGroup;
                return demographicGroupToSearchFor;
            }
        }
        return null;
    }

    private Studio lookupStudioByShortName(String studioShortName){
        List<Studio> studioList = this.studioRepository.findAll();
        Studio studioSearchResult = null;
        for (Studio studio: studioList){
            if (studio.getShortName().equalsIgnoreCase(studioShortName)){
                studioSearchResult = studio;
                return studioSearchResult;
            }
        }
        return null;
    }

    private StreamingService lookupStreamByShortName(String streamShortName){
        List<StreamingService> streamList = this.streamingServiceRepository.findAll();
        StreamingService streamSearchResult = null;
        for (StreamingService stream: streamList){
            if (stream.getShortName().equalsIgnoreCase(streamShortName)){
                streamSearchResult = stream;
                return streamSearchResult;
            }
        }
        return null;
    }

<<<<<<< Updated upstream
=======
    @RequestMapping("/updatedemo")
    public String updateDemo(Model model)
    {
        clearModelAttributes(model);
        DemographicGroup demo = new DemographicGroup();
        model.addAttribute("demo",demo);
        return "updatedemo.xhtml";
    }

    @PostMapping("/updatedemo")
    public String updateDemo(@ModelAttribute DemographicGroup group, Model model) {

        //lookup demogroup
        DemographicGroup demographicGroupLookup = null;
        demographicGroupLookup = lookupDemographicGroupByShortName(group.getShortName());

        if(demographicGroupLookup != null) {
            clearModelAttributes(model);

            // ONLY IF we've found the demoGroup (which we have since we're in this if block) update the number of accounts on the demogroup
            demographicGroupLookup.setNumberOfAccounts(group.getNumberOfAccounts());
            demographicRepository.save(demographicGroupLookup);

            model.addAttribute("demo", demographicGroupLookup);
            model.addAttribute("successmessage", "Demogroup number of accounts update succeeded");
            System.out.println("Successfully updated the number of accounts");
            return "updatedemo.xhtml";
        } else {
            clearModelAttributes(model);
            DemographicGroup newDemoGroup = new DemographicGroup();
            model.addAttribute("demo", newDemoGroup);
            model.addAttribute("errormessage", "Demogroup lookup Failed, Please try again");
            System.out.println("Couldn't find the demogroup to update the number of accounts");
            return "updatedemo.xhtml";
        }
    }

>>>>>>> Stashed changes
    private Event lookupEventByNameAndYear(String eventName, int eventYear){
        List<Event> eventList = this.eventRepository.findAll();
        Event eventSearchResult = null;
        for (Event event: eventList){
            if (event.getName().equalsIgnoreCase(eventName) && event.getYear() == eventYear){
                eventSearchResult = event;
                return eventSearchResult;
            }
        }
        return null;
    }

    private List<Transaction> getSalesTransactions(Vendor vendor){
        String vendorShortName = vendor.getShortName();

        List<Transaction> vendorSalesTransactions = new ArrayList<>();

        List<Transaction> allTransactions = this.transactionRepository.findAll();

        // Looks through all of the transactions where the vendor matches the "vendor" column
        for (Transaction transaction: allTransactions){
            if (transaction.getVendor().equalsIgnoreCase(vendorShortName)){
                vendorSalesTransactions.add(transaction);
            }
        }

        // returns the list of all Vendor Transactions
        return vendorSalesTransactions;
    }

    private TransactionSummary calculateTransactionSummaryForStudio(Studio studio){
        // Display for a studio shows
        // Licensing fees for the current month
        // Licensing fees for the previous motnh
        // Licensing fees for all previous months except the current month

        // first, get all the transactions where the studio was the vendor
        List<Transaction> allTransactions = getSalesTransactions(studio);

        // will need to change this to look at some global date, for now set current month == 11
        int current_month = 11;
        int current_year = 2020;

        // now, calculate the current month
        int currentMonthTotal = 0;
        for (Transaction transaction: allTransactions){
            if (transaction.getCreatedAt().getMonthValue() == current_month){
                currentMonthTotal += transaction.getTransactionCost();
            }
        }

        // calculate the previous month
        int previousMonthTotal = 0;
        for (Transaction transaction: allTransactions){
            if (transaction.getCreatedAt().getMonthValue() == current_month-1){
                previousMonthTotal += transaction.getTransactionCost();
            }
        }

        // calculate all upto except current month
        int cumulativeExceptCurrentTotal = 0;
        for (Transaction transaction: allTransactions){
            if (transaction.getCreatedAt().getMonthValue() <= current_month-1){
                cumulativeExceptCurrentTotal += transaction.getTransactionCost();
            }
        }

        TransactionSummary transactionSummary = new TransactionSummary();
        transactionSummary.setCurrentPeriod(currentMonthTotal);
        transactionSummary.setPreviousPeriod(previousMonthTotal);
        transactionSummary.setTotal(cumulativeExceptCurrentTotal);

        return transactionSummary;
    }

    private List<Transaction> getAllOffers(){
        List<Transaction> listOfAllTransactions = this.transactionRepository.findAll();

        List<Transaction> listOfOffers = new ArrayList<>();

        for (Transaction transaction: listOfAllTransactions){
            if (transaction.getTransactionType().equalsIgnoreCase("offer")){
                listOfOffers.add(transaction);
            }
        }

        return listOfOffers;
    }

    private Transaction checkToSeeIfStreamingIsOfferingEvent(String streamingServiceShortName, String eventName, int eventYear){
        List<Transaction> allOfferings = getAllOffers();

        Transaction foundOffering = null;
        for (Transaction transaction: allOfferings){
            if (transaction.getBuyer().equalsIgnoreCase(streamingServiceShortName) && transaction.getEventName().equalsIgnoreCase(eventName)
                    && transaction.getEventYear() == eventYear){
                foundOffering = transaction;
                return foundOffering;
            }
        }
        return null;
    }

    private Transaction returnSubscriptionBetweenStreamAndDemoWithLargestPercentage(String streamingServiceShortName, String demoGroupShortName){

        List<Transaction> allTransactions = transactionRepository.findAll();

        List<Transaction> transactionsIndicatingSubscription = new ArrayList<>();

        for (Transaction transaction: allTransactions){
            if (transaction.getBuyer().equalsIgnoreCase(demoGroupShortName)
                    && transaction.getVendor().equalsIgnoreCase(streamingServiceShortName)
<<<<<<< Updated upstream
                        && transaction.getEventType().equalsIgnoreCase("movie")
                            && transaction.getTransactionType().equalsIgnoreCase("watch")){
=======
                    && transaction.getEventType().equalsIgnoreCase("movie")
                    && transaction.getTransactionType().equalsIgnoreCase("watch")){
>>>>>>> Stashed changes
                transactionsIndicatingSubscription.add(transaction);
            }
        }

        // for all the transactions, return the one with the largest percentage
        Transaction transactionWithLargestPercentage = null;
        if (transactionsIndicatingSubscription != null && transactionsIndicatingSubscription.size() > 0) {
            for (Transaction transaction : transactionsIndicatingSubscription) {
                if (transactionWithLargestPercentage == null || transactionWithLargestPercentage.getPercentage() < transaction.getPercentage()) {
                    transactionWithLargestPercentage = transaction;
                }
            }
        }

        return transactionWithLargestPercentage;
    }

    private void clearModelAttributes(Model model)
    {
        model.addAttribute("successmessage",null);
        model.addAttribute("errormessage",null);
    }
}
