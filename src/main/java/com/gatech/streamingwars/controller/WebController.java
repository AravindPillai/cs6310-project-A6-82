package com.gatech.streamingwars.controller;

import com.gatech.streamingwars.maindb.model.*;
import com.gatech.streamingwars.maindb.repository.*;
import com.gatech.streamingwars.service.MainDBService;
import com.gatech.streamingwars.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Controller
public class WebController {

    @Autowired
    AccountRepository repository;

    @Autowired
    MainDBService mainDBService;

    @Autowired
    UserService userService;

    @Autowired
    StudioRepository studioRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    StreamingServiceRepository streamingServiceRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @RequestMapping("/")
    public  String none(Model model)
    {
      return "login.xhtml";
    }

    @RequestMapping("/index")
    public  String index(Model model) { return "index.xhtml";}

    @RequestMapping("/login")
    public  String login(Model model,@RequestParam(required = false) Boolean error) {
        if(error!=null && error)
        {
            model.addAttribute("errormessage", "Login Failed. Check the Credentials and Try Again.");
        }
        return "login.xhtml";
    }

    @RequestMapping("/registration")
    public  String registration(Model model) {
        clearModelAttributes(model);
        User user = new User();
        model.addAttribute(user);
        return "registration.xhtml";
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

    @PostMapping("/registration")
    public String registration(@ModelAttribute User user, Model model)
    {
        User userByUserName = userService.findUserByUserName(user.getName());
        if(userByUserName!=null)
        {
            model.addAttribute("errormessage", "User Exists");
            model.addAttribute("user",userByUserName);
            return "registration.xhtml";
        }
        else {
            User saveUser = userService.saveUser(user);
            model.addAttribute("successmessage", "User Created Successfully!");
            return "login.xhtml";
        }
    }

    @RequestMapping("/createdemo")
    public  String createDemo(Model model)
    {
        clearModelAttributes(model);
        if(!userService.isAuthenticated())
        {
            return "/";
        }
        DemographicGroup dGroup = new DemographicGroup();
        model.addAttribute("group",dGroup);
        return "createdemo.xhtml";
    }

    @PostMapping("/createdemo")
    public String creatingDemo(@ModelAttribute DemographicGroup group, Model model) {
        List<DemographicGroup> saved = null;
        try {
             group.setArchived(false);
             //group.setCreatedAt(getCreateDate(group.getCurrentMonthYear()));
             List<DemographicGroup> groups = new ArrayList<DemographicGroup>();
             groups.add(group);
             saved =  mainDBService.saveDemographicGroup(groups);
        }catch (SQLIntegrityConstraintViolationException|DataIntegrityViolationException exception)
        {
            exception.printStackTrace();
            System.out.println("Exception Occured during Saving "+exception.getMessage());
            model.addAttribute("errormessage", "Demographic Group Creation Failed,Entry With Same Name Possible Exist.Please Verify and try again");
            model.addAttribute("group",group);
            return "createdemo.xhtml";
        }
        if(saved!=null && saved.size()>0) {
            model.addAttribute("successmessage", "Demographic Group Saved Successfully!");
            return "index.xhtml";
        }
        else {
            model.addAttribute("errormessage", "Demographic Group Failed, Please try again");
            model.addAttribute("group",group);
            return "createdemo.xhtml";
        }
    }

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
            TransactionSummary transactionSummaryCalculated = calculateTransactionSummaryForStudio(studioLookup, studio.getCurrentMonthYear());
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

        TransactionSummary transactionSummary = new TransactionSummary();
        model.addAttribute("transactionSummary", transactionSummary);
        return "displaydemo.xhtml";
    }

