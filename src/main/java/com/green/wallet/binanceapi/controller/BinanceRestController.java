package com.green.wallet.binanceapi.controller;


import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.account.Account;
import com.binance.api.client.domain.account.Trade;
import com.binance.api.client.domain.general.Asset;
import com.binance.api.client.domain.market.TickerPrice;
import com.binance.api.client.domain.market.TickerStatistics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BinanceRestController {


    @GetMapping(path = "getAllTrades")
    public List<Trade> getAllTrades(@RequestParam(value = "apiKey") String apiKey, @RequestParam(value = "secret") String secret,@RequestParam(value = "coin") String coin){
        BinanceApiRestClient client = getClient(apiKey,secret);
        List<Trade> myTrades = client.getMyTrades(coin);//BNBUSDT
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

    private BinanceApiRestClient getClient(String APIKEY, String SECRET){
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(APIKEY, SECRET);
        return factory.newRestClient();
    }

}
