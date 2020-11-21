package com.gatech.streamingwars.service;


import com.gatech.streamingwars.maindb.model.*;
import com.gatech.streamingwars.maindb.repository.*;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MainDBService {

    @Autowired
    DemographicRepository demographicRepository;

    @Autowired
    StudioRepository studioRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    StreamingServiceRepository streamingServiceRepository;

    @Autowired
    public EventOfferRepository eventOfferRepository;

    @Autowired
    public TransactionRepository transactionRepository;

    public List<DemographicGroup> saveDemographicGroup(List<DemographicGroup> group) throws DataIntegrityViolationException,SQLIntegrityConstraintViolationException
    {
        List<DemographicGroup> save = demographicRepository.saveAll(group);
        return save;
    }

    public List<DemographicGroup> findAllDemographicGroup()
    {
        List<DemographicGroup> demographicGroupList = this.demographicRepository.findAll();
        return demographicGroupList;
    }

    public List<DemographicGroup> findDemographicGroupForArchival(LocalDateTime localDateTime)
    {
        List<DemographicGroup> demogrphicGroupGreaterThanCreatedDate = demographicRepository.getDemogrphicGroupLessThanCreatedDate(localDateTime);
        return demogrphicGroupGreaterThanCreatedDate;
    }

    public DemographicRepository getDemographicRepository(){
        return this.demographicRepository;
    }

    public List<DemographicGroup>  getAllDemos(LocalDateTime startDate,LocalDateTime endDate)
    {
        List<DemographicGroup> demographicGroups = demographicRepository.getDemographicGroupBetweenDates(startDate,endDate);
        return demographicGroups;
    }

    public Optional<DemographicGroup> findDemoGraphicGroupWithID(Long id) {
        return demographicRepository.findById(id);
    }

    public List<Studio> findAllStudios() {
        List<Studio> all = studioRepository.findAll();
        return all;
    }

    public List<Event> findAllEvents() {
        List<Event> eventRepositoryAll = eventRepository.findAll();
        return  eventRepositoryAll;
    }

    public List<StreamingService> findAllServices() {
        List<StreamingService> streamingServices = streamingServiceRepository.findAll();
        return streamingServices;
    }

    public StreamingService findStreamingServiceByName(String shortName)
    {
        return streamingServiceRepository.findByShortName(shortName);
    }

    public void saveEventOffer(EventOffer eventOffer) throws ConstraintViolationException,SQLIntegrityConstraintViolationException {
        eventOfferRepository.save(eventOffer);
    }

    public List<EventOffer> findByServiceID(String  serviceName)
    {
        StreamingService streamingService = streamingServiceRepository.findByShortName(serviceName);
        return eventOfferRepository.findByService(streamingService);
    }


    public DemographicGroup lookupDemographicGroupByShortName(String demographicGroupShortName){
        // looks up demographic group via the short name
        List<DemographicGroup> demographicGroupList = findAllDemographicGroup();
        DemographicGroup demographicGroupToSearchFor = null;
        for (DemographicGroup demographicGroup: demographicGroupList){
            if (demographicGroup.getShortName().equalsIgnoreCase(demographicGroupShortName)){
                demographicGroupToSearchFor = demographicGroup;
                return demographicGroupToSearchFor;
            }
        }
        return null;
    }

    public Studio lookupStudioByShortName(String studioShortName){
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

    public StreamingService lookupStreamByShortName(String streamShortName){
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

    public Event lookupEventByNameAndYear(String eventName, int eventYear){
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

    public List<Transaction> getSalesTransactions(String vendorShortName){
//        String vendorShortName = vendor.getShortName();

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

    public List<Transaction> getPurchaseTransactions(String buyerName){

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

    public TransactionSummary calculateTransactionSummaryForStream(StreamingService stream, String currentMonthYear){
        // Display for a StreamingService
        // Subscription fees for the current month
        // Subscription fees for the previous motnh
        // Subscription fees for all previous months except the current month

        // first, get all the transactions where the StreamingService was the vendor
        List<Transaction> allSalesTransactions = getSalesTransactions(stream.getShortName());

        // convert currentMonthYear to LocalDate
        String[] monthYear = currentMonthYear.split("-");
        int month = Integer.parseInt(monthYear[0]); // subtract 1 because the months index starts at 0
        int year = Integer.parseInt(monthYear[1]);
        LocalDate baseDate = LocalDate.of(year, month, 01);

        // now, calculate the current month
        int currentMonthTotal = 0;
        for (Transaction transaction: allSalesTransactions){
            if (baseDate.compareTo(LocalDate.from(transaction.getCreatedAt())) == 0){
                currentMonthTotal += transaction.getTransactionCost();
            }
        }

        // calculate the previous month
        int previousMonthTotal = 0;
        for (Transaction transaction: allSalesTransactions){
            if(baseDate.minusMonths(1).compareTo(LocalDate.from(transaction.getCreatedAt())) == 0){
                previousMonthTotal += transaction.getTransactionCost();
            }
        }

        // calculate all upto except current month
        int cumulativeExceptCurrentTotal = 0;
        for (Transaction transaction: allSalesTransactions){
            if (baseDate.compareTo(LocalDate.from(transaction.getCreatedAt())) > 0){
                cumulativeExceptCurrentTotal += transaction.getTransactionCost();
            }
        }

        TransactionSummary transactionSummary = new TransactionSummary();
        transactionSummary.setCurrentPeriod(currentMonthTotal);
        transactionSummary.setPreviousPeriod(previousMonthTotal);
        transactionSummary.setTotal(cumulativeExceptCurrentTotal);

        // Unique to StreamingServices
        // Calculate the total Licensing fees

        List<Transaction> allPurchases = getPurchaseTransactions(stream.getShortName());
        int totalLicensingFees = 0;
        for (Transaction transaction: allPurchases){
            totalLicensingFees+=transaction.getTransactionCost();
        }

        transactionSummary.setLicensing(totalLicensingFees);

        return transactionSummary;
    }

    public TransactionSummary calculateTransactionSummaryForStudio(Studio studio, String currentMonthYear){
        // Display for a studio shows
        // Licensing fees for the current month
        // Licensing fees for the previous motnh
        // Licensing fees for all previous months except the current month

        // first, get all the transactions where the studio was the vendor
        List<Transaction> allTransactions = getSalesTransactions(studio.getShortName());

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

    public TransactionSummary calculateTransactionSummaryForDemo(DemographicGroup demo, String currentMonthYear){
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

    public List<Transaction> getAllOffers(){
        List<Transaction> listOfAllTransactions = this.transactionRepository.findAll();

        List<Transaction> listOfOffers = new ArrayList<>();

        for (Transaction transaction: listOfAllTransactions){
            if (transaction.getTransactionType().equalsIgnoreCase("offer")){
                listOfOffers.add(transaction);
            }
        }

        return listOfOffers;
    }

    public List<Transaction> getAllWatches(){
        List<Transaction> listOfAllTransactions = this.transactionRepository.findAll();

        List<Transaction> listOfOffers = new ArrayList<>();

        for (Transaction transaction: listOfAllTransactions){
            if (transaction.getTransactionType().equalsIgnoreCase("watch")){
                listOfOffers.add(transaction);
            }
        }

        return listOfOffers;
    }

    public boolean isDemographicEditable(String demographicName,String currentMonthYear)
    {
        List<Transaction> byNameAndCurrentMonthYear = transactionRepository.findByNameAndCurrentMonthYear(demographicName, currentMonthYear);
        if(byNameAndCurrentMonthYear.size()==0)
        {
            return true;
        }
        else {
            return false;
        }
    }

    public Transaction checkToSeeIfStreamingIsOfferingEvent(String streamingServiceShortName, String eventName, int eventYear){
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

    public Transaction checkToSeeIfEventHasBeenWatchedInTheGivenMonth(String eventName, int eventYear, String monthYear){
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

    public Transaction checkToSeeIfStreamHasBeenWatchedInTheGivenMonth(String streamShortName, String monthYear){
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

    public Transaction returnSubscriptionBetweenStreamAndDemoWithLargestPercentage(String streamingServiceShortName, String demoGroupShortName){

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

    public List<EventOffer> findAllEventOffers() {
        List<EventOffer> all = eventOfferRepository.findAll();
        return  all;
    }

    public List<EventOffer> saveAllEventOfferData(List<EventOffer> allEventOffers) {
        List<EventOffer> eventOffers = eventOfferRepository.saveAll(allEventOffers);
        return eventOffers;
    }

    public List<Event> getAllEvents() {
        List<Event> all = eventRepository.findAll();
        return all;
    }

    public Optional<Event> getEventByID(Long eventId) {
        Optional<Event> byId = eventRepository.findById(eventId);
        return byId;
    }

    public List<Event> saveAllEvents(List<Event> events) throws DataIntegrityViolationException,SQLIntegrityConstraintViolationException{
        List<Event> events1 = eventRepository.saveAll(events);
        return events1;
    }

    public StreamingService saveStreamingService(StreamingService streamingServiceByName) throws DataIntegrityViolationException,SQLIntegrityConstraintViolationException{
        StreamingService save = streamingServiceRepository.save(streamingServiceByName);
        return save;
    }

    public  List<EventOffer> lookupEventStreamBasedOnCurrentMonth(String eventName,String streamName, String currentYearMonth)
    {
        Event eventByName = eventRepository.findEventByName(eventName);
        StreamingService byShortName = streamingServiceRepository.findByShortName(streamName);

        List<EventOffer> byServiceEventCurrentYear = eventOfferRepository.findByServiceEventCurrentYear(eventByName, byShortName, currentYearMonth);
        return  byServiceEventCurrentYear;

    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Studio saveStudio(Studio studio) {
        Studio save = studioRepository.save(studio);
        return save;
    }
}
