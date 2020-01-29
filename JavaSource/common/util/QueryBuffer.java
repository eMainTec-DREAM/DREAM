package common.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.bean.MwareConfig;

/**
 * StringBuffer
 * 
 * @author javaworker
 * @version $Id: QueryBuffer.java,v 1.6 2014/02/13 06:47:51 javaworker Exp $
 * @since 1.0
 */
public class QueryBuffer
{
    /**
     * Logger for this class
     */
    //private static final Logger logger = Logger.getLogger(QueryBuffer.class);

    private StringBuffer sbBuffer;

    public QueryBuffer()
    {
        sbBuffer = new StringBuffer();
    }

    public QueryBuffer(String sQuery)
    {
        sbBuffer = new StringBuffer(sQuery);
    }

    public QueryBuffer append(String sQuery)
    {
        sbBuffer.append(sQuery + "\n");
        return this;
    }
    
    
    public QueryBuffer setClear()
    {
        sbBuffer.setLength(0);
        return this;
    }
    
    
    public String toString()
    {
        // java --> DB
        return sbBuffer.toString();
    }
    
    public String toString(boolean isLoadMaxCount, String firstRow)
    {
        if (isLoadMaxCount)
        {
            return QueryBuffer.pagingQuery(this.toString(), Integer.parseInt(firstRow), Integer.parseInt(MwareConfig.getGridMaxLoadCount()));
        }
        else
        {
            return this.toString();
        }
    }
    
    public String toString(String loadCnt)
    {
    	return QueryBuffer.pagingQuery(this.toString(), 0, Integer.parseInt(loadCnt));
    }
    
    public String getMetaData()
    {
        // java 
        return sbBuffer.toString();
    }

    /**
     * Spring DAO 
     * 
     * @author javaworker
     * @version $Id: QueryBuffer.java,v 1.6 2014/02/13 06:47:51 javaworker Exp $
     * @since 1.0
     */
    public static String[][] toStringArray(List resultList)
    {

        String[][] sResults = new String[resultList.size()][];
        Iterator rowIter = resultList.iterator();
        Iterator colIter = null;
        Collection rowColl = null;
        int iColSize = 0;
        for (int i = 0; rowIter.hasNext(); i++)
        {
            rowColl = ((Map) rowIter.next()).values(); 
                                                           
            colIter = rowColl.iterator(); 
                                            
            iColSize = rowColl.size(); 

            sResults[i] = new String[iColSize];
            for (int j = 0; colIter.hasNext(); j++)
            {
                Object tempObj = colIter.next();
                sResults[i][j] = tempObj==null?"":tempObj.toString();
            }
        }
        return sResults;
    }


    public static String[] toStringSingleArray(List resultList)
    {
        String[] sResults = new String[resultList.size()];
        Iterator rowIter   = resultList.iterator();
        Iterator colIter   = null;
        Collection rowColl = null;
        Object tempObj     = null;
        
        for (int i = 0; rowIter.hasNext(); i++)
        {
            rowColl = ((Map) rowIter.next()).values();
            
            colIter = rowColl.iterator(); 
            
            if (colIter.hasNext()) 
            {
                tempObj = colIter.next();
                sResults[i] = tempObj==null?"":tempObj.toString();
            }
        }
        return sResults;
    }


    public static Vector toVector(List resultList)
    {
        Vector sResults = new Vector();
        Iterator rowIter   = resultList.iterator();
        Iterator colIter   = null;
        Collection rowColl = null;
        Object tempObj     = null;
        
        for (int i = 0; rowIter.hasNext(); i++)
        {
            rowColl = ((Map) rowIter.next()).values();
            
            colIter = rowColl.iterator(); 
            
            if (colIter.hasNext()) 
            {
                tempObj = colIter.next();
                sResults.add(i, tempObj.toString());
            }
        }
        return sResults;
    }
    
    public static String listToString(List resultList)
    {
        String result = "";
        
        if (resultList.size() > 0)
        {
            Map hashMap = (Map)resultList.get(0);
            Object[] objects = (hashMap.values()).toArray();
            
            result = objects[0]==null?"":objects[0].toString();
        }
        
        return result;
    }
    
    public static String pagingQuery(String query, int firstRow, int pageSize)
    {
        QueryBuffer pagingQuery = new QueryBuffer();
        
        int lastRow  = firstRow + pageSize;
//System.out.println("firstRow == > "+firstRow + "\t pageSize ==> " + pageSize + "\t currentPage == > " + currentPage + "\t lastRow == > " + lastRow);
        pagingQuery.append("SELECT *                                    ");
        pagingQuery.append("FROM  (                                     ");
        pagingQuery.append("       SELECT A.*, ROWNUM AS R              ");
        pagingQuery.append("       FROM  (                              ");
        pagingQuery.append(query);
        pagingQuery.append("              ) A                           ");
//        pagingQuery.append("       WHERE ROWNUM <= " + lastRow + "      ");
        pagingQuery.append("       ) B                                  ");
        pagingQuery.append("WHERE R > " + firstRow + "                  ");
        pagingQuery.append("  AND R <= " + lastRow + "                  ");
        
        return pagingQuery.getMetaData();
    }
    
    public static String [] singleRowToStringArray(List resultList)
    {
        String[] sResults = null;

        Iterator colIter = null;
        Map rowHash  = null;
        Object tempObj   = null;
        
        if (resultList.size() > 0)
        {
            rowHash = (Map)resultList.get(0);
            
            sResults = new String[rowHash.size()];
            
            colIter = (rowHash.values()).iterator(); 

            for (int i=0; colIter.hasNext(); i++) 
            {
                tempObj = colIter.next();
                sResults[i] = tempObj==null?"":tempObj.toString();   
            }
        }
        
        return sResults;
    }
    
  
    public static boolean haveData(List resultList)
    {
        boolean result = false;
        
        result = resultList.size()>0?true:false;

        return result;
    }
    
