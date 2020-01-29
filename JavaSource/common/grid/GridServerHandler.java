package common.grid;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import common.util.CommonUtil;
import com.fins.gt.common.Const;
import com.fins.gt.export.AbstractXlsWriter;
import com.fins.gt.export.SimpleXlsWriter;
import com.fins.gt.model.GridInfo;
import com.fins.gt.model.PageInfo;
import com.fins.gt.util.BeanUtils;
import com.fins.gt.util.JSONUtils;
import com.fins.gt.util.LogHandler;
import com.fins.gt.util.StringUtils;
import com.fins.org.json.JSONArray;
import com.fins.org.json.JSONException;
import com.fins.org.json.JSONObject;

/**
 * sigma grid server handler
 * @author fins
 * @version $Id: GridServerHandler.java,v 1.1 2013/08/30 09:12:56 javaworker Exp $
 * @since 1.0
 */
public class GridServerHandler 
{
    public static String CONTENTTYPE = "text/html; charset=UTF-8";
	public static String GT_JSON_NAME = "_gt_json";
	public static String DATA_ROOT = "data";
		
	private String action;
	private String exception;

	private List data;
	private String recordType;
	private String encoding=null;
	
	private List fieldsName;
	
	private boolean success;
	
	private HttpServletRequest request;
	private HttpServletResponse response;

	private JSONObject jsonObject;
	
	private Class dataBeanClass=null;
	private JSONArray jsonData =null;
	
	private GridInfo gridInfo = new GridInfo();
	
	private PageInfo pageInfo = new PageInfo();
	
	private List sortInfo = new ArrayList();
	private List filterInfo= new ArrayList();
	private List columnInfo= new ArrayList();
	
	private Map parameters = new HashMap();
	
	private Map parameterMap;
	
	private Map writers = new HashMap();
	
	/** data clien[browser]에서 전송여부 */
	private boolean isParam;
	
    public void setIsParam(boolean isParam)
    {
        this.isParam = isParam;
    }
	
	public GridServerHandler(){}
	
	public GridServerHandler(String gtJson){
		init(gtJson);
	}
	
	public GridServerHandler(Map parameterMap){
		setParameterMap(parameterMap);
		init();
	}
	
	public GridServerHandler(HttpServletRequest request,HttpServletResponse response) {
		setRequest(request);
		setResponse(response);
//		init();
	}

	public void init(String gtJson)
	{
		if (StringUtils.isNotEmpty(gtJson))
		{
			try 
			{
				LogHandler.debug(" AJAX IN : "+gtJson);
				jsonObject = new JSONObject(gtJson);
				action=jsonObject.getString("action");
				recordType=jsonObject.getString("recordType");
				
				initGridInfo();
				/*
				if ("load".equalsIgnoreCase(action))
				{
					initPageInfo();
					initSortInfo();
					initFilterInfo();
				}
				else if ("save".equalsIgnoreCase(action)){}
				else 
				*/    
				    
				if ("export".equalsIgnoreCase(action))
				{
					initColumnInfo();
				}
			}
			catch (JSONException e) 
			{
				LogHandler.error(this,e);
			}
		}
	}
    
	public void init() {
		init(getParameter(GT_JSON_NAME));
	}
	
	public void initGridInfo(){
		JSONObject modelJS;
		try {
			modelJS = jsonObject.getJSONObject("gridInfo");
			if (modelJS!=null){
				setGridInfo(ModelUtils.createGridInfo(modelJS));
			}
		} catch (JSONException e) {
			//LogHandler.error(this,e);
		}
	}
	
	public void initPageInfo(){
		JSONObject modelJS;
		try {
			modelJS = jsonObject.getJSONObject("pageInfo");
			if (modelJS!=null){
				setPageInfo(ModelUtils.createPageInfo(modelJS));
			}
		} catch (JSONException e) {
			LogHandler.error(this,e);
		}
	}

	public void initSortInfo(){
		JSONArray modelJS;
		try {
			modelJS = jsonObject.getJSONArray("sortInfo");
			if (modelJS!=null){
				for (int i=0;i<modelJS.length();i++){
					JSONObject si = modelJS.getJSONObject(i);
					sortInfo.add( ModelUtils.createSortInfo(si));
				}
			}
		} catch (JSONException e) {
			LogHandler.error(this,e);
		}
	}

