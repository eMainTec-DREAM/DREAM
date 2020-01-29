package dream.consult.program.setting.upload.service.spring;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import common.bean.MwareConfig;
import common.bean.User;
import common.exception.SqlIgnoreException;
import common.file.FileUploadUtil;
import common.file.MwareFile;
import common.util.CommonUtil;
import dream.consult.program.setting.upload.dao.ConsultPgmSettingUpdFileDAO;
import dream.consult.program.setting.upload.dto.ConsultPgmSettingUpdFileDTO;
import dream.consult.program.setting.upload.service.ConsultPgmSettingUpdFileService;

/**
 * 엑셀업로드 - 상세 serviceimpl 
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="consultPgmSettingUpdFileServiceTarget"
 * @spring.txbn id="consultPgmSettingUpdFileService"
 * @spring.property name="consultPgmSettingUpdFileDAO" ref="consultPgmSettingUpdFileDAO"
 */
public class ConsultPgmSettingUpdFileServiceImpl implements ConsultPgmSettingUpdFileService
{
    private ConsultPgmSettingUpdFileDAO consultPgmSettingUpdFileDAO = null;
    
    public ConsultPgmSettingUpdFileDAO getConsultPgmSettingUpdFileDAO() {
		return consultPgmSettingUpdFileDAO;
	}

	public void setConsultPgmSettingUpdFileDAO(ConsultPgmSettingUpdFileDAO consultPgmSettingUpdFileDAO) {
		this.consultPgmSettingUpdFileDAO = consultPgmSettingUpdFileDAO;
	}
	
	public int insertDetail(ConsultPgmSettingUpdFileDTO consultPgmSettingUpdFileDTO, String[] fileNames, User user) throws IOException
    {
	    ConsultPgmSettingUpdFileService consultPgmSettingUpdFileService = (ConsultPgmSettingUpdFileService)CommonUtil.getBean("consultPgmSettingUpdFileService",user);
	    List mappingList = consultPgmSettingUpdFileDAO.getExcelTableCol(consultPgmSettingUpdFileDTO.getExcelTabId());
	    Map excelToTable = new HashMap<String,String>();
	    if(mappingList!=null && mappingList.size()>0) {
	        for(Object map:mappingList) {
	            excelToTable.put(((Map) map).get("EXCELCOLNAME"), ((Map) map).get("TABLECOLNAME"));
	        }
	    }
	    
	    consultPgmSettingUpdFileDAO.deleteData(consultPgmSettingUpdFileDTO.getTableName(), user);
	    for(String fileName:fileNames){
	        consultPgmSettingUpdFileService.uploadData(consultPgmSettingUpdFileDTO, fileName, excelToTable, user);
	    }
	    
	    int rtnVal=0;
	    List idList = consultPgmSettingUpdFileDAO.getIds(consultPgmSettingUpdFileDTO, user);
	    if(idList!=null && idList.size()>0) {
	        for(Object idMap:idList){
	            Object id = ((Map) idMap).get("EXCELTABLISTID");
	            rtnVal = consultPgmSettingUpdFileDAO.updateDetail(id, user);
	        }
	    }
	    else {
	        consultPgmSettingUpdFileDTO.setExcelTabListId(consultPgmSettingUpdFileDAO.getNextSequence("sqaexceltablist_id"));
	        rtnVal = consultPgmSettingUpdFileDAO.insertDetail(consultPgmSettingUpdFileDTO, user);
	    }
	    return rtnVal;
    }
	