    private String validCheck(String columnValue)
    {
    	if (columnValue == null || "".equals(columnValue))
        {
            return null;            
        }
    	
    	columnValue = columnValue.replaceAll("'", "'||CHR(39)||'");
    	
    	if ("=".equals(columnValue.substring(0, 1)))
        {
    	    columnValue = columnValue.substring(1, columnValue.length());
            columnValue = " Like UPPER('"+columnValue +"%')"; 
        }
    	else if("^".equals(columnValue.substring(0, 1)))
    	{
    		columnValue = columnValue.substring(1, columnValue.length());
            columnValue = " = '"+columnValue +"'"; 
    	}
    	else if("-".equals(columnValue.substring(0, 1)))
        {
            columnValue = columnValue.substring(1, columnValue.length());
            columnValue = " <> '"+columnValue +"'"; 
        }
    	else
    	{
    	    if(columnValue.indexOf("_") > -1 || columnValue.indexOf("%") > -1)
            {
                String escapeLan = "`";
                
                if(columnValue.indexOf("`") > -1) escapeLan = "!";
                if(columnValue.indexOf("!") > -1) escapeLan = "^"; 
                
                columnValue = columnValue.replaceAll("_", escapeLan + "_");
                columnValue = columnValue.replaceAll("%", escapeLan + "%");
         
                columnValue = " LIKE UPPER('%"+columnValue +"%') ESCAPE '"+escapeLan+"'"; 

            }
            else //Escape 
            {
     
                columnValue = " LIKE UPPER('%"+columnValue +"%')"; 
            }
    	}
    	
    	return columnValue;
    }
    
    public void getAndQuery(String columnName, String columnValue)
    {
        
    	//this.getLikeQuery(columnName, columnValue);

    	if (columnValue == null || "".equals(columnValue) || columnValue.equals("null"))
        {
            return;            
        }

    	if(columnValue.indexOf("+") >= 0)
        {
            int cnt = 0;
            String[] colValArr = columnValue.split("\\+");
            this.append("AND (");
            for(String colVal : colValArr)
            {
                cnt++;
                if(colVal==null||"%%".equals(colVal)||"".equals(colVal)) continue;
                this.append(" UPPER("+columnName+")"+ validCheck("^"+colVal));
                if(cnt != colValArr.length) this.append(" OR ");
            }
            
            this.append(")");
        }
        else this.append("  AND  "+ columnName  + validCheck("^"+columnValue));
        
    }

    public void getAndKeyQuery(String columnName, String columnValue) {
    	
    	if (columnValue == null || "".equals(columnValue) || columnValue.equals("null"))
        {
    		this.append("  AND  1 = 2");
            return;            
        }
    	
    	getAndNumKeyQuery(columnName, columnValue);
    }
    
    
    /**
     * Key Column Search
     * @param columnName
     * @param columnValue
     */
    public void getAndNumKeyQuery(String columnName, String columnValue)
    {
        if (columnValue == null || "".equals(columnValue) || columnValue.equals("null"))
        {
            return;            
        }

        if(columnValue.indexOf("+") >= 0)
        {
            int cnt = 0;
            String[] colValArr = columnValue.split("\\+");
            this.append("AND (");
            for(String colVal : colValArr)
            {
                cnt++;
                if(colVal==null||"".equals(colVal)) continue;
                this.append("  " + columnName + "="+ colVal+"" );
                if(cnt != colValArr.length) this.append(" OR ");
            }
            
            this.append(")");
        }
        else this.append("  AND  " + columnName + "="+ columnValue+"" );
    }
    
    public void getStringEqualQuery(String columnName, String columnValue)
    {
    	if (columnValue == null || "".equals(columnValue) || columnValue.equals("null"))
    	{
    		return;            
    	}
    	
    	this.append("  AND  " + columnName + "='"+ columnValue+"'" );
    	
    }
    
    /**
     * For Multi Column OR Like Search 
     * @param columnNameArr
     * @param columnValue
     */
    public void getLikeQuery(String[] columnNameArr, String columnValue)
    {
    	if (columnValue == null || "".equals(columnValue))
    	{
    		return;            
    	}    

    	int colCnt = 0;
    	this.append("AND (");
    	for(String columnName : columnNameArr)
    	{
    		if(colCnt !=0) this.append(" OR ");
    		
	        if(columnValue.indexOf("*") >= 0)
	        {
	            String[] colValArr = columnValue.split("\\*");
	            
	            for(String colVal : colValArr)
	            {
	                if("".equals(colVal)) continue;
	                
	                this.append("UPPER(" + columnName + ")"+ validCheck(colVal));
	            }
	        }
	        else if(columnValue.indexOf("+") >= 0)
	        {
	            int cnt = 0;
	            String[] colValArr = columnValue.split("\\+");
	            this.append("AND (");
	            for(String colVal : colValArr)
	            {
	                cnt++;
	                if(colVal==null||"%%".equals(colVal)||"".equals(colVal)) continue;
	                this.append(" UPPER("+columnName+")"+ validCheck(colVal));
	                if(cnt != colValArr.length) this.append(" OR ");
	            }
	            
	            this.append(")");
	        }
	        else this.append("UPPER(" + columnName + ")"+ validCheck(columnValue));
    		
    		colCnt++;

    	}
    	this.append(")");
    }
    
    public void getLikeQuery(String columnName, String columnValue)
    {

        if (columnValue == null || "".equals(columnValue)|| "null".equals(columnValue))
        {
            return;            
        }    
        else if(columnValue.indexOf("*") >= 0)
        {
            String[] colValArr = columnValue.split("\\*");
            
            for(String colVal : colValArr)
            {
                if("".equals(colVal)) continue;
                
                this.append(" AND UPPER(" + columnName + ")"+ validCheck(colVal));
            }
        }
        else if(columnValue.indexOf("+") >= 0)
        {
            int cnt = 0;
            String[] colValArr = columnValue.split("\\+");
            this.append("AND (");
            for(String colVal : colValArr)
            {
                cnt++;
                if(colVal==null||"%%".equals(colVal)||"".equals(colVal)) continue;
                this.append(" UPPER("+columnName+")"+ validCheck(colVal));
                if(cnt != colValArr.length) this.append(" OR ");
            }
            
            this.append(")");
        }
        else this.append(" AND UPPER(" + columnName + ")"+ validCheck(columnValue));

    }
    