	public void initFilterInfo(){
		JSONArray modelJS;
		try {
			modelJS = jsonObject.getJSONArray("filterInfo");
			if (modelJS!=null){
				for (int i=0;i<modelJS.length();i++){
					JSONObject si = modelJS.getJSONObject(i);
					filterInfo.add( ModelUtils.createFilterInfo(si));
				}
			}
		} catch (JSONException e) {
			LogHandler.error(this,e);
		}
	}
	
	public void initColumnInfo(){
		JSONArray modelJS;
		try {
			modelJS = jsonObject.getJSONArray("columnInfo");
			if (modelJS!=null){
				for (int i=0;i<modelJS.length();i++){
					JSONObject si = modelJS.getJSONObject(i);
					columnInfo.add(ModelUtils.createColumnInfo(si));
				}
			}
		} catch (JSONException e) {
			LogHandler.error(this,e);
		}
	}
	

	public void setXlsWriter(AbstractXlsWriter writer){
		writers.put("xls", writer);
	}
	
	public AbstractXlsWriter getXlsWriter(){
		AbstractXlsWriter writer=null;
		try {
		 writer = (AbstractXlsWriter)writers.get("xls");
		} catch (Exception e) {
			LogHandler.warn(this,e);
		} 
		if (writer==null){
			writer=new SimpleXlsWriter();
		}
		return writer;
	}
	
	public List getDisplayColumnInfo(){
		List disColumnInfo = new ArrayList();
		if (columnInfo!=null){
			for (int i=0,len=columnInfo.size();i<len;i++) {
				ColumnInfo col=(ColumnInfo)columnInfo.get(i);
				if (!col.isHidden()) {
					disColumnInfo.add(col);
				}
			}
		}
		return disColumnInfo;
	}
	
	
	
	public String getSaveResponseText(){
		JSONObject json=new JSONObject();
		try {
			json.put("success", success);
			json.put("exception", exception);
		} catch (JSONException e) {
			LogHandler.error(this,e);
		}
		return json.toString();
	}

	public JSONObject getLoadResponseJSON(){
		JSONObject json=new JSONObject();
		try {
			json.put(DATA_ROOT, jsonData);
			json.put("pageInfo", ModelUtils.generatePageInfoJSON(getPageInfo()));
			json.put("exception", exception);
		} catch (JSONException e) {
			LogHandler.error(this,e);
		}
		return json;
	}
	public String getLoadResponseText(){
		JSONObject json=getLoadResponseJSON();
		String jstr=json==null?"":json.toString();
		LogHandler.debug(" AJAX OUT : "+jstr);
		return jstr;
	}


	public void setData(List data) {
		this.data = data;
		this.dataBeanClass=null;
		setJsonData(new JSONArray(data));
		
	}

	public void setData(List data,Class beanClass) {
		this.data = data;
		this.dataBeanClass=beanClass;
		setJsonData(JSONUtils.BeanList2JSONArray(data, beanClass));
	}


	public List getUpdatedRecords() {
		return getRecordsList("updatedRecords");
	}
	public List getUpdatedRecords(Class beanClass) {
		return getRecordsList("updatedRecords",beanClass);
	}
	public List getInsertedRecords() {
		return getRecordsList("insertedRecords");
	}
	public List getInsertedRecords(Class beanClass) {
		return getRecordsList("insertedRecords",beanClass);
	}	
	public List getDeletedRecords() {
		return getRecordsList("deletedRecords");
	}
	public List getDeletedRecords(Class beanClass) {
		return getRecordsList("deletedRecords",beanClass);
	}

	
	public List getRecordsList(String rname,Class beanClass) {
		List recordsList=null;
		JSONArray records_JS;
		try {
			records_JS = jsonObject.getJSONArray(rname);
			if (records_JS!=null){
				recordsList=new ArrayList();
				Object[] methodsInfo = BeanUtils.getCacheSetterMethodInfo(beanClass);
				for (int i=0;i<records_JS.length();i++){
					JSONObject obj_JS = records_JS.getJSONObject(i);
					recordsList.add(JSONUtils.JSONObject2Bean(obj_JS,beanClass,methodsInfo));
				}
			}
		} catch (JSONException e) {
			//LogHandler.error(this,e);
		}
		return recordsList;
	}
	
