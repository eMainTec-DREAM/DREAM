package dream.mgr.rpt.cp.service.spring;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;

import common.bean.MwareConfig;
import common.bean.User;
import common.report.action.ReportAction;
import common.util.CommonUtil;
import common.util.DateUtil;
import dream.mgr.rpt.cp.dao.LovMgrRptCpDAO;
import dream.mgr.rpt.cp.dto.LovMgrRptCpDTO;
import dream.mgr.rpt.cp.dto.LovMgrRptCpLogDTO;
import dream.mgr.rpt.cp.service.LovMgrRptCpService;

/**
 * 출력물 선택 ServiceImpl
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovMgrRptCpServiceTarget"
 * @spring.txbn id="lovMgrRptCpService"
 * @spring.property name="lovMgrRptCpDAO" ref="lovMgrRptCpDAO"
 */
public class LovMgrRptCpServiceImpl implements LovMgrRptCpService
{
    /** 자재팝업 DAO */
    private LovMgrRptCpDAO lovMgrRptCpDAO = null;

    public LovMgrRptCpDAO getLovMgrRptCpDAO() {
		return lovMgrRptCpDAO;
	}

	public void setLovMgrRptCpDAO(LovMgrRptCpDAO lovMgrRptCpDAO) {
		this.lovMgrRptCpDAO = lovMgrRptCpDAO;
	}

	@Override
	public List findList(LovMgrRptCpDTO lovMgrRptCpDTO, User user) throws Exception
    {
        return lovMgrRptCpDAO.findList(lovMgrRptCpDTO, user);
    }
	
	@Override
	public String findTotalCount(LovMgrRptCpDTO lovMgrRptCpDTO, User user)  throws Exception
    {
        return lovMgrRptCpDAO.findTotalCount(lovMgrRptCpDTO, user);
    }
	
    private int logHistory(LovMgrRptCpDTO lovMgrRptCpDTO, User user) throws Exception
    {
	    LovMgrRptCpLogDTO lovMgrRptCpLogDTO = new LovMgrRptCpLogDTO();
	    lovMgrRptCpLogDTO.setParamValue(lovMgrRptCpDTO.getParam());
	    lovMgrRptCpDTO = (LovMgrRptCpDTO) CommonUtil.makeDetailFromList(this.findList(lovMgrRptCpDTO, user), lovMgrRptCpDTO);
	    lovMgrRptCpLogDTO.setRptCpFileAccLogId(lovMgrRptCpDAO.getNextSequence("SQARPTCPFILEACCLOG_ID"));
	    lovMgrRptCpLogDTO.setRptCpFileId(lovMgrRptCpDTO.getRptCpFileId());
	    lovMgrRptCpLogDTO.setRptCpListId(lovMgrRptCpDTO.getRptCpListId());
	    lovMgrRptCpLogDTO.setUserNo(user.getUserId());
	    lovMgrRptCpLogDTO.setUserName(user.getUserName());
	    String datetime = DateUtil.getTimeStamp(user.getOffset(), "yyyyMMddHHmmss");
	    lovMgrRptCpLogDTO.setExeDate(datetime.substring(0, 8));
	    lovMgrRptCpLogDTO.setExeTime(datetime);
	    lovMgrRptCpLogDTO.setTerminalNo(lovMgrRptCpDTO.getRptListNo());
	    return lovMgrRptCpDAO.log(lovMgrRptCpLogDTO, user);
    }
	
	@Override
	public Map makeReport(LovMgrRptCpDTO lovMgrRptCpDTO, User user) throws Exception
    {
	    this.logHistory(lovMgrRptCpDTO, user);
	    
        Map result = new HashMap();
        if("UBI".equals(lovMgrRptCpDTO.getRptFileType())){
            result = makeUBIReport(lovMgrRptCpDTO);
        }
        else if("RDX".equals(lovMgrRptCpDTO.getRptFileType())){
            result = makeRDXReport(lovMgrRptCpDTO);
        }
        
        return result;
    }
    
