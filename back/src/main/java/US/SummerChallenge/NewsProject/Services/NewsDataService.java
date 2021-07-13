package US.SummerChallenge.NewsProject.Services;

import java.io.IOException;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/6/29 23:29
 **/
public interface NewsDataService {
    void CCTVfetchData() throws IOException,InterruptedException;
    void CBSNewsfetchData() throws IOException,InterruptedException;
    void XINHUAfetchData() throws IOException,InterruptedException;
    void PEOPLEfetchData() throws IOException,InterruptedException;

}