    public void getCodeLikeQuery(String codeColName, String codeDescCol, String codeValue,  String codeDescValue)
    {
        String columnValue  = "";
        String columnName   = "";
        
        if (codeValue == null || "".equals(codeValue) || "null".equals(codeValue))
        {
            columnName  = codeDescCol;
            columnValue = codeDescValue;
        }
        else 
        {
            columnName  = codeColName;
            columnValue = codeValue;
        }

        if((codeValue == null || "".equals(codeValue) || "null".equals(codeValue)) && (codeDescValue == null || "".equals(codeDescValue) || "null".equals(codeDescValue)))
        {
            return;
        }

        String columnVal = "";
        if(codeValue == null || "".equals(codeValue) || "null".equals(codeValue))
        {
            if(columnValue.indexOf("+") < 0)
            { 
                columnVal = validCheck(columnValue);
                if(columnVal==null||"%%".equals(columnVal)||"".equals(columnVal)||"null".equals(columnVal)) return;
                this.append("AND "+"UPPER("+columnName+")"+ columnVal);
            }
            else
            {
                int cnt = 0;
                String[] sysDescArr = columnValue.split("\\+");
                this.append("AND (");
                for(String sys : sysDescArr)
                {
                    cnt++;
                    if(sys==null||"%%".equals(sys)||"".equals(sys)||"null".equals(sys)) continue;
                    this.append(" UPPER("+columnName+")"+ validCheck(sys));
                    if(cnt != sysDescArr.length) this.append(" OR ");
                }
                
                this.append(")");
            }
            
//            this.append(" AND UPPER(" + columnName +")"+ validCheck(columnValue));            
        }
        else
        {
        	//this.append(" AND " + columnName + validCheck("^"+columnValue));
        	
        	if(columnValue.indexOf("+") >= 0)
            { 
        	    int cnt = 0;
                String[] sysDescArr = columnValue.split("\\+");
                this.append("AND (");
                for(String sys : sysDescArr)
                {
                    cnt++;
                    if(sys==null||"%%".equals(sys)||"".equals(sys)||"null".equals(sys)) continue;
                    this.append(columnName + validCheck("^"+sys));
                    if(cnt != sysDescArr.length) this.append(" OR ");
                }
                
                this.append(")");

            }
        	else if(columnValue.indexOf("-") >= 0)
            { 
                columnVal = validCheck(columnValue);
                this.append(" AND " + columnName + validCheck(columnValue));
            }
            else
            {
                columnVal = validCheck(columnValue);
                this.append(" AND " + columnName + validCheck("^"+columnValue));
            }
        }
    }
    
    public void getOrderByQuery(String keyStr, String oriStr , String colId, String dir)
    {
        if(CommonUtil.isNullCheck(keyStr)) keyStr = oriStr;
        
        if("des".equals(dir)) dir = "desc";
        
        if(colId == null || "".equals(colId)) this.append("ORDER BY " + oriStr);
        else 
        {
            
            if(colId.indexOf(",") > 0 ) 
            {
                String[] colIdArr = colId.split(",");
                String[] dirArr = dir.split(",");
                
                this.append("ORDER BY");
                int i = 0;
                int arrLen = colIdArr.length - 1 ;
                for(String cId : colIdArr)
                {
                    String p = i!=arrLen?",":"";
                    String dirDesc = "des".equals(dirArr[i])?"desc":dirArr[i];
                    
                    this.append(cId+" "+dirDesc+" "+p);
                    i++;
                }
                
                this.append(" ,"+keyStr);
            }
            else this.append("ORDER BY " + colId + " "+ dir +" ,"+ keyStr);
        }
        
    }
    /**
     * For Paging Page Sorting
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param colId
     * @param dir
     */
    public  void getOrderByQuery(String oriStr , String colId, String dir)
    {
        String keyStr = oriStr;
        
        getOrderByQuery(keyStr, oriStr , colId, dir);
    }
   
    public static String makeFilterString(String originData)
    {
        originData = originData.replaceAll("'", "''");
        
        if(originData != null && originData.indexOf("%") != -1)
        {
            originData = " LIKE '" + originData + "'";
        }
        else
        {
            originData = " = '" + originData + "'";
        }
        
        return originData;
    }
    
    public void getAndNumQuery(String columnName, String columnValue)
    {
        columnValue = CommonUtil.convertMoney(columnValue); // ï¿½ï¿½ï¿½ï¿½Æ® ï¿½ï¿½ , ï¿½ï¿½ ï¿½Ö´Ù¸ï¿½ , ï¿½ï¿½ ï¿½ï¿½ï¿½Ø´ï¿½.
        
        if (columnValue == null || "".equals(columnValue))
        {
            return;            
        }
        this.append("  AND  " + columnName + QueryBuffer.makeFilterString(columnValue));
    }
    
    
    public void getAndDateQuery(String columnName, String fromDate, String toDate)
    {
        fromDate = CommonUtil.dateToData(fromDate); // ï¿½ï¿½ï¿½ï¿½Æ® ï¿½ï¿½ - ï¿½ï¿½ ï¿½Ö´Ù¸ï¿½ - ï¿½ï¿½ ï¿½ï¿½ï¿½Ø´ï¿½.
        toDate   = CommonUtil.dateToData(toDate);
        
        if (fromDate != null && !"".equals(fromDate) && !"null".equals(fromDate))
        {
            this.append("  AND  " + columnName + " >= '" + fromDate + "'       ");
        }
        
        if (toDate != null && !"".equals(toDate) && !"null".equals(toDate))
        {
            this.append("  AND  " + columnName + " <= '" + toDate + "'          ");
        }        
    }

    
    public void getDateToDateQuery(String startColumnName, String endColumnName, String fromDate, String toDate)
    {
        fromDate = CommonUtil.dateToData(fromDate); // ï¿½ï¿½ï¿½ï¿½Æ® ï¿½ï¿½ - ï¿½ï¿½ ï¿½Ö´Ù¸ï¿½ - ï¿½ï¿½ ï¿½ï¿½ï¿½Ø´ï¿½.
        toDate   = CommonUtil.dateToData(toDate);
        
        /*
         * ï¿½ï¿½ï¿½Ë°Ë»ï¿½ ï¿½â°£ fromDate~toDate ï¿½È¿ï¿½ startColumnName~endColumnName ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½Ôµï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½È¸ï¿½Ñ´ï¿½.
         */
        if (toDate != null && !"".equals(toDate))
        {
            this.append("  AND  " + startColumnName + " <= '" + toDate + "'       ");
        }
        
        if ( fromDate != null && !"".equals( fromDate))
        {
            this.append("  AND  " + endColumnName + " >= '" + fromDate + "'          ");
        }        
    }
    
