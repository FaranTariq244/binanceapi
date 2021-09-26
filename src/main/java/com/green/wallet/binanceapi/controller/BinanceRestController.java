package com.green.wallet.binanceapi.controller;


import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.account.*;
import com.binance.api.client.domain.account.request.AllOrderListRequest;
import com.binance.api.client.domain.account.request.AllOrdersRequest;
import com.binance.api.client.domain.general.Asset;
import com.binance.api.client.domain.market.OrderBook;
import com.binance.api.client.domain.market.TickerPrice;
import com.binance.api.client.domain.market.TickerStatistics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BinanceRestController {



    @GetMapping(path = "getAllOrders")
    public List<Order> getAllOrders(@RequestParam(value = "apiKey") String apiKey, @RequestParam(value = "secret") String secret, @RequestParam(value = "coin") String coin){
        BinanceApiRestClient client = getClient(apiKey,secret);
        AllOrdersRequest orderRequest = new AllOrdersRequest(coin);
        List<Order> lOrders = client.getAllOrders(orderRequest);
        System.out.println(lOrders);
        return lOrders;
    }


    @GetMapping(path = "getAllTrades")
    public List<Trade> getAllTrades(@RequestParam(value = "apiKey") String apiKey, @RequestParam(value = "secret") String secret,@RequestParam(value = "coin") String coin){
        BinanceApiRestClient client = getClient(apiKey,secret);
        List<Trade> myTrades = client.getMyTrades(coin.toUpperCase(),1000,null, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW,System.currentTimeMillis());//BNBUSDT
        System.out.println(myTrades);
        return myTrades;
    }


    @GetMapping(path = "getAccountBalance")
    public Account getAccountBalance(@RequestParam(value = "apiKey") String apiKey, @RequestParam(value = "secret") String secret){
        BinanceApiRestClient client = getClient(apiKey,secret);
        Account account = client.getAccount();
        System.out.println(account.getBalances());
        //System.out.println(account.getAssetBalance("ETH").getFree());
        return account;
    }


    @GetMapping(path = "getAssets")
    public List<Asset> getAssets(@RequestParam(value = "apiKey") String apiKey, @RequestParam(value = "secret") String secret){
        BinanceApiRestClient client = getClient(apiKey,secret);
        List<Asset> allAssets = client.getAllAssets();
        System.out.println(allAssets);
        return allAssets;
    }

    @GetMapping(path = "getAllCoins")
    public List<TickerPrice> getAllCoins(@RequestParam(value = "apiKey") String apiKey, @RequestParam(value = "secret") String secret){
        BinanceApiRestClient client = getClient(apiKey,secret);
        List<TickerPrice> allPrices = client.getAllPrices();
        System.out.println(allPrices);
        return allPrices;
    }

    @GetMapping(path = "getAllSymbols")
    public List<String> getAllSymbols(@RequestParam(value = "apiKey") String apiKey, @RequestParam(value = "secret") String secret){
        BinanceApiRestClient client = getClient(apiKey,secret);
        List<TickerPrice> allPrices = client.getAllPrices();
        List<String> symbolList = new ArrayList<>();
        for(TickerPrice tp: allPrices){
            symbolList.add(tp.getSymbol());
        }
        System.out.println(allPrices);
        return symbolList;
    }


    @GetMapping(path = "/getCoinPrice")
    public TickerStatistics getCoinPrice(@RequestParam(value = "coin") String coin, @RequestParam(value = "apiKey") String apiKey, @RequestParam(value = "secret") String secret){
        BinanceApiRestClient client = getClient(apiKey,secret);
        TickerStatistics tickerStatistics = client.get24HrPriceStatistics(coin);//NEOETH
        System.out.println(tickerStatistics.getLastPrice());
        return tickerStatistics;
    }



    @GetMapping(path = "getServerTime")
    public long getServerTime(@RequestParam(value = "apiKey") String apiKey,@RequestParam(value = "secret") String secret){
        BinanceApiRestClient client = getClient(apiKey,secret);
        long serverTime = client.getServerTime();
        System.out.println(serverTime);
        return serverTime;
    }
    @GetMapping(path = "getHistoricalTrade")
    public List<TradeHistoryItem> getHistoricalTrade(@RequestParam(value = "apiKey") String apiKey, @RequestParam(value = "secret") String secret, @RequestParam(value = "coin") String coin){
        BinanceApiRestClient client = getClient(apiKey,secret);
        List<TradeHistoryItem> tradeHistoryItems = client.getHistoricalTrades(coin,null,null);
        System.out.println(tradeHistoryItems);
        return tradeHistoryItems;
    }
    @GetMapping(path = "getOrderBook")
    public OrderBook getOrderBook(@RequestParam(value = "apiKey") String apiKey, @RequestParam(value = "secret") String secret, @RequestParam(value = "coin") String coin){
        BinanceApiRestClient client = getClient(apiKey,secret);
        OrderBook orderBook = client.getOrderBook(coin,null);
        System.out.println(orderBook);
        return orderBook;
    }
    private BinanceApiRestClient getClient(String APIKEY, String SECRET){
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(APIKEY, SECRET);
        return factory.newRestClient();
    }

}
