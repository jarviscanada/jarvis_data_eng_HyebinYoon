package java.ca.jrvs.apps.trading.dao;

import org.apache.http.conn.HttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import org.springframework.stereotype.Repository;

import java.ca.jrvs.apps.trading.model.config.MarketDataConfig;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * MarketDataDao is responsible for getting Quotes from IEX
 */
@Repository
public class MarketDataDao implements CrudRepository<IexQuote, String> {

    private static final String IEX_BATCH_PATH = "stock/market/batch?types=quote&token=";
    private String IEX_BATCH_URL;

    private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
    private HttpClientConnectionManager httpClientConnectionManager;

    @Autowired
    public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager, MarketDataConfig marketDataConfig){
        this.httpClientConnectionManager = httpClientConnectionManager;
        IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH + marketDataConfig.getToken();
    }

    @Override
    public <S extends IexQuote> S save(S s) {
        return null;
    }

    @Override
    public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    /**
     * Get an IexQuote (helper method which class findAllById)
     * @param ticker
     * @throws IllegalArgumentException if a given ticker is invalid
     * @throws org.springframework.dao.DataRetrievalFailureException if HTTP request failed
     * @return iexQuote
     */
    @Override
    public Optional<IexQuote> findById(String ticker) {
        Optional<IexQuote> iexQuote;
        List<IexQuote> quotes = (List<IexQuote>) findAllById(Collections.singletonList(ticker));

        if (quotes.size() == 0){
            return Optional.empty();
        }else if (quotes.size() == 1){
            iexQuote = Optional.of(quotes.get(0));
        }else{
            throw new DataRetrievalFailureException("Unexpected number of quotes");
        }
        return iexQuote;

    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<IexQuote> findAll() {
        return null;
    }

    /**
     * Get quotes from IEX
     * @param tickers is a list of tickers
     * @return a list of IexQuote object
     * throws IllegalArgumentException if any ticker is invalid or tickers is empty
     * DataRetrievalFailureException if HTTP request failed
     */
    @Override
    public Iterable<IexQuote> findAllById(Iterable<String> tickers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(IexQuote iexQuote) {

    }

    @Override
    public void deleteAll(Iterable<? extends IexQuote> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}