    public void getInequalityQuery(String columnName, String inequality, String qty)
    {
        if (inequality != null && !"".equals(inequality) && qty != null && !"".equals(qty))
        {
            this.append("  AND  " + columnName + inequality + "'" + qty + "'       ");
        }
    }
    
    public void getSubQuery(String mstrCol, String subMstrCol, String subCol, String subColValue,String subTable)
    {
        if (subColValue == null || "".equals(subColValue)) return;            

        this.append("AND  " + mstrCol +" IN (SELECT " + subMstrCol + "      ");
        this.append("                        FROM   " + subTable + "        ");
        this.append("                        WHERE  1=1                     ");
        this.getAndQuery(subCol, subColValue);
        this.append("                           )                           ");
    }
    
    public void getDeptLevelQuery(String colName, String deptId, String deptDesc, String compNo)
    {

        String deptCol = "";
        String deptStr = "";
        StringBuffer strbf = new StringBuffer();

        if(deptId==null||"".equals(deptId)||"null".equals(deptId))
        {
        	if(deptDesc==null||"%%".equals(deptDesc)||"".equals(deptDesc)||"null".equals(deptDesc)) return;
        	
            deptCol = "UPPER(description)";
            deptStr = validCheck(deptDesc);

            //Multi Description 
            if(deptDesc.indexOf("+") >= 0)
            {
                int cnt = 0;
                String[] colValArr = deptDesc.split("\\+");
                strbf.append("(");
                for(String colVal : colValArr)
                {
                    cnt++;
                    if(colVal==null||"%%".equals(colVal)||"".equals(colVal)) continue;
                    strbf.append(deptCol + validCheck(colVal));
                    if(cnt != colValArr.length) strbf.append(" OR ");
                }
                
                strbf.append(")");
                
            }
            else 
            {
                strbf.append(deptCol);
                strbf.append(deptStr);
            }
            
        }
        else
        {
            deptCol = "dept_id";
            deptStr = validCheck("^"+deptId);
            
            //Multi Description 
            if(deptId.indexOf("+") >= 0)
            {
                int cnt = 0;
                String[] colValArr = deptId.split("\\+");
                strbf.append("(");
                for(String colVal : colValArr)
                {
                    cnt++;
                    if(colVal==null||"%%".equals(colVal)||"".equals(colVal)) continue;
                    strbf.append(deptCol + validCheck("^"+colVal));
                    if(cnt != colValArr.length) strbf.append(" OR ");
                }
                
                strbf.append(")");
                
            }
            else 
            {
                strbf.append(deptCol);
                strbf.append(deptStr);
            }

        }

           
        if (colName.indexOf(".") > 0) {
            this.append("AND exists (   SELECT dept_id                                              ");
            this.append("                       FROM TADEPT                                         ");
            this.append("                       WHERE comp_no = '"+compNo+"'                        ");
            this.append("                       and dept_id = "+colName+"                           ");
            this.append("                       START wITH "+strbf.toString()+"                 ");
            this.append("                       CONNECT BY PRIOR dept_id = p_dept_id                ");
            this.append("                   )                                                       ");
        } 
        else
        {
            this.append("AND "+colName+" IN (   SELECT dept_id                          ");
            this.append("                       FROM TADEPT                             ");
            this.append("                       WHERE comp_no = '"+compNo+"'            ");
            this.append("                       START wITH "+strbf.toString()+"     ");
            this.append("                       CONNECT BY PRIOR dept_id = p_dept_id    ");
            this.append("                   )                                           ");
        }
        

    }
    
    public void getDeptLevelQuery(String colName, Map columnMap, String compNo){
    	if("".equals(columnMap) | "undefined".equals(columnMap) | columnMap == null ) return;
		
		String colStr = "";
		if(colName.indexOf(".") > 0) colStr = colName.split("\\.")[1];
    	else colStr = colName;
		
    	if(columnMap.containsKey(colStr))
    	{
    		String columnValue = String.valueOf(columnMap.get(colStr));
    		if("".equals(columnValue)) return;
    		
    		if (colName.indexOf(".") > 0) {
    			this.append("AND exists (	SELECT dept_id												");
    			this.append("						FROM TADEPT											");
    			this.append("						WHERE comp_no = '"+compNo+"'						");
    			this.append("						and dept_id = "+colName+"							");
    			this.append("						START wITH "+colStr+" "+validCheck("^"+columnValue));
    			this.append("						CONNECT BY PRIOR dept_id = p_dept_id				");
    			this.append("					)														");
			} else{
				this.append("AND "+colName+" IN (	SELECT dept_id										");
				this.append("						FROM TADEPT											");
				this.append("						WHERE comp_no = '"+compNo+"'						");
				this.append("						START wITH "+colStr+" "+validCheck("^"+columnValue));
				this.append("						CONNECT BY PRIOR dept_id = p_dept_id				");
				this.append("					)														");
			}
    		
    	}
    	else return;
    }
    
