package dream.consult.program.setting.upload.service.spring;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.bean.User;
import common.exception.SqlIgnoreException;
import common.util.CommonUtil;
import common.util.StringUtil;
import dream.consult.program.setting.upload.dao.ConsultPgmSettingUpdDAO;
import dream.consult.program.setting.upload.dto.ConsultPgmSettingUpdDTO;
import dream.consult.program.setting.upload.service.ConsultPgmSettingUpdService;
import dream.consult.program.setting.upload.service.ExcelUploadService;

/**
 * Excel Data Upload - List Service implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="consultPgmSettingUpdServiceTarget"
 * @spring.txbn id="consultPgmSettingUpdService"
 * @spring.property name="consultPgmSettingUpdDAO" ref="consultPgmSettingUpdDAO"
 */
public class ConsultPgmSettingUpdServiceImpl implements ConsultPgmSettingUpdService
{
	private ConsultPgmSettingUpdDAO consultPgmSettingUpdDAO = null;

	public List findExldataList(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception
    {      
	    List excelColList = consultPgmSettingUpdDAO.findExcelColList(consultPgmSettingUpdDTO, user);
	    consultPgmSettingUpdDTO.setExcelColList(excelColList);
        return consultPgmSettingUpdDAO.findExldataList(consultPgmSettingUpdDTO,user);
    }

	public int deleteExldataList( String table, String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + consultPgmSettingUpdDAO.deleteExldataList(table, id, user);
            }
        return result;
    }

	public ConsultPgmSettingUpdDAO getConsultPgmSettingUpdDAO() {
		return consultPgmSettingUpdDAO;
	}

	public void setConsultPgmSettingUpdDAO(ConsultPgmSettingUpdDAO consultPgmSettingUpdDAO) {
		this.consultPgmSettingUpdDAO = consultPgmSettingUpdDAO;
	}
	public String findTotalCount(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO,User user) throws Exception
    {
        return consultPgmSettingUpdDAO.findTotalCount(consultPgmSettingUpdDTO, user);
    }
	