    private Map makeUBIReport(LovMgrRptCpDTO lovMgrRptCpDTO) throws Exception
    {
        Map result = new HashMap();
        
        String queryFile = lovMgrRptCpDTO.getQueryFile();
        String param = lovMgrRptCpDTO.getParam();
        Map<String,String> paramMap = new Gson().fromJson(param, HashMap.class);
        
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        
        //result file 생성
        Map<String,String [][]> resultMap = new HashMap<String,String [][]>();
        InputStream in = request.getServletContext().getResourceAsStream(queryFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        
        boolean read = false;
        String dataSet = "";
        String query = "";
        String line;
        while (true)
        {
            if((line = br.readLine()) == null) break;
            
            if(line.startsWith("$")) {//쿼리의 시작
                read = true;
                dataSet = line.substring(1);//쿼리의 데이타 셋 key
                continue;
            }
            else if("/".equals(line)) {//쿼리의 끝
                read = false;
                // 쿼리에 파라메터를 셋팅한다.
                for(String key:paramMap.keySet()){
                    query = query.replace("{"+key+"}", paramMap.get(key));
                }
                System.out.println(dataSet);
                String [][] resultArray = lovMgrRptCpDAO.executeQuery(query);
                resultMap.put(dataSet, resultArray);
                dataSet = "";
                query = "";
            }
            if(read) {
                query = query + "\n" + line;
            }
        }
        
        String resultData = makeUBIDataFile(resultMap);
        result.put("jrf", lovMgrRptCpDTO.getDesignFile());
        result.put("data", resultData);
        
        return result;
    }
    
    private String makeUBIDataFile(Map<String, String[][]> resultMap) throws Exception
    {
        StringBuffer sb = new StringBuffer();
        
        for (String key:resultMap.keySet())
        {
            sb.append(key+"#\"");
            String [][] resultArray = (String [][])resultMap.get(key);
            
            for (int array1=0; array1<resultArray.length; array1++)
            {
                // 한줄씩 입력한다.
                String resultLine = "";
                for (int array2=0; array2<resultArray[array1].length; array2++)
                {
                    resultLine = resultLine + resultArray[array1][array2] + "^t";
                }
                
                sb.append(resultLine+"^n");
            }
            sb.append("\"#");
        }
        
        return sb.toString();
    }
    
    private Map makeRDXReport(LovMgrRptCpDTO lovMgrRptCpDTO) throws Exception
    {
        Map result = new HashMap();
        
        String queryFile = lovMgrRptCpDTO.getQueryFile();
        String param = lovMgrRptCpDTO.getParam();
        Map<String,String> paramMap = new Gson().fromJson(param, HashMap.class);
        
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        
        //result file 생성
        List resultList = new ArrayList();
        InputStream in = request.getServletContext().getResourceAsStream(queryFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        
        boolean firstLoop = true;
        String query = "";
        String [][] resultArrayToken = new String[1][1];
        resultArrayToken[0][0] = "//EOR//";
        String line = br.readLine();
        while (line!=null)
        {
            if (!firstLoop)
            {
                // 2개이상의 쿼리는 중간에 쿼리 결과 구분자[//EOR//]를 넣는다.
                resultList.add(resultArrayToken);
                query = "";
            }
            
            if ("$".equals(line))
            {
                // $는 쿼리의 시작이다.
                for(String subline;(subline=br.readLine())!=null;)
                {
                    // 쿼리 끝을 만나면 다음 쿼리가 있는지 확인후 수행한다.
                    if ("/".equals(subline))
                    {
                        break;  // exit for
                    }
                    query = query + "\n" + subline;
                }
                
                // 쿼리에 파라메터를 셋팅한다.
                for(String key:paramMap.keySet()){
                    query = query.replace("{"+key+"}", paramMap.get(key));
                }
                
                String [][] resultArray = lovMgrRptCpDAO.executeQuery(query);
                resultList.add(resultArray);
                
                line = br.readLine();
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
        String resultFile = this.makeRDXFile(resultList, lovMgrRptCpDTO.getRptListName());
        
        // temp 폴더의 파일 삭제
        deleteRDXFiles();
        
        result.put("strutsAction", ReportAction.RD_CALL);
        result.put("reportName", lovMgrRptCpDTO.getDesignFile());
        result.put("resultFileName", resultFile);
        
        return result;
    }
    
    private String makeRDXFile(List resultList, String reportName) throws IOException
    {
        String resultToken = "//EOR//";
        String dataToken = "^|^";
        String currentTime = DateUtil.getDateTime("yyyyMMddHH24mmssSSS");
        
        String resultFileName = reportName+"_"+currentTime+".txt";
        
        //FileWriter fileOut = null;
        BufferedWriter uniOutput = null;
        
        try
        {
            String fileName = "";
            File tempFile;
            if(MwareConfig.osName.indexOf("LINUX") >=0){
                fileName =  MwareConfig.getWebAppPath()+"dream/print/rd/temp/" + resultFileName;
                tempFile = new File(MwareConfig.getWebAppPath()+"dream/print/rd/temp");
            }else{
                fileName =  MwareConfig.getWebAppPath()+"dream\\print\\rd\\temp\\" + resultFileName;
                tempFile = new File(MwareConfig.getWebAppPath()+"dream\\print\\rd\\temp");
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
     * temp 폴더의 쌓인 결과 값들을 삭제한다.
     */
    private void deleteRDXFiles()
    {
        //=======================================================
        // 임시 파일 dir 에 모든 파일 을 삭제한다.
        File dirFile = null;
        
        if(MwareConfig.osName.indexOf("LINUX") >=0){
            dirFile =  new File(MwareConfig.getWebAppPath()+"dream/print/rd" + "/temp/");
        }else{
            dirFile =  new File(MwareConfig.getWebAppPath()+"dream\\print\\rd" + "\\temp\\");
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
}