    public void getWkCtrLevelQuery(String colName, String wkctrId, String wkCtrDesc, String compNo){
    	String wkctrCol = "";
    	String wkctrStr = "";

    	if(wkctrId==null||"".equals(wkctrId)||"null".equals(wkctrId))
    	{
    		if(wkCtrDesc==null||"".equals(wkCtrDesc)||"null".equals(wkCtrDesc)) return;
    		
    		wkctrCol = "UPPER(description)";
    		wkctrStr = validCheck(wkCtrDesc);
    	}
    	else
    	{
    		wkctrCol = "wkctr_id";
    		wkctrStr = validCheck("^"+wkctrId);
    	}

    	
    	this.append("AND "+colName+" IN (	SELECT wkctr_id							");
    	this.append("						FROM TAWKCTR							");
    	this.append("						WHERE comp_no = '"+compNo+"'			");
    	this.append("						START wITH "+wkctrCol+" "+wkctrStr+"	");
    	this.append("						CONNECT BY PRIOR wkctr_id = p_wkctr_id	");
    	this.append("					)											");
    }
    
    public void getEqLocLevelQuery(String colName, String eqLocId, String eqLocDesc, String compNo)
    {
    	String locCol = "";
    	String locStr = "";

    	if(eqLocId==null||"".equals(eqLocId)||"null".equals(eqLocId))
    	{
    		if(eqLocDesc==null||"".equals(eqLocDesc)||"null".equals(eqLocDesc)) return;
    		
    		locCol = "UPPER(full_desc)";
    		locStr = validCheck(eqLocDesc);
    	}
    	else
    	{
    		locCol = "eqloc_id";
    		locStr = validCheck("^"+eqLocId);
    	}
    	
    	if(locStr==null||"%%".equals(locStr)||"".equals(locStr)) return;
		if (colName.indexOf(".") > 0) {
			this.append("AND exists (	SELECT eqloc_id									");
			this.append("						FROM TAEQLOC							");
			this.append("						WHERE comp_no = '"+compNo+"'			");
			this.append("						and eqloc_id = "+colName+"				");
			this.append("						START wITH "+locCol+" "+locStr+"		");
			this.append("						CONNECT BY PRIOR eqloc_id = p_eqloc_id	");
			this.append("					)											");
		} else{
	    	this.append("AND "+colName+" IN (	SELECT eqloc_id							");
	    	this.append("						FROM TAEQLOC							");
	    	this.append("						WHERE comp_no = '"+compNo+"'			");
	    	this.append("						START wITH "+locCol+""+locStr+"			");
	    	this.append("						CONNECT BY PRIOR eqloc_id = p_eqloc_id	");
	    	this.append("					)											");
		}

    }
    
    public void getEqLocLevelQuery(String colName, Map columnMap, String compNo){
    	if("".equals(columnMap) | "undefined".equals(columnMap) | columnMap == null ) return;
		
		String colStr = "";
		if(colName.indexOf(".") > 0) colStr = colName.split("\\.")[1];
    	else colStr = colName;
		
    	if(columnMap.containsKey(colStr))
    	{
    		String columnValue = String.valueOf(columnMap.get(colStr));
    		if("".equals(columnValue)) return;
    		
    		if (colName.indexOf(".") > 0) {
    		 	this.append("AND exists (	SELECT eqloc_id												");
    	    	this.append("						FROM TAEQLOC										");
    	    	this.append("						WHERE comp_no = '"+compNo+"'						");
    	    	this.append("						and eqloc_id = "+colName+"							");
    	    	this.append("						START wITH "+colStr+" "+validCheck("^"+columnValue));
    	    	this.append("						CONNECT BY PRIOR eqloc_id = p_eqloc_id				");
    	    	this.append("					)														");
			} else{
				this.append("AND "+colName+" IN (	SELECT eqloc_id										");
		    	this.append("						FROM TAEQLOC										");
		    	this.append("						WHERE comp_no = '"+compNo+"'						");
		    	this.append("						START wITH "+colStr+" "+validCheck("^"+columnValue));
		    	this.append("						CONNECT BY PRIOR eqloc_id = p_eqloc_id				");
		    	this.append("					)														");
			}
    		
    	}
    	else return;
    }
    
    public void getEqCtgLevelQuery(String colName, String eqCtgId, String eqCtgDesc, String compNo)
    {
    	String ctgCol = "";
    	String ctgStr = "";

    	if(eqCtgId==null||"".equals(eqCtgId)||"null".equals(eqCtgId))
    	{
    		if(eqCtgDesc==null||"".equals(eqCtgDesc)||"null".equals(eqCtgDesc)) return;
    		
    		ctgCol = "UPPER(full_desc)";
    		ctgStr = validCheck(eqCtgDesc);
    	}
    	else
    	{
    		ctgCol = "eqctg_id";
    		ctgStr = validCheck("^"+eqCtgId);
    	}
    	
    	if(ctgStr==null||"%%".equals(ctgStr)||"".equals(ctgStr)) return;
		if (colName.indexOf(".") > 0) {
			this.append("AND exists (	SELECT eqctg_id									");
			this.append("						FROM TAEQCTG							");
			this.append("						WHERE comp_no = '"+compNo+"'			");
			this.append("						and eqctg_id = "+colName+"				");
			this.append("						START wITH "+ctgCol+" "+ctgStr+"		");
			this.append("						CONNECT BY PRIOR eqctg_id = p_eqctg_id	");
			this.append("					)											");
		} else{
	    	this.append("AND "+colName+" IN (	SELECT eqctg_id							");
	    	this.append("						FROM TAEQCTG							");
	    	this.append("						WHERE comp_no = '"+compNo+"'			");
	    	this.append("						START wITH "+ctgCol+""+ctgStr+"		");
	    	this.append("						CONNECT BY PRIOR eqctg_id = p_eqctg_id	");
	    	this.append("					)											");
		}
    }
    