	public int uploadData(ConsultPgmSettingUpdFileDTO consultPgmSettingUpdFileDTO, String fileName, Map excelToTable, User user)
	{
	    int rtnVal=0;
        
        String nfFilePath ="";
        if(MwareConfig.osName.indexOf("LINUX") >=0){
            nfFilePath = consultPgmSettingUpdFileDTO.getCompNo() + "/TEMP/";
        }else{
            nfFilePath = consultPgmSettingUpdFileDTO.getCompNo() + "\\TEMP\\";
        }
        Workbook workbook = null;
        try
        {
            workbook = WorkbookFactory.create(new File(MwareConfig.getFileDir()+nfFilePath+fileName));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return rtnVal;
        }
        finally
        {
            try
            {
                workbook.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        // Cell에 값이 없을 경우 빈 값으로 가져오기
        workbook.setMissingCellPolicy(MissingCellPolicy.CREATE_NULL_AS_BLANK);
        // 수식된 값을 가져오기 위함
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        //만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
        Sheet sheet=workbook.getSheet(consultPgmSettingUpdFileDTO.getSheetName());
        if(sheet==null) sheet=workbook.getSheetAt(0);
        //행의 수
        int rows=sheet.getPhysicalNumberOfRows();
        
        Map headMap = new HashMap<Integer,String>();
        //first row is head
        Row head=sheet.getRow(0);
        short lastInd = head.getLastCellNum();
        for(short headindex=0;headindex<lastInd;headindex++) {
            Cell cell=head.getCell(headindex);
            if(cell==null) {
                continue;
            }
            else if(excelToTable.get(cell.getStringCellValue())==null) {
                continue;
            }
            else{
                headMap.put(headindex, excelToTable.get(cell.getStringCellValue()));
            }
        }
        
        for(int rowindex=1;rowindex<rows;rowindex++){
            //행을읽는다
            Row row=sheet.getRow(rowindex);
            if(row !=null){
                Map valueMap = new HashMap<Integer,String>();
                for(short columnindex=0;columnindex<lastInd;columnindex++){
                    try{
	                	//셀값을 읽는다
	                    Cell cell=row.getCell(columnindex);
	                    String value="";
	                    //헤드의 값이 빈값일경우 pass
	                    if(headMap.get(columnindex)==null){
	                        continue;
	                    }
	                    else{
	                        int cellType = (cell.getCellType()==Cell.CELL_TYPE_FORMULA)?evaluator.evaluateFormulaCell(cell):cell.getCellType();
	                        switch(cellType)
	                        {
	                            case Cell.CELL_TYPE_NUMERIC:
	                                if(DateUtil.isCellDateFormatted(cell)) {
	                                    value = new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
	                                }
	                                else {
	                                    cell.setCellType(Cell.CELL_TYPE_STRING);
	                                    value=cell.getStringCellValue();
	                                }
	                                break;
	                            default:
	                                value=cell.getStringCellValue();
	                                break;
	                        }
	                    }
	                    valueMap.put(columnindex, value);
                    }catch(Exception e){
	            	   e.printStackTrace();
	                }
                }
                String rowString = "";
                for(Object cellString:valueMap.values()){
                    rowString += cellString;
                }
                if(!"".equals(rowString)){
                    try
                    {
                        rtnVal += consultPgmSettingUpdFileDAO.insertExcelData(consultPgmSettingUpdFileDTO.getTableName(),headMap,valueMap,user);
                    }
                    catch (SqlIgnoreException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        try
        {
            workbook.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return rtnVal;
	}
	
    public Map<String,String> uploadAutoFiles(ConsultPgmSettingUpdFileDTO consultPgmSettingUpdFileDTO, HttpServletRequest request, HttpServletResponse response, User user) throws Exception
    {
        int result = 0;
        
        //저장 경로 저장
        String nfFilePath ="";
        if(MwareConfig.osName.indexOf("LINUX") >=0){
        	nfFilePath = consultPgmSettingUpdFileDTO.getCompNo() + "/TEMP";
        }else{
        	nfFilePath = consultPgmSettingUpdFileDTO.getCompNo() + "\\TEMP";
        }

        consultPgmSettingUpdFileDTO.setNfFilePath(nfFilePath);
        
        String mode = request.getParameter("mode");
        String action = "";

        List<FileItem> items = null;
        
        if (mode == null || (mode != null && !mode.equals("conf") && !mode.equals("sl"))) {
            items = (List<FileItem>) request.getAttribute("items");
        }
  
        Map<String,String> rstMap = FileUploadUtil.checkFile(items, "TEMP");
        if(rstMap.containsValue("ERROR"))
        {
        	return rstMap;
        }
        
        if (mode == null) {
            mode = "";

            for (FileItem item : items) {
                if (item != null) {
                    if (item.getFieldName().equals("mode")) {
                        mode = getStringFromStream(item.getInputStream());
                    }
                    if (item.getFieldName().equals("action")) {
                        action = getStringFromStream(item.getInputStream());
                    }
                }
            }
        }

        if (mode.equals("conf")) {
            
            int maxPostSize = Integer.parseInt(MwareConfig.getMaxFileSize())*1000000;
            //MwareConfig.get
            response.setHeader("Content-type", "text/json");
            response.getWriter().write("{ \"maxFileSize\":" + maxPostSize + " }");
        }

        if (mode.equals("html4") || mode.equals("flash") || mode.equals("html5")) {
            if (action.equals("cancel")) {
                response.setHeader("Content-type", "text/json");
                response.getWriter().write("{\"state\":\"cancelled\"}");
            } else {
                String filename = "";
                Integer filesize = 0;
                
                for (FileItem item : items) 
                {
                    if (!item.isFormField()) 
                    {
                        MwareFile mwareFile = FileUploadUtil.doFileUpload(item, 
                                FilenameUtils.getName(item.getName()), nfFilePath,consultPgmSettingUpdFileDTO.getCompNo()); //file, physicalName(저장될 이름), 저장될 폴더  
                        //================================================================================
                        // 저장된 엑셀 데이타를 DB 에 저장한다.
                        //result += consultPgmSettingUpdFileDAO.insertFileInfo(mwareFile, consultPgmSettingUpdFileDTO, user);
                    }
                    
                }

                response.setHeader("Content-type", "text/html");
                response.getWriter().write("{\"state\":true,\"name\":\"" + filename.replace("\"","\\\"") + "\",\"size\":" + filesize + ",\"extra\":{\"info\":\"just a way to send some extra data\",\"param\":\"some value here\"}}");
            }
        }
        
        return rstMap;
    }

    public String getStringFromStream(InputStream is) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    @Override
    public void deleteAutoFiles(ConsultPgmSettingUpdFileDTO consultPgmSettingUpdFileDTO, String[] deleteRows)
    {
        if(deleteRows != null)
        {
            String nfFilePath ="";
            if(MwareConfig.osName.indexOf("LINUX") >=0){
                nfFilePath = consultPgmSettingUpdFileDTO.getCompNo() + "/TEMP";
            }else{
                nfFilePath = consultPgmSettingUpdFileDTO.getCompNo() + "\\TEMP";
            }
            
            for(String fileName : deleteRows)
            {
                FileUploadUtil.deleteFile(nfFilePath, fileName);
            }
            
        }
    }
    
}