	public List getRecordsList(String rname) {
		List recordsList=null;
		JSONArray records_JS;
		try {
			//records_JS = jsonObject.getJSONArray(rname);
		    records_JS = jsonObject.optJSONArray(rname);
			if (records_JS!=null){
				recordsList=new ArrayList();
				for (int i=0;i<records_JS.length();i++){
				    JSONArray obj_JS = records_JS.getJSONArray(i);
					recordsList.add(jSONArrayToMap(obj_JS));
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return recordsList;
	}
	
	/**
	 * Export시 넘겨진 파라메터를 List로 리턴 
	 * @author  javaworker
	 * @version $Id: GridServerHandler.java,v 1.1 2013/08/30 09:12:56 javaworker Exp $
	 * @since   1.0
	 * 
	 * @param rname
	 * @return
	 */
    public List getExportList(String rname)
    {
        List recordsList=null;
        JSONArray records_JS;
        try 
        {
            records_JS = jsonObject.optJSONArray(rname);
            if (records_JS!=null)
            {
                recordsList=new ArrayList();
                for (int i=0;i<records_JS.length();i++)
                {
                    JSONArray obj_JS = records_JS.getJSONArray(i);
                    recordsList.add(jSONArrayToMap(obj_JS));
                }
            }
        } 
        catch (JSONException e) 
        {
            e.printStackTrace();
        }
        return recordsList;
    }
	
    /**
     * dataset 의 한 record를 map으로 만든다.
     * @author  javaworker
     * @version $Id: GridServerHandler.java,v 1.1 2013/08/30 09:12:56 javaworker Exp $
     * @since   1.0
     * 
     * @param obj_JS
     * @return
     */
	private Map jSONArrayToMap(JSONArray obj_JS)
	{
	    Map map=new LinkedHashMap();
	    
	    for (int i=0;i<obj_JS.length();i++)
	    {
	        map.put(i, obj_JS.get(i));
	    }
	    
	    return map;
	}
	
	public String[] getParameterValues(String name){
		return (String[])parameterMap.get(name);
	}
	public String getParameter(String name){
		String[] pv=getParameterValues(name);
		if (pv!=null && pv.length>0){
			return pv[0];
		}
		return null;
	}
	
	public void printResponseText(String text){
		try {
			response.setContentType(CONTENTTYPE);
			PrintWriter out=response.getWriter();
			out.println(text);
			out.flush();
			out.close();
		} catch (IOException e) {
			LogHandler.error(this,e);
		}	
	}

	public void initAttachmentHeader(){
		getResponse().setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0" );
		getResponse().setHeader("Content-Type","application/force-download" );
		getResponse().setHeader("Content-Type","application/octet-stream" );
		getResponse().setHeader("Content-Type","application/download" );
		getResponse().setHeader("Cache-Control","private, max-age=0, must-revalidate" );
		getResponse().setHeader("Pragma","public" );
	}
	
	public void downloadFile( String fileName){
		downloadFile(fileName,Const.nullInt);
		
////		getResponse().setContentType("application/vnd.ms-excel"); 
////	    getResponse().setContentType("application/vnd.openxml"); 
//	    getResponse().setContentType("application/octet-stream; charset=ISO-8859-1");
//	    
//		getResponse().setHeader("Content-Disposition", "attachment; filename=test.xlsx;");    
////		getResponse().setHeader("Content-Length", String.valueOf(file.length())); 
//		
//		getResponse().setContentLength((int)file.length());

	}
	
	public void downloadFile( String fileName ,long  length){
		initAttachmentHeader();
		getResponse().setHeader("Content-Disposition","attachment; filename=\""+fileName+"\"");		
		if (length!=Const.nullInt){
			getResponse().setHeader("Content-Length",String.valueOf(length) );
		}
	}
	
	public void exportXLSExcel(List headData, List rowData, HttpServletRequest req, HttpServletResponse res) throws IOException
	{
	    exportXLSXfromMaps(headData, rowData);
	}
	
	/**
	 * map의 값을 배열로 변한 리턴
	 * @author  javaworker
	 * @version $Id: GridServerHandler.java,v 1.1 2013/08/30 09:12:56 javaworker Exp $
	 * @param isParam 
	 * @since   1.0
	 * 
	 * @param map
	 * @param properiesName
	 * @param rowIndex
	 * @return
	 */
	 /*
	private static Object[] map2Array(Map map){
//		int len=properiesName.length;
//		Object[] objs=new Object[ len ];
//		for (int i=0;i<len;i++){
//			objs[i]=map.get(properiesName[i]);
//		}
//		return objs;
	    Object[] objs = (map.values().toArray());
	    return objs;
	}*/
	public void exportXLSfromMaps(List data, String exportType) throws IOException{
		List cols = this.getDisplayColumnInfo();
		int len=cols.size();
		
		String[] headsName=new String[len] ;
		for (int i=0;i<len;i++){
			ColumnInfo colInfo =(ColumnInfo)cols.get(i);
			headsName[i]=colInfo.getHeader();				
		}

		if ("xlsx".equals(exportType))
		{
//		    exportXLSXfromMaps(data,headsName);
		}
		else
		{
		    exportXLSfromMaps(data,headsName);
		}
	}
	
	/**
	 * XLSX format 으로 export
	 * @author  javaworker
	 * @version $Id: GridServerHandler.java,v 1.1 2013/08/30 09:12:56 javaworker Exp $
	 * @since   1.0
	 * 
	 * @param data
	 * @param res 
	 * @param req 
	 * @param headsName
	 * @throws IOException
	 */
	private void exportXLSXfromMaps(List headList, List data) throws IOException
    {
	    XSSFWorkbook wb = new XSSFWorkbook();

        XSSFSheet sheet = wb.createSheet("sheet");
        
        //스타일 객체 생성
        XSSFCellStyle style = wb.createCellStyle();
        
        //셀 스타일
//        style.setFillBackgroundColor(HSSFColor.WHITE.index);
//        style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
//        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        //보더 스타일
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style.setBottomBorderColor(HSSFColor.BLACK.index);
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style.setLeftBorderColor(HSSFColor.BLACK.index);
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style.setRightBorderColor(HSSFColor.BLACK.index);
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style.setTopBorderColor(HSSFColor.BLACK.index);
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
//        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        //폰트 객체 생성
//        XSSFFont font = wb.createFont(); 
//        
//        font.setFontHeightInPoints((short)10); //폰트 크기
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //폰트 굵게

//        style.setFont(font); 

        // Header Set
        XSSFRow headerRow = sheet.createRow(0);
        
        //셀 높이 설정
        headerRow.setHeight((short)512);
        
        Map<String, String> headMapA = new HashMap<String, String>();
        int k = 0;
        for(Object obj : headList)
        {
            Map headMap = (Map)obj;
            
            String hidden = String.valueOf(headMap.get("HIDDEN"));
            String colId = String.valueOf(headMap.get("ID"));
            String colName = String.valueOf(headMap.get("VALUE"));

            if("false".equals(hidden))
            {
                XSSFCell cell = headerRow.createCell(k);

                cell.setCellStyle(style);
                cell.setCellValue(colName);
                k++;
                
                headMapA.put(colId.toUpperCase(), colName);
                sheet.autoSizeColumn((short)k); //셀 사이즈(width) 자동 조정
            }

        }
        
        

        int i = 0;
        for(Object obj : data)
//        for (int i=0,len=data.size();i<len;i++)
        {
            XSSFRow dataRow = sheet.createRow(i+1);
            
            Map<String,String> record=(Map)obj;

            int j = 0;
            for( String key : record.keySet() )
            {
                if(!headMapA.containsKey(key.toUpperCase())) continue;
                
                String colVal = String.valueOf(record.get(key));
                if(colVal == "null")colVal = "";

                XSSFCell cell = dataRow.createCell(j);  
                cell.setCellValue(colVal);
    
                if(colVal.length() > 35)
                {
                    sheet.setColumnWidth(j, 6000);
                }
                  
                j++;
            }
            
            i++;
        }

        //파일 생성 다운
//        String excelFileName = "C:\\Test.xlsx";
//        String fileName ="grid.xlsx";
//        FileOutputStream fileOut = new FileOutputStream(excelFileName);
//        
//        wb.write(fileOut);
//        fileOut.flush();
//        fileOut.close();
        
//        java.io.File tempFile = new java.io.File(excelFileName);
//
//        res.setContentType("application/octet-stream; charset=ISO-8859-1");
//        res.setHeader("Content-Disposition", 
//                "attachment; filename=" + K2E(fileName) + ";");
//        res.setContentLength((int)tempFile.length());
//        
//        transport(new FileInputStream(tempFile), res.getOutputStream());
        
        //엑셀 다운

//              res.setContentType("application/vnd.ms-excel"); 
//      res.setContentType("application/vnd.openxml"); 
////      res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//      //      res.setContentType("application/octet-stream; charset=ISO-8859-1");
//      res.setHeader("Content-Disposition", 
//            "attachment; filename=" + K2E(fileName) + ";");
//      res.setContentLength(Const.nullInt);
      
      downloadFile("grid.xls");
        
        
        BufferedOutputStream bos = null;
        try
        {
//            OutputStream te = res.getOutputStream();
            bos = new BufferedOutputStream(getResponse().getOutputStream());
            wb.write(bos);
            bos.flush();
            bos.close();
//            getResponse().setContentType("application/vnd.ms-excel");
//            getResponse().setCharacterEncoding("UTF-8");
//
//            String fileName = java.net.URLEncoder.encode("grid", "UTF-8");
//            fileName = fileNx, "attachment;filename="+fileName);
//            getResponse().setHeader("Cache-Control", "max-age=0");
//            wb.write(bos);  
//            bos.flush();
//            wb.close();
        }   
        finally 
        {
            if (bos != null)
                bos.close();
        }
    }
	
	public static final String KOR_CHARSET = "MS949"; //EUC-KR
	   public static final String ENG_CHARSET = "ISO-8859-1";
	   private String K2E(String korean) 
	   {
	        String english = null;
	        
	        if (korean == null ) {
	            return null;
	        }
	        
	        try { 
	            english = new String(korean.getBytes(KOR_CHARSET), ENG_CHARSET);
	        } catch (UnsupportedEncodingException e) {
	            english = new String(korean);
	        }

	        return english;
	    }
	   
	private void transport(InputStream in, OutputStream out)
	        throws IOException 
	        {
	                
	            BufferedInputStream bin = null;
	            BufferedOutputStream bos = null;
	            
	            try 
	            {
	                bin = new BufferedInputStream( in );
	                bos = new BufferedOutputStream( out );
	            
	                byte[] buf = new byte[2048]; //buffer size 2K.
	                int read = 0;
//	                while ((read = bin.read(buf)) != -1) 
//	                {
//	                    bos.write(buf,0,read);
//	                }

	                while ((read = bin.read()) != -1) {
	                    bos.write(read);
	                }
	                
	                bos.flush();
	                bin.close();
	                
	            }
	            finally 
	            {
	                bos.flush();
	                bin.close();
	            }        
	        }
	
	private void exportXLSfromMaps(List data,String[] headsName) throws IOException
	{
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet");
        
        //스타일 객체 생성
        HSSFCellStyle style = wb.createCellStyle(); 

        HSSFPalette palette = wb.getCustomPalette();
        HSSFColor hssfColor = null;
        hssfColor= palette.findSimilarColor(204, 255, 204);

        
        //셀 스타일
//        style.setFillBackgroundColor();
        style.setFillBackgroundColor(hssfColor.getIndex());
	    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    style.setAlignment(HorizontalAlignment.CENTER);
	    style.setVerticalAlignment(VerticalAlignment.CENTER);
	    //보더 스타일
	    style.setBorderBottom(BorderStyle.THIN);

        //폰트 객체 생성
        HSSFFont font = wb.createFont(); 
        
        font.setFontHeightInPoints((short)10); //폰트 크기
        font.setBold(true);

        style.setFont(font); 

        // Header Set
        HSSFRow headerRow = sheet.createRow(0);
        
        //셀 높이 설정
        headerRow.setHeight((short)512);

        for (int i=0; i<headsName.length; i++)
        {
            HSSFCell cell = headerRow.createCell(i);
            
            cell.setCellStyle(style);
            cell.setCellValue(headsName[i]);
            
            sheet.autoSizeColumn((short)i); //셀 사이즈(width) 자동 조정
        }

        for (int i=0,len=data.size();i<len;i++)
        {
            HSSFRow dataRow = sheet.createRow(i+1);
            
            Map record=(Map)data.get(i);
            Object[] rowObj = getDisplayData(record);
            
            for (int j=0; j<rowObj.length; j++)
            {
                HSSFCell cell = dataRow.createCell(j);  
                cell.setCellValue(rowObj[j].toString());

                if(rowObj[j].toString().length() > 35)
                {
                    sheet.setColumnWidth(j, 6000);
                }
            }
            
        }
        
               
//        downloadFile("grid.xls");
        
        BufferedOutputStream bos = null;
        try
        {
            bos = new BufferedOutputStream(getResponse().getOutputStream());
//            wb.write(bos);
            
            getResponse().setContentType("application/vnd.ms-excel");
            getResponse().setCharacterEncoding("UTF-8");

            String fileName = java.net.URLEncoder.encode("grid", "UTF-8");
            fileName = fileName +".xls";

            getResponse().setHeader("Content-Disposition", "attachment;filename="+fileName);
            getResponse().setHeader("Cache-Control", "max-age=0");
            wb.write(bos);
            wb.close();
        }
        finally 
        {
            bos.close();
        }
	}
	
    public void exportCSVfromMaps(List data) throws IOException
    {
        List cols = this.getDisplayColumnInfo();
        int len=cols.size();
        
        String[] headsName=new String[len] ;
        for (int i=0;i<len;i++)
        {
            ColumnInfo colInfo =(ColumnInfo)cols.get(i);
            headsName[i]=colInfo.getHeader();               
        }

        exportCSVfromMaps(data,headsName);
    }
    
    public void exportCSVfromMaps(List data,String[] headsName) throws IOException
    {
        BufferedOutputStream bos = null;
            
        try 
        {
//            downloadFile("grid.csv");
            
            bos = new BufferedOutputStream(getResponse().getOutputStream());
        
            String headData="";
            for (int j=0; j<headsName.length; j++)
            {
                headData = headData + headsName[j];
                if ((j+1)!=headsName.length) headData = headData + ",";
                else headData = headData + "\n";
            }
                
            bos.write(headData.getBytes());
            
            for (int i=0,len=data.size();i<len;i++)
            {
                Map record=(Map)data.get(i);
                Object[] recordArray = getDisplayData(record);
                String rowData="";
                for (int j=0; j<recordArray.length; j++)
                {
                    rowData = rowData + recordArray[j];
                    if ((j+1)!=recordArray.length) rowData = rowData + ",";
                    else rowData = rowData + "\n";
                }
                    
                bos.write(rowData.getBytes());
            }
            bos.flush();
        }
        finally 
        {
            bos.close();
        }     
    }
	
	public void printSaveResponseText(){
		printResponseText(getSaveResponseText());
	}

	public void printLoadResponseText(){
		printResponseText(getLoadResponseText());
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}


	public Map getParameters() {
		return parameters;
	}

	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	
	public List getSortInfo() {
		return sortInfo;
	}

	public void setSortInfo(List sortInfo) {
		this.sortInfo = sortInfo;
	}
	public Map getSingleSortInfo(){
		return sortInfo==null||sortInfo.size()<1?null:(Map)sortInfo.get(0);
	}
	public List getFilterInfo() {
		return filterInfo;
	}

	public void setFilterInfo(List filterInfo) {
		this.filterInfo = filterInfo;
	}
	public Map getSingleFilterInfo(){
		return filterInfo==null||filterInfo.size()<1?null:(Map)filterInfo.get(0);
	}
	public List getData() {
		return data;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public List getFieldsName() {
		return fieldsName;
	}

	public void setFieldsName(List fieldsName) {
		this.fieldsName = fieldsName;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getPageSize() {
		return getPageInfo().getPageSize();
	}

	public void setPageSize(int pageSize) {
		getPageInfo().setPageSize(pageSize);
	}

	public int getPageNum() {
		return getPageInfo().getPageNum();

	}

	public void setPageNum(int pageNum) {
		getPageInfo().setPageNum(pageNum );
	}

	public int getTotalRowNum() {
		return  getPageInfo().getTotalRowNum();
	}

	public void setTotalRowNum(int totalRowNum) {
		getPageInfo().setTotalRowNum(totalRowNum) ;
	}

	public int getTotalPageNum() {
		return getPageInfo().getTotalPageNum();
	}

	public void setTotalPageNum(int totalPageNum) {
		getPageInfo().setTotalPageNum(totalPageNum);
	}

	public int getStartRowNum() {
		return getPageInfo().getStartRowNum();
	}

	public void setStartRowNum(int startRowNum) {
		getPageInfo().setStartRowNum(startRowNum );
	}

	public int getEndRowNum() {
		return getPageInfo().getEndRowNum();
	}

	public void setEndRowNum(int endRowNum) {
		getPageInfo().setEndRowNum(endRowNum );
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
	    try{
	        request.setCharacterEncoding("UTF-8");
	    }
	    catch(Exception ex){}
		this.request = request;
		setParameterMap(request.getParameterMap());
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public JSONObject getJsonObject() {
		return jsonObject;
	}
	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
	public Class getDataBeanClass() {
		return dataBeanClass;
	}
	public void setDataBeanClass(Class dataBeanClass) {
		this.dataBeanClass = dataBeanClass;
	}
	public JSONArray getJsonData() {
		return jsonData;
	}
	public void setJsonData(JSONArray jsonData) {
		this.jsonData = jsonData;
	}

	public Map getParameterMap() {
		return parameterMap;
	}

	public void setParameterMap(Map parameterMap) {
		this.parameterMap = parameterMap;
	}
	public static int getInt(Object i){
		return getInt(i,-1);
	}
	public static int getInt(Object i,int defaultI){
		try {
			if (i!=null){
				return Integer.parseInt(String.valueOf(i));
			}
		} catch (Exception e) {	}
		return defaultI;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public GridInfo getGridInfo() {
		return gridInfo;
	}

	public void setGridInfo(GridInfo gridInfo) {
		this.gridInfo = gridInfo;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public List getColumnInfo() {
		return columnInfo;
	}

	public void setColumnInfo(List columnInfo) {
		this.columnInfo = columnInfo;
	}

	/**
	 * 현재 화면에 보이는 View Data를 column 정보에 맞게 출력한다.<br>
	 * DB에서 조회시에는 row_edit 값이 넘어오지 않는다.<br>
	 * (parameter로 browser에서 넘어 올때는 row_edit가 전송됨)<br>
	 * DB 조회시[isParam:false] 해더정보에는 row_edit 도 같이 넘오기 때문에 첫번째에 row_edit를 보정해줘야한다.<br>
	 * @author  javaworker
	 * @version $Id: GridServerHandler.java,v 1.1 2013/08/30 09:12:56 javaworker Exp $
	 * @since   1.0
	 * 
	 * @param map : data 값
	 * @return
	 */
    public Object[] getDisplayData(Map map)
    {
        Object[] retObjs = new Object[this.getDisplayColumnInfo().size()];
        
        Object[] objs = (map.values().toArray());
        
        for (int colIdx=0,retIdx=0,objIdx=0; colIdx<columnInfo.size(); colIdx++,objIdx++)
        {
            ColumnInfo columnInfoObj = (ColumnInfo)columnInfo.get(colIdx);
            
            // DB에서 조회 한 경우 row_edit 해더 무시
            if (!this.isParam && "row_edit".equals(columnInfoObj.getId()))
            {
                objIdx--;
                continue;
            }
            
            // hidden 컬럼이 아닌경우만 셋팅한다.
            if (!columnInfoObj.isHidden())
            {
                // Data 의 index보다 Column index 가 크다면 data가 없으므로 빈문자열 셋팅 
                if(objs.length<=objIdx)
                {
                    retObjs[retIdx]="";
                }
                else
                {
                    //retObjs[retIdx]=objs[objIdx]==null?"":objs[objIdx];
                    retObjs[retIdx]=getDate(objs[objIdx], columnInfoObj);
                }
                retIdx++;
            }
        }
        
        return retObjs;
    }

    /**
     * Excel에 보여지는 data type에 맞게 추출
     * @author  javaworker
     * @version $Id: GridServerHandler.java,v 1.1 2013/08/30 09:12:56 javaworker Exp $
     * @since   1.0
     * 
     * @param object
     * @param columnInfoObj 
     * @return
     */
    private Object getDate(Object object, ColumnInfo columnInfoObj)
    {
        if (object == null) return "";
        
        // parameter로 전송된 경우는 data type 별로 숫자, 날짜 형식이 지정되었다. 따로 변환 필요없음
        if (this.isParam) return object.toString();
        
        String dataValue = object.toString();
        if ("date".equals(columnInfoObj.getType()))
        {
            if (dataValue.length() == 8) dataValue = CommonUtil.dataToDate(dataValue);
        }
        else if ("time".equals(columnInfoObj.getType()))
        {
            if (dataValue.length() == 4) dataValue = CommonUtil.convertTime(dataValue);
        }
        else if ("float".equals(columnInfoObj.getType()) || "int".equals(columnInfoObj.getType()))
        {
            dataValue = CommonUtil.convertMoney(dataValue);
        }
        
        return dataValue;
    }
}