	public List getDummyHeader(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception {
	    return consultPgmSettingUpdDAO.getDummyHeader(consultPgmSettingUpdDTO, user);
	}
	
	public List findExcelTemplateData(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception
	{
	    List dataList = new ArrayList();
	    Map dataMap = new HashMap();
	    
	    //main template
    	    List mainHeader = consultPgmSettingUpdDAO.findExcelHeader(consultPgmSettingUpdDTO, user);
    	    String mainSheetName = consultPgmSettingUpdDAO.findExcelSheetName(consultPgmSettingUpdDTO, user);
    	    
    	    List mainList = new ArrayList();
    	    Map mainMap = new HashMap();
    	    for(Object obj:mainHeader)
    	    {
    	        Map headMap = (Map) obj;
    	        
    	        mainMap.put(headMap.get("ID"), headMap.get("COLCOMMENT"));
    	    }
    	    mainList.add(mainMap);
            
            dataMap.put("SHEETNAME", mainSheetName);
            dataMap.put("HEADLIST", mainHeader);
            dataMap.put("RESULTLIST", mainList);
            dataList.add(dataMap);
        
        //sub template
            List<Map> excelExScriptList = consultPgmSettingUpdDAO.findExcelExScript(consultPgmSettingUpdDTO,user);
            
            String subSheetName = "";
            List subList = new ArrayList();
            List subHeader = new ArrayList();
            CharSequence inputStr;
            StringBuffer scriptBuffer;
            Pattern pattern;
            Matcher matcher;
            String fieldName;
            String methodName;
            Method[] methods;
            boolean isMethod;
            Method configMethod;
            for(Map excelExScriptMap:excelExScriptList){
                dataMap = new HashMap();
                
                inputStr = String.valueOf(excelExScriptMap.get("SCRIPT"));
                scriptBuffer = new StringBuffer();
                pattern = Pattern.compile("\\$\\$[a-zA-Z_]*\\$\\$");
                matcher = pattern.matcher(inputStr);
                while(matcher.find()){
                    fieldName = matcher.group().replaceAll("\\$\\$", "").replaceAll("_", "");
                    methodName = "GET".concat(fieldName.toUpperCase());
                    
                    methods = user.getClass().getMethods();
                    isMethod = false;
                    for(Method method : methods)
                    {
                        if(methodName.equals(method.getName().toUpperCase()))
                        {
                            isMethod = true;
                            methodName = method.getName();
                        }
                    }
                    if(!isMethod) continue;
                    
                    configMethod = user.getClass().getMethod(methodName);
                    matcher.appendReplacement(scriptBuffer, configMethod.invoke(user).toString());
                }
                matcher.appendTail(scriptBuffer);
                
                subSheetName = String.valueOf(excelExScriptMap.get("SHEETNAME"));
                try{
                    subList = consultPgmSettingUpdDAO.exeScriptToList(scriptBuffer.toString());
                    subHeader = CommonUtil.makeHeader(subList);
                    
                    dataMap.put("SHEETNAME", subSheetName);
                    dataMap.put("HEADLIST", subHeader);
                    dataMap.put("RESULTLIST", subList);
                    dataList.add(dataMap);
                } catch(SqlIgnoreException se){
                    se.printStackTrace();
                }
            }
	    
	    return dataList;
	}

	@Override
	public void uploadData(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception {
		
	    consultPgmSettingUpdDTO = consultPgmSettingUpdDAO.findExcelTab(consultPgmSettingUpdDTO, user);
	    if("PROCEDURE".equals(consultPgmSettingUpdDTO.getExcelUploadType())) {
	        consultPgmSettingUpdDAO.uploadData(consultPgmSettingUpdDTO.getUploadPgmName(), user);
	    }
	    else if("JAVA".equals(consultPgmSettingUpdDTO.getExcelUploadType())) {
	        ExcelUploadService excelUploadService = (ExcelUploadService) CommonUtil.getBean("excelUploadService", user);
	        List<Map> dataList = this.findExldataList(consultPgmSettingUpdDTO, user);
	        Map resultMap = new HashMap();
	        Map paramMap = new HashMap();
	        for(Map dataMap:dataList){
	            resultMap = excelUploadService.upload(consultPgmSettingUpdDTO.getExcelTabNo(), dataMap, user);
	            paramMap.put("MESSAGE", resultMap.get("MESSAGE"));
	            paramMap.put("IS_SUCCESS", resultMap.get("IS_SUCCESS"));
	            consultPgmSettingUpdDTO.setParamMap(paramMap);
	            consultPgmSettingUpdDTO.setSkeyId(StringUtil.valueOf(resultMap.get("SKEY_ID")));
	            consultPgmSettingUpdDAO.updateExldataDetail(consultPgmSettingUpdDTO, user);
	        }
	    }
	}
	
	public List findExldataDetail(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception
    {
        List excelColList = consultPgmSettingUpdDAO.findExcelColList(consultPgmSettingUpdDTO, user);
        consultPgmSettingUpdDTO.setExcelColList(excelColList);
        return consultPgmSettingUpdDAO.findExldataDetail(consultPgmSettingUpdDTO, user);
    }
    
    public int insertExldataDetail(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception
    {
        return consultPgmSettingUpdDAO.insertExldataDetail(consultPgmSettingUpdDTO, user);
    }
    
    public int updateExldataDetail(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception
    {
        consultPgmSettingUpdDAO.updateUpdDate(consultPgmSettingUpdDTO.getExcelTabId(), user);
        return consultPgmSettingUpdDAO.updateExldataDetail(consultPgmSettingUpdDTO, user);
    }
    
    public void uploadExldataDetail(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception
    {
        String skeyId = consultPgmSettingUpdDTO.getSkeyId();
        consultPgmSettingUpdDTO = consultPgmSettingUpdDAO.findExcelTab(consultPgmSettingUpdDTO, user);
        consultPgmSettingUpdDTO.setSkeyId(skeyId);
        if("PROCEDURE".equals(consultPgmSettingUpdDTO.getExcelUploadType())) {
            consultPgmSettingUpdDAO.uploadExldataDetail(consultPgmSettingUpdDTO, user);
        }
        else if("JAVA".equals(consultPgmSettingUpdDTO.getExcelUploadType())) {
            ExcelUploadService excelUploadService = (ExcelUploadService) CommonUtil.getBean("excelUploadService", user);
            List<Map> dataList = this.findExldataList(consultPgmSettingUpdDTO, user);
            Map resultMap = new HashMap();
            Map paramMap = new HashMap();
            if(dataList.size() == 1) {
                resultMap = excelUploadService.upload(consultPgmSettingUpdDTO.getExcelTabNo(), dataList.get(0), user);
                paramMap.put("MESSAGE", resultMap.get("MESSAGE"));
                paramMap.put("IS_SUCCESS", resultMap.get("IS_SUCCESS"));
                consultPgmSettingUpdDTO.setParamMap(paramMap);
                consultPgmSettingUpdDTO.setSkeyId(StringUtil.valueOf(resultMap.get("SKEY_ID")));
                consultPgmSettingUpdDAO.updateExldataDetail(consultPgmSettingUpdDTO, user);
            }
        }
    }
    
    public List findField(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception
    {
        return consultPgmSettingUpdDAO.findExcelColList(consultPgmSettingUpdDTO, user);
    }
}