    public void getEqCtgLevelQuery(String colName, Map columnMap, String compNo){
    	if("".equals(columnMap) | "undefined".equals(columnMap) | columnMap == null ) return;
		
		String colStr = "";
		if(colName.indexOf(".") > 0) colStr = colName.split("\\.")[1];
    	else colStr = colName;
		
    	if(columnMap.containsKey(colStr))
    	{
    		String columnValue = String.valueOf(columnMap.get(colStr));
    		if("".equals(columnValue)) return;
    		
    		if (colName.indexOf(".") > 0) {
    		 	this.append("AND exists (	SELECT eqctg_id												");
    	    	this.append("						FROM TAEQCTG										");
    	    	this.append("						WHERE comp_no = '"+compNo+"'						");
    	    	this.append("						and eqctg_id = "+colName+"							");
    	    	this.append("						START wITH "+colStr+" "+validCheck("^"+columnValue));
    	    	this.append("						CONNECT BY PRIOR eqctg_id = p_eqctg_id				");
    	    	this.append("					)														");
			} else{
			 	this.append("AND "+colName+" IN (	SELECT eqctg_id										");
		    	this.append("						FROM TAEQCTG										");
		    	this.append("						WHERE comp_no = '"+compNo+"'						");
		    	this.append("						START wITH "+colStr+" "+validCheck("^"+columnValue));
		    	this.append("						CONNECT BY PRIOR eqctg_id = p_eqctg_id				");
		    	this.append("					)														");
			}
    		
    	}
    	else return;
    }
    
    public void getEqAssetQuery(String colName, String assetId, String assetDesc, String compNo)
    {
        String ctgCol = "";
        String ctgStr = "";

    	if(assetId==null||"".equals(assetId)||"null".equals(assetId))
        {
            ctgCol = "UPPER(description)";
            ctgStr = validCheck(assetDesc);
        }
        else
        {
            ctgCol = "asset_id";
            ctgStr = validCheck("^"+assetId);
        }
        
        if(ctgStr==null||"%%".equals(ctgStr)||"".equals(ctgStr)) return;
        
        this.append("AND "+colName+" IN (SELECT equip_id FROM TAEQASSET         ");
        this.append("                    WHERE  comp_no = '"+compNo+"'          ");
        this.append("                      AND  asset_id IN (SELECT asset_id	");
        this.append("						                 FROM   TAASSET     ");
        this.append("						                 WHERE  comp_no = '"+compNo+"' ");
        this.append("						                   AND  "+ctgCol+""+ctgStr+" ");
        this.append("                                        )                  ");
        this.append("					)										");
    }
    
    
    public void getPartsQuery(String colName, String partId, String partDesc, String compNo)
    {
        String ctgCol = "";
        String ctgStr = "";

    	if(partId==null||"".equals(partId)||"null".equals(partId))
        {
            ctgCol = "UPPER(full_desc)";
            ctgStr = validCheck(partDesc);
        }
        else
        {
            ctgCol = "part_id";
            ctgStr = validCheck("^"+partId);
        }
        
        if(ctgStr==null||"%%".equals(ctgStr)||"".equals(ctgStr)) return;
        
        this.append("AND "+colName+" IN (SELECT part_id FROM TAPARTS            ");
        this.append("                    WHERE  comp_no = '"+compNo+"'          ");
        this.append("                      AND  "+ctgCol+""+ctgStr+"            ");
        this.append("					)										");
    }
    
    /**
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param colName Ä®·³ÀÌ¸§
     * @param sysCode ÄÚµå
     * @param sysDesc ÄÚµå¸í
     * @param type ½Ã½ºÅÛÄÚµåÁ¾·ù
     * @param compNo È¸»ç¹øÈ£
     * @param lang ¾ð¾î
     * @param defVal ±âº»°ª 
     */
    public void getSysCdQuery(String colName, String sysCode, String sysDesc, String type, String compNo,String lang, String defVal)
    {
        if("".equals(sysCode) && "".equals(sysDesc))
        {
            sysCode = defVal;
        }
        
        getSysCdQuery(colName, sysCode, sysDesc, type, compNo,lang);
    }
    
    public void getSysCdQuery(String colName, String sysCode, String sysDesc, String type, String compNo,String lang)
    {
    	String sysCol = "";

		if(type.indexOf("||")<0){
			type = "'"+type+"'";
		}
		sysCol = "SFACODE_TO_DESC("+colName+","+type+",'SYS','"+compNo+"','"+lang+"')";

    	getCodeLikeQuery(colName, sysCol, sysCode, sysDesc);
    }
    
    public void getPlantCdQuery(String colName, String plantCode, String plantDesc, String compNo)
    {
    	String sysCol = "";
    	String sysStr = "";

    	if(plantCode==null||"".equals(plantCode)||"null".equals(plantCode))
    	{
    		
    		sysCol = "SFAPLANTTODESC("+compNo+","+colName+")";
    		sysStr = validCheck(plantDesc);
    	}
    	else
    	{
    		sysCol = colName;
    		sysStr = validCheck("^"+plantCode);
    	}
    	
    	if(sysStr==null||"%%".equals(sysStr)||"".equals(sysStr)) return;
    	this.append("AND "+"UPPER("+sysCol+")"+ sysStr);
    }
    
    
    
    public void getUsrCdQuery(String colName, String usrCode, String usrDesc, String type, String compNo, String lang)
    {
    	String usrCol = "";
    	String usrStr = "";

    	if(usrCode==null||"".equals(usrCode)||"null".equals(usrCode))
    	{
    		usrCol = "SFACODE_TO_DESC("+colName+",'"+type+"','USR','"+compNo+"','"+lang+"')";
    		usrStr = validCheck(usrDesc);
    	}
    	else
    	{
    		usrCol = colName;
    		usrStr = validCheck("^"+usrCode);
    	}
    	
    	if(usrStr==null||"%%".equals(usrStr)||"".equals(usrStr)) return;
    	this.append("AND "+usrCol+ usrStr);
    }
    
