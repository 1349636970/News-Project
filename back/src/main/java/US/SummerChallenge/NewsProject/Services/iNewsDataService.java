package US.SummerChallenge.NewsProject.Services;

import java.io.IOException;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/6/29 23:29
 **/
public interface iNewsDataService {
    void CCTVfetchData() throws IOException,InterruptedException;
    void CBSNewsfetchData() throws IOException,InterruptedException;
    void XINHUAfetchData() throws IOException,InterruptedException;
    void PEOPLEfetchData() throws IOException,InterruptedException;
    void USTODAYfetchData() throws IOException,InterruptedException;
    void NYTIMEfetchData() throws IOException,InterruptedException;
    void THEHIMALAYANTIMESfetchData() throws IOException,InterruptedException;
    void KATHMANDUPOSTfetchData() throws IOException,InterruptedException;
    void REPUBLICAfetchData() throws IOException,InterruptedException;
}
