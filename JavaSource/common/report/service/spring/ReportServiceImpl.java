package common.report.service.spring;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.google.gson.Gson;

import common.bean.MwareConfig;
import common.bean.User;
import common.report.dao.ReportDAO;
import common.report.form.ReportForm;
import common.report.service.ReportService;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QueryBuffer;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 * Report 결과 조회
 * @author javaworker
 * @version $Id: ReportServiceImpl.java,v 1.5 2014/02/04 01:31:12 jung7126 Exp $
 * @since 1.0
 * @spring.bean id="reportServiceTarget"
 * @spring.txbn id="reportService"
 * @spring.property name="reportDAO" ref="reportDAO"
 */
public class ReportServiceImpl
        implements ReportService
{
    //결과 Text 파일 경로(RESULT_PATH) : MwareConfig.getWebAppPath()+"dream\\rd\\\temp\\";
    //SQL Text 파일 경오(SQL_PATH) : MwareConfig.getWebAppPath()+"dream\\rd\\\";
    
    /** parameter 구분자 */
    private final String paramToken = "^";
    
    /** 결과 구분자 */
    private final String resultToken = "//EOR//";
    
    /** result data 구분자 */
    private final String dataToken = "^|^";
    
    /** master alias token 구분자 */
    private final char masterAlias = ':';
    
    private ReportDAO reportDAO;

    /** Array 구분자 */
    private String [][] resultArrayToken = new String[1][1];
    
    public ReportDAO getReportDAO()
    {
        return reportDAO;
    }

    public void setReportDAO(ReportDAO reportDAO)
    {
        this.reportDAO = reportDAO;
    }

    public void makeResultFile(ReportForm reportForm)
    {
        //Find Package No From Company Info for report file location.
        List<Map> compList = MwareConfig.getCompanies();
        String packageNo = "";
        for(Map compMap : compList)
        {
            if(reportForm.getParam1().equals(String.valueOf(compMap.get("CODE"))))
            {
                packageNo = compMap.get("PACKAGE_NO")==null?"":String.valueOf(compMap.get("PACKAGE_NO"));
            }
        }

        List resultList = new ArrayList();

        // 레포트 파일이름
        String reportName = reportForm.getReportName()+reportForm.getParam1();       
        String qrdName = reportForm.getQrdName()+reportForm.getParam1();
        
        String webAppPath = MwareConfig.getWebAppPath();
        String dbName = MwareConfig.getServerDatabase();
        String s = "\\";
        if(MwareConfig.osName.indexOf("LINUX") >=0) s = "/";
        
        // 공장 레포트 파일 검사(원본파일명+회사코드)
        String reportPath = "enhance"+s+packageNo+s+"rd"+s;
        File mrdFile = new File(webAppPath+reportPath+reportName+".mrd");
        if(!mrdFile.exists()) {
            reportName = reportForm.getReportName();
            reportPath = "dream"+s+"rd"+s;
            mrdFile = new File(webAppPath+reportPath+reportName+".mrd");
        }
        File qrdFile = new File(webAppPath+"enhance"+s+packageNo+s+"rd"+s+dbName+s+qrdName+".qrd");
        if(!qrdFile.exists()) {
            qrdName = reportForm.getQrdName();
            qrdFile = new File(webAppPath+"dream"+s+"rd"+s+dbName+s+qrdName+".qrd");
        }
        
        if(!mrdFile.exists()||!qrdFile.exists()){
        	//두 파일 중 하나라도 없으면.
            return;
        }
        reportForm.setReportName(reportName);
        reportForm.setReportPath(reportPath);
        
        resultArrayToken[0][0] = resultToken;
        
        System.out.println("호출되는 리포트 네임:"+reportName);
        
        BufferedReader br = null;
        
        try
        {
            br = new BufferedReader(new FileReader(qrdFile));

            boolean firstLoop = true;
            
            //마스터 쿼리
            //List masterQryList = null;
            //String masterQry = "";
            
            String query = "";
            String line = br.readLine();
            while (line!=null)
            {
                /*
                 * 쿼리 수행후에 다시 쿼리중이라면
                 * [$2] 인경우 중간에 결과 구분자 //EOR//을 셋팅하지 않는다. 처리는 [$]와 같다.
                 */ 
                if (!firstLoop && !"$2".equals(line))
                {
                    // 2개이상의 쿼리는 중간에 쿼리 결과 구분자[//EOR//]를 넣는다.
                    resultList.add(resultArrayToken);
                    query = "";
                }
                
                if ("$".equals(line)|| "$2".equals(line))
                {
                    // $는 쿼리의 시작이다.
                    for(String subline;(subline=br.readLine())!=null;)
                    {
                        // 쿼리 끝을 만나면 다음 쿼리가 있는지 확인후 수행한다.
                        if ("/".equals(subline))
                        {
                            break;  // exit for
                        }
                        query = query + "\n" + getQuery(subline, br, reportForm);
                    }
                    
                    // 쿼리에 파라메터를 셋팅한다.
                    query = refactorQeury(query, reportForm);
                    
                    String [][] resultArray = reportDAO.executeQuery(query);
                    resultList.add(resultArray);
                    
                    line = br.readLine();
                }
                // 2개 이상인 경우는 master - detail 이다.
                else if ("$$".equals(line))
                {
                    //==========================================================
                    // $$ 는 쿼리의 시작이다.
                    for (String subline;(subline=br.readLine())!=null;)
                    {
                        // 쿼리 끝을 만나면 다음 쿼리가 있는지 확인후 수행한다.
                        if ("/".equals(subline))
                        {
                            break;  // exit for
                        }
                        query = query + "\n" + getQuery(subline, br, reportForm);
                    }

                    ArrayList queryDtlArray = new ArrayList();
                    //==========================================================
                    // $$ 다음은  $$$ 로 시작되어야만 한다.
                    if ("$$$".equals(br.readLine()))
                    {
                        line = setQeuryDtl(queryDtlArray, br, reportForm, "$$$");
                    }
                    
                    query = refactorQeury(query, reportForm);   // 쿼리에 파라메터를 셋팅한다.
                    List masterList = reportDAO.executeMasterQuery(query);    // 첫번째 쿼리 수행
                    
                    /*
                     * Master List 로 Detail List 조회(쿼리 만듬)
                     * 마지막 쿼리가 아니거나 그 이하 하위 Detail List 가 없다면 
                     * 조회된 Detail 만 resultList 에 셋팅
                     * 아니라면
                     * 다시 마지막 Detail List를 Master로 반복
                     */
                    masterList = masterDetailQuery(reportForm, masterList, resultList, queryDtlArray, 0, true);
                }

                firstLoop = false;
            }
            
            /*
             * 1건이상 입력된 되었다면 2개이상의 sql이 실행된 경우이다.
             * 이때 마지막에 [//EOR//] 을 추가한다.
             */
            if (resultList.size() > 1)
            {
                resultList.add(resultArrayToken);
            }
            
            // resultList 의 결과값으로 파일을 생성한다.
            String resultFileName = this.makeResultFile(resultList, reportName);
            
            reportForm.setResultFileName(resultFileName);
            
            // temp 폴더의 파일 삭제
            deleteTempResultFiles();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                br.close();
            }
            catch(IOException ex){}
        }
    }

    /**
     * Master List 로 Detail List 조회(쿼리 만듬)
     * 마지막 쿼리가 아니거나 그 이하 하위 Detail List 가 없다면 
     * 조회된 Detail 만 resultList 에 셋팅
     * 아니라면 다시 마지막 Detail List를 Master로 반복
     * @author  javaworker
     * @version $Id: ReportServiceImpl.java,v 1.5 2014/02/04 01:31:12 jung7126 Exp $
     * @param reportForm 
     * @since   1.0
     * 
     * @param masterList
     * @param resultList
     * @param queryDtlArray1
     * @param size
     * @return
     */
    private List masterDetailQuery(ReportForm reportForm, List masterList, List resultList,
            ArrayList queryDtlArray, int nextDtlsize, boolean needToken)
    {
        /*
         * 첫번째 쿼리 결과 만큼 반복하면서, 
         * 첫번째 결과를 두번째 쿼리조건으로 사용하면서 두번째 쿼리 결과 셋팅한다.
         */
        for (int i=0; i<masterList.size(); i++)
        {
            if (i != 0 && needToken) resultList.add(resultArrayToken);
            
            // 첫번째 결과 조회결과에 셋팅
            resultList.add(getFirstrResult(masterList, i));
            
            // 두번째 Detail 쿼리 생성
            for (int j=0; j<queryDtlArray.size(); j++)
            {
                String dtlQuery = (String)queryDtlArray.get(j);
                
                // 첫번째 결과 변수를 두번째 쿼리에 셋팅
                dtlQuery = refactorDetailQuery(dtlQuery, masterList.get(i));
                
                // 쿼리에 파라메터를 셋팅한다.
                dtlQuery = refactorQeury(dtlQuery, reportForm);
                
                // $$$$[하위 detail] 값이 없거나 마지막 $$$ 값이 아니라면 셋팅 
                if (nextDtlsize == 0 || j<(queryDtlArray.size()-1))
                {
                    String [][] resultArray = reportDAO.executeQuery(dtlQuery);
                    if (j!=0) resultList.add(resultArrayToken);
                    resultList.add(resultArray);
                }
                // 마지막 $$$ 이고 하위 Detail $$$$ 에 값이 있다면 
                else
                {
                    List detailList1 = reportDAO.executeMasterQuery(dtlQuery);    // 첫번째 쿼리 수행
                    return detailList1;
                }
            }
        }
        return null;
    }

    /**
     * Detail 작성
     * @author  javaworker
     * @version $Id: ReportServiceImpl.java,v 1.5 2014/02/04 01:31:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param queryDtlArray1
     * @param br
     * @param reportForm 
     * @param string 
     * @return
     */
    private String setQeuryDtl(ArrayList queryDtlArray,
            BufferedReader br, ReportForm reportForm, String dtlStr) throws IOException
    {
        String queryDtl = "";
        
        // $$$ 는 detail 쿼리의 시작이다.
        for(String subline;(subline=br.readLine())!=null;)
        {
            // 쿼리 끝을 만나면 다음 쿼리가 있는지 확인후 수행한다.
            if ("/".equals(subline))
            {
                break;  // exit for
            }
            queryDtl = queryDtl + "\n" + getQuery(subline, br, reportForm);
        }
        queryDtlArray.add(queryDtl);
        
        String line = br.readLine();
        // 다음 문장이 Detail 단위(dtlStr) 같다면 다시 재귀호출
        if (dtlStr.equals(line)) line = setQeuryDtl(queryDtlArray, br, reportForm, dtlStr);
        
        return line;
    }

    /**
     * qrd 파일에 라인을 파싱한다.
     * @author  javaworker
     * @version $Id: ReportServiceImpl.java,v 1.5 2014/02/04 01:31:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param subline
     * @param br
     * @return
     */
    private String getQuery(String subline, BufferedReader br, ReportForm reportForm) throws IOException
    {
        /*
         * command
         * @AND : 비교연산자
         * @AND_DATE : 일자연산자
         */
        if (subline.indexOf("@AND") == -1 &&
            subline.indexOf("@AND_DATE") == -1 &&
            subline.indexOf("@IF") == -1 &&
            subline.indexOf("@END_IF") == -1 ) return subline;
        
        subline = subline.trim();
        StringTokenizer st = new StringTokenizer(subline, "(,)");
        if (st.hasMoreTokens())
        {
            String command = st.nextToken();
            
            // AND 연산자
            if ("@AND".equals(command))
            {
                String firstParam = st.nextToken().replaceAll("\"", "").trim();
                String nextParam  = st.nextToken().replaceAll("\"", "").trim();
                
                QueryBuffer query = new QueryBuffer();
                
                nextParam = refactorQeury(nextParam, reportForm); // ^param1 을 값을 치환
                
                query.getAndQuery(firstParam, nextParam);
                
                return query.toString();
            }
            else if ("@AND_DATE".equals(command))
            {
                // ^param을 ' or " 로 감쌌어도 출력되게 함
                
                String dateCol     = st.nextToken().replaceAll("\"", "").trim();
                String fromDateVal = st.nextToken().replaceAll("\"", "").trim();
                String toDateVal   = st.nextToken().replaceAll("\"", "").trim();
                
                fromDateVal = fromDateVal.replaceAll("'", "");
                toDateVal = toDateVal.replaceAll("'", "");
                
                fromDateVal = refactorQeury(fromDateVal, reportForm); // ^param1 을 값을 치환
                toDateVal = refactorQeury(toDateVal, reportForm); // ^param1 을 값을 치환
                
                QueryBuffer query = new QueryBuffer();
                
                query.getAndDateQuery(dateCol, fromDateVal, toDateVal);
                
                return query.toString();
            }
            else if ("@IF".equals(command))
            {
                String com_ = st.nextToken();    // 안에 수식 "" <= ^Param1 이와 같이 있음
                com_ = refactorQeury(com_, reportForm); // ^param1 을 값을 치환
                
                String query = "";
                for(String line;(line=br.readLine())!=null;)
                {
                    if ("@END_IF".equals(line)) break;
                    
                    if (!"".equals(query)) query = query + "\n";    // 첫번째를 제외하고 붙이기전 줄바꿈 한다.
                    query = query + line;
                }
                
                /*
                 * 수식 : ==, <= ,>=, <>
                 */
                int signIndex;
                if ((signIndex=com_.indexOf("==")) != -1)
                {
                    String firstStr  = (com_.substring(0, signIndex)).replaceAll("\"", "").trim();
                    String secondStr = (com_.substring(signIndex+2)).replaceAll("\"", "").trim();
                    
                    if (firstStr.equals(secondStr)) return query;
                    else return "";
                }
                else if ((signIndex=com_.indexOf("<=")) != -1)
                {
                    String firstStr  = (com_.substring(0, signIndex)).replaceAll("\"", "").trim();
                    String secondStr = (com_.substring(signIndex+2)).replaceAll("\"", "").trim();
                    
                    int firstNum = Integer.parseInt(firstStr);
                    int secondNum = Integer.parseInt(secondStr);
                    
                    if (firstNum <= secondNum) return query;
                    else return "";
                }
                else if ((signIndex=com_.indexOf(">=")) != -1)
                {
                    String firstStr  = (com_.substring(0, signIndex)).replaceAll("\"", "").trim();
                    String secondStr = (com_.substring(signIndex+2)).replaceAll("\"", "").trim();
                    
                    int firstNum = Integer.parseInt(firstStr);
                    int secondNum = Integer.parseInt(secondStr);
                    
                    if (firstNum >= secondNum) return query;
                    else return "";
                }
                else if ((signIndex=com_.indexOf("<>")) != -1)
                {
                    String firstStr  = (com_.substring(0, signIndex)).replaceAll("\"", "").trim();
                    String secondStr = (com_.substring(signIndex+2)).replaceAll("\"", "").trim();
                    
                    if (!firstStr.equals(secondStr)) return query;
                    else return "";
                }
            }
        }
        
        return subline;
    }

    /**
     * temp 폴더의 쌓인 결과 값들을 삭제한다.
     * @author  javaworker
     * @version $Id: ReportServiceImpl.java,v 1.5 2014/02/04 01:31:12 jung7126 Exp $
     * @since   1.0
     *
     */
    private void deleteTempResultFiles()
    {
        //=======================================================
        // 임시 파일 dir 에 모든 파일 을 삭제한다.
        File dirFile = null;
        String rdExtPath = MwareConfig.getServerDatabase();
        
        if(MwareConfig.osName.indexOf("LINUX") >=0){
        	if(!"".equals(rdExtPath)) rdExtPath = "/"+rdExtPath;
        	dirFile =  new File(MwareConfig.getWebAppPath()+"dream/rd" + rdExtPath + "/temp/");
        }else{
        	if(!"".equals(rdExtPath)) rdExtPath = "\\"+rdExtPath;
        	dirFile =  new File(MwareConfig.getWebAppPath()+"dream\\rd" + rdExtPath + "\\temp\\");
        }
        
        
        File [] files = dirFile.listFiles();
        for (int i=0; i<files.length; i++)
        {
            // 일자가 오늘날짜 이전에 생성된것들은 모두 삭제한다.
            long fileDateLong = files[i].lastModified();
            Date fileDate = new Date(fileDateLong);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            
            // 파일 수정일자
            String madeDate = dateFormat.format(fileDate);
            
            // 현재일자
            String currentDate = DateUtil.getDateTime("yyyyMMdd");
            
            try
            {
                // 파일생성일자 < 현재일자 라면 삭제한다.
                if (DateUtil.compareDate(madeDate, null, "<", currentDate, null))
                {
                    files[i].delete();
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();                
            }
        }
    }

    /**
     * Detail 쿼리를 셋팅한다.
     * @author  javaworker
     * @version $Id: ReportServiceImpl.java,v 1.5 2014/02/04 01:31:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param query
     * @param masterMap
     * @return
     */
    private String refactorDetailQuery(String query, Object masterObj)
    {
        // casting
        Map masterMap = (Map)masterObj;
        
        char [] queryCharArray = query.toCharArray();
        
        int joinCount = 0;
        for (int i=0; i<queryCharArray.length; i++)
        {
            if (masterAlias == queryCharArray[i])
            {
                joinCount++;
            }
        }
        
        // : 로 붙은 변수들을 모두 조회한다.
        String [] joinColumns = new String[joinCount];
        
        int joinColIndex = 0;
        
        for (int i=0; i<joinCount; i++)
        {
            joinColIndex = query.indexOf(":", joinColIndex) + 1;
            
            // : 붙은 문자부터 뒤에  ' 이 올때까지의 문자열이 마스터 쿼리의 컬럼[Alias]이다.
            joinColumns[i] = query.substring(joinColIndex, query.indexOf('\'', joinColIndex));
        }
        
        // query replace
        for (int i=0; i<joinCount; i++)
        {
            Object resultObj = masterMap.get(joinColumns[i].toUpperCase());
            query = query.replaceAll(":"+joinColumns[i], resultObj==null?":"+joinColumns[i]:resultObj.toString());
        }
        
        return query;
    }

    /**
     * resultList 의 결과값으로 파일을 생성한다.
     * @author  javaworker
     * @version $Id: ReportServiceImpl.java,v 1.5 2014/02/04 01:31:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param resultList
     * @param reportName 
     * @return
     */
    private String makeResultFile(List resultList, String reportName) throws IOException
    {
        String currentTime = DateUtil.getDateTime("yyyyMMddHH24mmssSSS");
        
        String resultFileName = reportName+"_"+currentTime+".txt";
        
        String rdExtPath = MwareConfig.getServerDatabase();
        
        //FileWriter fileOut = null;
        BufferedWriter uniOutput = null;
        
        try
        {
            String fileName = "";
            File tempFile;
            if(MwareConfig.osName.indexOf("LINUX") >=0){
            	if(!"".equals(rdExtPath)) rdExtPath = "/"+rdExtPath;
            	fileName =  MwareConfig.getWebAppPath()+"dream/rd" + rdExtPath + "/temp/" + resultFileName;
            	tempFile = new File(MwareConfig.getWebAppPath()+"dream/rd" + rdExtPath + "/temp");
            }else{
            	if(!"".equals(rdExtPath)) rdExtPath = "\\"+rdExtPath;
            	fileName =  MwareConfig.getWebAppPath()+"dream\\rd" + rdExtPath + "\\temp\\" + resultFileName;
            	tempFile = new File(MwareConfig.getWebAppPath()+"dream\\rd" + rdExtPath + "\\temp");
            }
            
            //파일 객체 생성
            if(!tempFile.exists()){
                //디렉토리 생성 메서드
            	tempFile.mkdirs();
            }
            
            //fileOut = new FileWriter(file);
            
            uniOutput = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(fileName), "UTF8")
                    );

            
            for (int i=0; i<resultList.size(); i++)
            {
                String [][] resultArray = (String [][])resultList.get(i);
                
                for (int array1=0; array1<resultArray.length; array1++)
                {
                    // 한줄씩 입력한다.
                    String resultLine = "";
                    for (int array2=0; array2<resultArray[array1].length; array2++)
                    {
                        if (resultArray.length == 1 && 
                            resultArray[0].length == 1 && 
                            resultToken.equals(resultArray[0][0]))
                        {
                            /*
                             * resultArray 에 1개의 string만 들어가 있고,
                             * 들어 있는값이 resultToken[//EOR//] 과 같다면 뒤에 dataToken[^|^]을 붙이지 않는다.
                             */ 
                            resultLine = resultLine + resultArray[array1][array2];
                        }
                        else
                        {
                            resultLine = resultLine + resultArray[array1][array2] + dataToken;
                        }
                    }
                    
                    //2019.02.11 textarea의 "/n"으로 인한 RD의 Error 발생 --> ^||^으로 Replace 해서 보냄 RD에서 요약함수 replacestr()사용하여, 
                    //개행함수 hexstr("0D0A")로 치환하시면 됩니다. 예) replacestr(필드명, "^||^", hexstr("0D0A"))
                    resultLine = resultLine.replaceAll("\n", "^||^");
                    
                    uniOutput.write(resultLine+"\n");
                }
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            uniOutput.close();
        }
        
        return resultFileName;
    }

    /**
     * query에 파라메터를 셋팅한다.
     * @author  javaworker
     * @version $Id: ReportServiceImpl.java,v 1.5 2014/02/04 01:31:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param query
     * @param reportForm
     */
    private String refactorQeury(String query, ReportForm reportForm)
    {
        //======================================
        String param1 = reportForm.getParam1();
        String param2 = reportForm.getParam2();
        String param3 = reportForm.getParam3();
        String param4 = reportForm.getParam4();
        String param5 = reportForm.getParam5();
        String param6 = reportForm.getParam6();
        String param7 = reportForm.getParam7();
        String param8 = reportForm.getParam8();
        String param9 = reportForm.getParam9();
        String param10 = reportForm.getParam10();
        String param11 = reportForm.getParam11();
        String param12 = reportForm.getParam12();
        String param13 = reportForm.getParam13();
        String param14 = reportForm.getParam14();
        String param15 = reportForm.getParam15();
        //======================================
        
        query = query.replaceAll("\\" + paramToken + "param1", param1);
        query = query.replaceAll("\\" + paramToken + "param2", param2);
        query = query.replaceAll("\\" + paramToken + "param3", param3);
        query = query.replaceAll("\\" + paramToken + "param4", param4);
        query = query.replaceAll("\\" + paramToken + "param5", param5);
        query = query.replaceAll("\\" + paramToken + "param6", param6);
        query = query.replaceAll("\\" + paramToken + "param7", param7);
        query = query.replaceAll("\\" + paramToken + "param8", param8);
        query = query.replaceAll("\\" + paramToken + "param9", param9);
        query = query.replaceAll("\\" + paramToken + "param10", param10);
        query = query.replaceAll("\\" + paramToken + "param11", param11);
        query = query.replaceAll("\\" + paramToken + "param12", param12);
        query = query.replaceAll("\\" + paramToken + "param13", param13);
        query = query.replaceAll("\\" + paramToken + "param14", param14);
        query = query.replaceAll("\\" + paramToken + "param15", param15);
        
        return query;
    }
    
    /**
     * index의 결과 를 2차 String[][]로 리턴한다.
     * @author  javaworker
     * @version $Id: ReportServiceImpl.java,v 1.5 2014/02/04 01:31:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param resultArray
     * @param index
     * @return
     */
    private String [][] getFirstrResult(List masterList, int index)
    {
        String [][] resultArray = QueryBuffer.toStringArray(masterList);
        
        String [][] result = new String[1][];
        result[0] = resultArray[index];
        
        return result;
    }
    
    /**
     * Jasper Report Common Call Method
     * @throws JRException 
     * @throws DRException 
     * @throws IOException 
     */
    public String viewReport(String jasperReportName, List resultList, User user) throws JRException, DRException, IOException
    {
        String baseUrl ="";
        String jsaperReportUrl = "";
        String packageNo = CommonUtil.getPackageNo(user.getCompNo());
        String conPath = "\\";
        if(MwareConfig.osName.indexOf("LINUX") >=0) conPath = "/";

        jsaperReportUrl = MwareConfig.getWebAppPath()+"dream"+conPath+"jasper"+conPath+"template"+conPath+"masterReport.jrxml"; //Master File is located dream/jasper...
        if(!"".equals(packageNo)) baseUrl = MwareConfig.getWebAppPath()+"enhance"+conPath+packageNo+conPath+"jasper"+conPath;

        //If the Report Folder doesn't exist, make the baseUrl as DREAM.
        File mrdFile = new File(baseUrl + jasperReportName + ".jrxml");
        if(!mrdFile.exists()) baseUrl = MwareConfig.getWebAppPath()+"dream"+conPath+"jasper"+conPath;

        
        /*if(MwareConfig.osName.indexOf("LINUX") >=0){
            if(!"".equals(rdExtPath)) rdExtPath = rdExtPath + "/";
            
            if(!"".equals(packageNo)) packageNo = "enhance/"+packageNo+"/";
            else packageNo = "dream/";
                
            enhancePath =  packageNo+"rd/" + rdExtPath;
            originPath =  "dream/rd/" + rdExtPath;
        }else{
            if(!"".equals(rdExtPath)) rdExtPath = rdExtPath + "\\";
            
            if(!"".equals(packageNo)) packageNo = "enhance\\"+packageNo+"\\";
            else packageNo = "dream\\";
            
            enhancePath =  packageNo+"rd\\" + rdExtPath;
            originPath =  "dream\\rd\\" + rdExtPath;
        }*/
        
        JasperCompileManager.compileReportToFile(baseUrl+jasperReportName+".jrxml",baseUrl+jasperReportName+".jasper");
        
        Map resultMap = new HashMap();
        resultMap.put("reportData", resultList);
        resultMap.put("reportName", baseUrl.replaceAll("\\\\", "/")+jasperReportName+".jasper");
        
        Gson gson = new Gson();
        String jsonStr = gson.toJson(resultMap);
        String destReportUrl = baseUrl + jasperReportName+".pdf";

        InputStream iostream = new ByteArrayInputStream(jsonStr.getBytes(StandardCharsets.UTF_8));
        JsonDataSource dataSource = new JsonDataSource(iostream);
        
        JasperDesign jasperDesign = JRXmlLoader.load(jsaperReportUrl);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        
        Map parameters = new HashMap();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//        JasperViewer.viewReport(jasperPrint, false);
        
//        JasperExportManager.exportReportToHtmlFile(jasperPrint,destReportUrl);
        JasperExportManager.exportReportToPdfFile(jasperPrint,destReportUrl);
        
        return destReportUrl;

    }
    
    public String viewReport(String jasperReportName, String jsonStr, Map parameters, User user) throws JRException, DRException, IOException
    {
        return makeSingleReport(jasperReportName, jsonStr, parameters);
    }
    
    public String viewReport(String jasperReportName, String jsonStr, User user) throws JRException, DRException, IOException
    {
        Map parameters = new HashMap();
        return makeSingleReport(jasperReportName, jsonStr, parameters);
    }
    
    public String makeSingleReport(String jasperReportName, String jsonStr, Map parameters) throws JRException, IOException
    {
        String baseUrl = "";
        if(MwareConfig.osName.indexOf("LINUX") >=0){
            baseUrl = MwareConfig.getWebAppPath()+"dream/jasper/";
        }else{
            baseUrl = MwareConfig.getWebAppPath()+"dream\\jasper\\";
        }

        
        InputStream iostream = new ByteArrayInputStream(jsonStr.getBytes(StandardCharsets.UTF_8));
        JsonDataSource dataSource = new JsonDataSource(iostream);
        
        String jsaperReportUrl = baseUrl + jasperReportName+".jrxml";
        String destReportUrl = baseUrl + jasperReportName+".pdf";

        JasperDesign jasperDesign = JRXmlLoader.load(jsaperReportUrl);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        
        JasperExportManager.exportReportToPdfFile(jasperPrint,destReportUrl);
        
        return destReportUrl;
//        JasperViewer.viewReport(jasperPrint, false);

    }
    
    public String getImgPath(String image)
    {
        String baseUrl ="";
        if(MwareConfig.osName.indexOf("LINUX") >=0){
            baseUrl = MwareConfig.getWebAppPath()+"dream/jasper/images/"+image;
        }else{
            baseUrl =MwareConfig.getWebAppPath()+"dream/jasper/images/"+image;
        }
        return baseUrl.replaceAll("\\\\", "/");
    }
}