    public void getUsrCdLevelQuery(String colName, String usrCode, String usrDesc, String type, String compNo, String lang){
        String usrCol = "";
        String usrStr = "";
        
        if(usrCode==null||"".equals(usrCode)||"null".equals(usrCode))
        {
        	if(usrDesc==null||"".equals(usrDesc)||"null".equals(usrDesc)) return;
        	
            usrCol = "SFACODE_TO_DESC("+colName+",'"+type+"','USR','"+compNo+"','"+lang+"')";
            usrStr = validCheck(usrDesc);
        }
        else
        {
            usrCol = "cdusrd_no";
            usrStr = validCheck("^"+usrCode);
        }

        if(usrStr==null||"%%".equals(usrStr)||"".equals(usrStr)||"null".equals(usrStr)) return;
        
        if (colName.indexOf(".") > 0) {
            this.append("AND exists (   SELECT cdusrd_no                                            ");
            this.append("                       FROM TACDUSRD                                       ");
            this.append("                       WHERE comp_no = '"+compNo+"'                        ");
            this.append("                       and cdusrd_no = "+colName+"                         ");
            this.append("                       and cdusrm_id = (SELECT cdusrm_id FROM TACDUSRM     ");
            this.append("                                        WHERE comp_no = '"+compNo+"'       ");
            this.append("                                        AND dir_type = '"+type+"'          ");
            this.append("                                        )                                  ");
            this.append("                       START wITH "+usrCol+" "+usrStr+"                    ");
            this.append("                       CONNECT BY PRIOR cdusrd_id = p_cdusrd_id            ");
            this.append("                   )                                                       ");
        } else{
            this.append("AND "+colName+" IN (   SELECT cdusrd_no                                    ");
            this.append("                       FROM TACDUSRD                                       ");
            this.append("                       WHERE comp_no = '"+compNo+"'                        ");
            this.append("                       and cdusrm_id = (SELECT cdusrm_id FROM TACDUSRM     ");
            this.append("                                        WHERE comp_no = '"+compNo+"'       ");
            this.append("                                        AND dir_type = '"+type+"'          ");
            this.append("                                        )                                  ");
            this.append("                       START wITH "+usrCol+" "+usrStr+"                    ");
            this.append("                       CONNECT BY PRIOR cdusrd_id = p_cdusrd_id            ");
            this.append("                   )                                                       ");
        }
        

    }
    
    public void getUsrCdLevelQuery(String colName, Map columnMap, String compNo){
        if("".equals(columnMap) | "undefined".equals(columnMap) | columnMap == null ) return;
        
        String colStr = "";
        if(colName.indexOf(".") > 0) colStr = colName.split("\\.")[1];
        else colStr = colName;
        
        if(columnMap.containsKey(colStr) && columnMap.containsKey("DIR_TYPE"))
        {
            String columnValue = String.valueOf(columnMap.get(colStr));
            String columnType = String.valueOf(columnMap.get("DIR_TYPE"));
            if("".equals(columnValue) || "".equals(columnType)) return;
            
            if (colName.indexOf(".") > 0) {
                this.append("AND exists (   SELECT cdusrd_no                                            ");
                this.append("                       FROM TACDUSRD                                       ");
                this.append("                       WHERE comp_no = '"+compNo+"'                        ");
                this.append("                       and cdusrd_no = "+colName+"                         ");
                this.append("                       and cdusrm_id = (SELECT cdusrm_id FROM TACDUSRM     ");
                this.append("                                        WHERE comp_no = '"+compNo+"'       ");
                this.append("                                        AND dir_type = '"+columnType+"'    ");
                this.append("                                        )                                  ");
                this.append("                       START wITH "+colStr+" "+validCheck("^"+columnValue));
                this.append("                       CONNECT BY PRIOR cdusrd_id = p_cdusrd_id            ");
                this.append("                   )                                                       ");
            } else{
                this.append("AND "+colName+" IN (   SELECT cdusrd_no                                    ");
                this.append("                       FROM TACDUSRD                                       ");
                this.append("                       WHERE comp_no = '"+compNo+"'                        ");
                this.append("                       and cdusrm_id = (SELECT cdusrm_id FROM TACDUSRM     ");
                this.append("                                        WHERE comp_no = '"+compNo+"'       ");
                this.append("                                        AND dir_type = '"+columnType+"'    ");
                this.append("                                        )                                  ");
                this.append("                       START wITH "+colStr+" "+validCheck("^"+columnValue));
                this.append("                       CONNECT BY PRIOR cdusrd_id = p_cdusrd_id            ");
                this.append("                   )                                                       ");
            }
            
        }
        else return;
    }
    
    public void getDate(String _date, String _alias)
    {
        //this.append(" DECODE("+_date+",'','',SUBSTR("+_date+", 0, 4)||'-'||SUBSTR("+_date+", 5, 2)||'-'||SUBSTR("+_date+", 7, 2)) || '<img src=\"http://www.ddanzi.com/layouts/_common/images/icon-coin.png\" style=\"width:10px;display:inline;vertical-align:middle;\">' as " + _alias  );
        this.append(" DECODE("+_date+",'','',SUBSTR("+_date+", 0, 4)||'-'||SUBSTR("+_date+", 5, 2)||'-'||SUBSTR("+_date+", 7, 2)) as " + _alias  );
    }

    public void getTime(String _timeCol, String _alias)
    {
//    	this.append(" DECODE( LENGTH("+_timeCol+"),0,''");
//    	this.append(" ,4 ,SUBSTR("+_timeCol+", 0, 2)||':'||SUBSTR("+_timeCol+", 2, 2)");
//    	this.append(" ,3 ,SUBSTR("+_timeCol+", 0, 1)||':'||SUBSTR("+_timeCol+", 2, 2)");
//    	this.append(" ,2 ,"+_timeCol);
//    	this.append(" ,1 ,"+_timeCol);
//    	this.append(" ,SUBSTR("+_timeCol+", 0, LENGTH("+_timeCol+")-4)||':'||SUBSTR("+_timeCol+", LENGTH("+_timeCol+")-3, 2)||':'||SUBSTR("+_timeCol+", LENGTH("+_timeCol+")-1, 2)) as " + _alias  );
    	
    	this.append(" CASE 		");
//    	this.append(" WHEN LENGTH("+_timeCol+") = 0 THEN ''		");
    	this.append(" WHEN LENGTH("+_timeCol+") = 6 THEN SUBSTR("+_timeCol+", 0, 2)||':'||SUBSTR("+_timeCol+", 3, 2)||':'||SUBSTR("+_timeCol+", 5, 2)		");
    	this.append(" WHEN LENGTH("+_timeCol+") = 4 THEN SUBSTR("+_timeCol+", 0, 2)||':'||SUBSTR("+_timeCol+", 3, 2)		");
    	this.append("  WHEN LENGTH("+_timeCol+") = 3 THEN SUBSTR("+_timeCol+", 0, 1)||':'||SUBSTR("+_timeCol+", 3, 2)		");
    	this.append("   WHEN LENGTH("+_timeCol+") = 2 THEN ''		");
    	this.append("      WHEN LENGTH("+_timeCol+") = 1 THEN ''		");
//    	this.append("     ELSE SUBSTR("+_timeCol+", 0, LENGTH("+_timeCol+")-3)||':'||SUBSTR("+_timeCol+", LENGTH("+_timeCol+")-3, 2)||':'||SUBSTR("+_timeCol+", LENGTH("+_timeCol+")-1, 2) 		");
    	this.append("	  ELSE '' 	   ");
    	this.append(" END as 	"+_alias);	

    }
    