    @PostMapping("/displaydemo")
    public String displayDemo(@ModelAttribute DemographicGroup group, @ModelAttribute TransactionSummary transactionSummary, Model model) {

        //lookup demogroup
        DemographicGroup demographicGroupLookup = null;

        demographicGroupLookup = lookupDemographicGroupByShortName(group.getShortName());

        if(demographicGroupLookup != null) {
            clearModelAttributes(model);
            model.addAttribute("demo", demographicGroupLookup);
            model.addAttribute("successmessage", "Demogroup lookup succeeded");
            System.out.println("Successfully found demogroup");

            // since the studio was found, do a lookup on the transactionSummaryDetails
            TransactionSummary transactionSummaryCalculated = calculateTransactionSummaryForDemo(demographicGroupLookup, group.getCurrentMonthYear());
            model.addAttribute("transactionSummary", transactionSummaryCalculated);

            return "displaydemo.xhtml";
        } else {
            clearModelAttributes(model);
            DemographicGroup newDemoGroup = new DemographicGroup();
            model.addAttribute("demo", newDemoGroup);
            model.addAttribute("errormessage", "Demogroup lookup Failed, Please try again");
            model.addAttribute("transactionSummary", transactionSummary);
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
            mainDBService.getDemographicRepository().save(demographicGroupLookup);

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

    @RequestMapping("/updatestream")
    public String updateStream(Model model)
    {
        clearModelAttributes(model);
        StreamingService streamingService = new StreamingService();
        model.addAttribute("streamingservice",streamingService);
        return "updatestream.xhtml";
    }

    @PostMapping("/updatestream")
    public String updateEvent(@ModelAttribute StreamingService streamingService, Model model) {


        // Most important thing for this is that we need to be validate that the movie itself exists before
        // committing the transaction

        Boolean isValid = true;
        String reasonForFailure = "";

        StreamingService streamLookup = null;
        streamLookup = lookupStreamByShortName(streamingService.getShortName());

        if (streamLookup == null){
            isValid = false;
            reasonForFailure += "Streaming Service not found";
        } else if (streamLookup != null) {

            String currentYearMonth = streamLookup.getCurrentMonthYear();
            // if the event has already been viewed in that month, do not proceed with the transaction
            Transaction subscribedToThatMonth = checkToSeeIfStreamHasBeenWatchedInTheGivenMonth(streamingService.getShortName(), streamLookup.getCurrentMonthYear());

            if (subscribedToThatMonth != null) {
                isValid = false;
                reasonForFailure += "Stream has already been subscribed to in the given month";
            }
        }

        StreamingService saved = null;
        if (isValid) {
            System.out.println("Streaming Service passed validation steps");
            // Get the studio from the event and then commit the transaction
            clearModelAttributes(model);

            streamLookup.setSubscriptionPrice(streamingService.getSubscriptionPrice());

            saved = streamingServiceRepository.save(streamLookup);
        }

        if(saved!=null) {
            model.addAttribute("streamingservice", streamLookup);
            model.addAttribute("successmessage", "Event Saved Successfully!");
            return "index.xhtml";
        }
        else {
            model.addAttribute("errormessage", String.format("Streaming Service Update Failed for the following reasons: %s, Please try again", reasonForFailure));
            model.addAttribute("streamingservice",streamingService);
            return "updatestream.xhtml";
        }
    }

    @RequestMapping("/updateevent")
    public String updateEvent(Model model)
    {
        clearModelAttributes(model);
        Event event = new Event();
        model.addAttribute("event",event);
        return "updateevent.xhtml";
    }

    @PostMapping("/updateevent")
    public String updateEvent(@ModelAttribute Event event, Model model) {


        // Most important thing for this is that we need to be validate that the movie itself exists before
        // committing the transaction

        Boolean isValid = true;
        String reasonForFailure = "";

        Event eventLookup = null;
        eventLookup = lookupEventByNameAndYear(event.getName(), event.getYear());

        if (eventLookup == null){
            isValid = false;
            reasonForFailure += "Event not found";
        } else if (event != null) {


            String currentYearMonth = eventLookup.getCurrentMonthYear();
            // if the event has already been viewed in that month, do not proceed with the transaction
            Transaction watchedInThatMonth = checkToSeeIfEventHasBeenWatchedInTheGivenMonth(event.getName(), event.getYear(), eventLookup.getCurrentMonthYear());

            if (watchedInThatMonth != null) {
                isValid = false;
                reasonForFailure += "Event has already been watched in the given month";
            }
        }

        Event saved = null;
        if (isValid) {
            System.out.println("Event and dateCheck passed validation steps");
            // Get the studio from the event and then commit the transaction
            clearModelAttributes(model);

            eventLookup.setDuration(event.getDuration());
            eventLookup.setEventLicensingFee(event.getEventLicensingFee());

            saved = eventRepository.save(eventLookup);
        }

        if(saved!=null) {
            model.addAttribute("event", eventLookup);
            model.addAttribute("successmessage", "Event Saved Successfully!");
            return "index.xhtml";
        }
        else {
            model.addAttribute("errormessage", String.format("Event save Failed for the following reasons: %s, Please try again", reasonForFailure));
            model.addAttribute("event",event);
            return "updateevent.xhtml";
        }
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

    @RequestMapping("/retractmovie")
    public  String retractMovieOffer(Model model)
    {
        clearModelAttributes(model);
        Transaction transaction = new Transaction();
        model.addAttribute("transaction",transaction);
        return "retractmovie.xhtml";
    }

    @PostMapping("/retractmovie")
    public String retractMovieOfferSubmit(@ModelAttribute Transaction transaction, Model model) {


        Boolean isValid = true;
        String reasonForFailure = "";

        // Most important thing for this is that we need to be validate that the movie itself is being offered before
        // committing the transaction retraction

        // validate that the streaming service is actually offering the event
        Transaction offerLookup = null;
        offerLookup = checkToSeeIfStreamingIsOfferingEvent(transaction.getBuyer(), transaction.getEventName(), transaction.getEventYear());
        if (offerLookup == null){
            isValid = false;
            reasonForFailure += "Offering for streaming service and movie not found";
        }

        Transaction saved = null;
        if (isValid) {
            System.out.println("Found Valid Offering passed validation steps");
            // Get the studio from the event and then commit the transaction

            offerLookup.setEventType(offerLookup.getEventType() + "- retracted");

            saved = transactionRepository.save(offerLookup);
        }

        if(saved!=null) {
            model.addAttribute("successmessage", "Movie retracted Successfully!");
            return "index.xhtml";
        }
        else {
            model.addAttribute("errormessage", String.format("Retraction Failed for the following reasons: %s, Please try again", reasonForFailure));
            model.addAttribute("transaction",transaction);
            return "retractmovie.xhtml";
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

    private DemographicGroup lookupDemographicGroupByShortName(String demographicGroupShortName){
        // looks up demographic group via the short name
        List<DemographicGroup> demographicGroupList = mainDBService.findAllDemographicGroup();
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

    private List<Transaction> getPurchaseTransactions(String buyerName){

        List<Transaction> buyerPurchaseTransactions = new ArrayList<>();

        List<Transaction> allTransactions = this.transactionRepository.findAll();

        // Looks through all of the transactions where the vendor matches the "vendor" column
        for (Transaction transaction: allTransactions){
            if (transaction.getBuyer().equalsIgnoreCase(buyerName)){
                buyerPurchaseTransactions.add(transaction);
            }
        }

        // returns the list of all Buyer Transactions
        return buyerPurchaseTransactions;
    }

    private TransactionSummary calculateTransactionSummaryForStudio(Studio studio, String currentMonthYear){
        // Display for a studio shows
        // Licensing fees for the current month
        // Licensing fees for the previous motnh
        // Licensing fees for all previous months except the current month

        // first, get all the transactions where the studio was the vendor
        List<Transaction> allTransactions = getSalesTransactions(studio);

        // will need to change this to look at some global date, for now set current month == 11
//        int current_month = 11;
//        int current_year = 2020;

        // convert currentMonthYear to LocalDate
        String[] monthYear = currentMonthYear.split("-");
        int month = Integer.parseInt(monthYear[0]); // subtract 1 because the months index starts at 0
        int year = Integer.parseInt(monthYear[1]);
        LocalDate baseDate = LocalDate.of(year, month, 01);

        // now, calculate the current month
        int currentMonthTotal = 0;
        for (Transaction transaction: allTransactions){
            if (baseDate.compareTo(LocalDate.from(transaction.getCreatedAt())) == 0){
                currentMonthTotal += transaction.getTransactionCost();
            }
        }

        // calculate the previous month
        int previousMonthTotal = 0;
        for (Transaction transaction: allTransactions){
            if(baseDate.minusMonths(1).compareTo(LocalDate.from(transaction.getCreatedAt())) == 0){
                previousMonthTotal += transaction.getTransactionCost();
            }
        }

        // calculate all upto except current month
        int cumulativeExceptCurrentTotal = 0;
        for (Transaction transaction: allTransactions){
            if (baseDate.compareTo(LocalDate.from(transaction.getCreatedAt())) > 0){
                cumulativeExceptCurrentTotal += transaction.getTransactionCost();
            }
        }

        TransactionSummary transactionSummary = new TransactionSummary();
        transactionSummary.setCurrentPeriod(currentMonthTotal);
        transactionSummary.setPreviousPeriod(previousMonthTotal);
        transactionSummary.setTotal(cumulativeExceptCurrentTotal);

        return transactionSummary;
    }

    private TransactionSummary calculateTransactionSummaryForDemo(DemographicGroup demo, String currentMonthYear){
        // Display for a studio shows
        // Spending fees for the current month
        // Spending fees for the previous motnh
        // Spending fees for all previous months except the current month

        // first, get all the transactions where the demo was the buyer
        List<Transaction> allTransactions = getPurchaseTransactions(demo.getShortName());

        // convert currentMonthYear to LocalDate
        String[] monthYear = currentMonthYear.split("-");
        int month = Integer.parseInt(monthYear[0]); // subtract 1 because the months index starts at 0
        int year = Integer.parseInt(monthYear[1]);
        LocalDate baseDate = LocalDate.of(year, month, 01);

        // now, calculate the current month
        int currentMonthTotal = 0;
        for (Transaction transaction: allTransactions){
            if (baseDate.compareTo(LocalDate.from(transaction.getCreatedAt())) == 0){
                currentMonthTotal += transaction.getTransactionCost();
            }
        }

        // calculate the previous month
        int previousMonthTotal = 0;
        for (Transaction transaction: allTransactions){
            if(baseDate.minusMonths(1).compareTo(LocalDate.from(transaction.getCreatedAt())) == 0){
                previousMonthTotal += transaction.getTransactionCost();
            }
        }

        // calculate all upto except current month
        int cumulativeExceptCurrentTotal = 0;
        for (Transaction transaction: allTransactions){
            if (baseDate.compareTo(LocalDate.from(transaction.getCreatedAt())) > 0){
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

    private List<Transaction> getAllWatches(){
        List<Transaction> listOfAllTransactions = this.transactionRepository.findAll();

        List<Transaction> listOfOffers = new ArrayList<>();

        for (Transaction transaction: listOfAllTransactions){
            if (transaction.getTransactionType().equalsIgnoreCase("watch")){
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
                    && transaction.getEventYear() == eventYear

                    // new functionality for the retractevent
                    && (!(transaction.getEventType().contains("retracted")))

                        ){
                foundOffering = transaction;
                return foundOffering;
            }
        }
        return null;
    }

    private Transaction checkToSeeIfEventHasBeenWatchedInTheGivenMonth(String eventName, int eventYear, String monthYear){
        List<Transaction> allWatches = getAllWatches();

        Transaction foundWatch = null;
        for (Transaction transaction: allWatches){
            if (transaction.getEventName().equalsIgnoreCase(eventName) && transaction.getCurrentMonthYear().equalsIgnoreCase(monthYear)
                    && transaction.getEventYear() == eventYear){
                foundWatch = transaction;
                return foundWatch;
            }
        }
        return null;
    }

    private Transaction checkToSeeIfStreamHasBeenWatchedInTheGivenMonth(String streamShortName, String monthYear){
        List<Transaction> allWatches = getAllWatches();

        Transaction foundStreamWatch = null;
        for (Transaction transaction: allWatches){
            if (transaction.getVendor().equalsIgnoreCase(streamShortName) && transaction.getCurrentMonthYear().equalsIgnoreCase(monthYear)){
                foundStreamWatch = transaction;
                return foundStreamWatch;
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
                    && transaction.getEventType().equalsIgnoreCase("movie")
                    && transaction.getTransactionType().equalsIgnoreCase("watch")){
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