    public void getNumber(String _numCol, String _alias)
    {
        this.append("DECODE(INSTR("+_numCol+", '.', 1, 1), 0 , TO_CHAR("+_numCol+", '999G999G999G999') , TO_CHAR("+_numCol+", '999G999G999G999D99')) as "+_alias);
    }

    
	/**
	 * AutoComplete Column Control
	 * @param cols
	 * @param colMap
	 */
	public void getAcCols(String cols, Map<String, String> colMap) {
		
		String[] colarr = null; 
		if(cols.indexOf(",") > 0) colarr = cols.split(",");
		else
		{
			colarr = new String[1];
			colarr[0] = cols;
		}
		
		String columnStr = "";
		Map<String,String> addedMap = new HashMap<String, String>();
		
		for( String key : colMap.keySet() ){

			
            for(String colStr : colarr)
            {
            	if(colStr.indexOf(".") > 0) columnStr = colStr.split("\\.")[1];
            	else columnStr = colStr;
            	
            	columnStr = columnStr.toUpperCase();

            	if(columnStr.equals(colMap.get(key).toUpperCase()))
            	{
            		addedMap.put(key, colMap.get(key).toUpperCase());
            		this.append(","+colStr);
            	}
            	
            }

        }
		
		for(String colStr : colarr)
        {

			if(colStr.indexOf(".") > 0) columnStr = colStr.split("\\.")[1];
        	else columnStr = colStr;
        	
        	columnStr = columnStr.toUpperCase();

        	if(!addedMap.containsValue(columnStr)) this.append(","+colStr);

        }
		
	}

	public void getAcCols(Map<String,String> cols, Map<String, String> colMap) {

		String columnStr = "";
		Map<String,String> addedMap = new HashMap<String, String>();
		
		int cnt = 0;
		for( String key : colMap.keySet() ){
			if("DISPLAY".equals(key))continue;

			if(addedMap.containsKey(key)) continue;
				
            for(String colStr : cols.keySet())
            {
            	if(colStr.indexOf(".") > 0) columnStr = colStr.split("\\.")[1];
            	else columnStr = colStr;
            	
            	columnStr = columnStr.toUpperCase();

            	if(columnStr.equals(colMap.get(key).toUpperCase()))
            	{
            		addedMap.put(key, colMap.get(key).toUpperCase());
            		this.append(","+colStr+" AS \""+cols.get(colStr)+"\"");
            		
            		cnt++;
            	}
            }

        }


		for(String colStr : cols.keySet())
        {
			if(colStr.indexOf(".") > 0) columnStr = colStr.split("\\.")[1];
        	else columnStr = colStr;
        	
        	columnStr = columnStr.toUpperCase();

        	if(!addedMap.containsValue(columnStr)) this.append(","+colStr+" AS \""+cols.get(colStr)+"\"");

        }

	}
	
	public void getAndQuery(String columnName, Map columnMap)
    {
		if("".equals(columnMap) | "undefined".equals(columnMap) | columnMap == null ) return;
		
		String colStr = "";
		if(columnName.indexOf(".") > 0) colStr = columnName.split("\\.")[1];
    	else colStr = columnName;
		
    	//{"list_type":"EQ_STATUS","is_use":"Y"}
    	if(columnMap.containsKey(colStr))
    	{
    		String columnValue = String.valueOf(columnMap.get(colStr));
    		if("".equals(columnValue)) return;
     		
    		if(columnValue.indexOf("+") >= 0)
            {
                int cnt = 0;
                String[] colValArr = columnValue.split("\\+");
                this.append("AND (");
                for(String colVal : colValArr)
                {
                    cnt++;
                    if(colVal==null||"%%".equals(colVal)||"".equals(colVal)) continue;
                    this.append(" UPPER("+columnName+")"+ validCheck(colVal));
                    if(cnt != colValArr.length) this.append(" OR ");
                }
                
                this.append(")");
            }
    		else if("NULL".equals(columnValue.toUpperCase()))
    		{
    		    this.append("  AND  "+ columnName  + "  IS NULL  ");
    		}
            else this.append("  AND  "+ columnName  + validCheck("^"+columnValue));
    		
    		//this.append("  AND  "+ columnName  + validCheck("^"+columnMap.get(columnName)));	
    	}
    	else return;
    }
	
	public void getAndQuery(String columnName, Map columnMap, String isVal)
    {
        if("Y".equals(isVal)) 
        {
            String colStr = "";
            if(columnName.indexOf(".") > 0) colStr = columnName.split("\\.")[1];
            else colStr = columnName;
            
            if(columnMap.containsKey(colStr))
            {
                String columnValue = String.valueOf(columnMap.get(colStr));
                
                if("".equals(columnValue)) {
                    this.append("  AND 1 = 2  ");
                } else {
                    getAndQuery(columnName, columnMap);
                }
            }
        } 
        else getAndQuery(columnName, columnMap);
    }
}
