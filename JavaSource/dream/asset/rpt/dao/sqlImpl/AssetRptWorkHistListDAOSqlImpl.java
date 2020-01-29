package dream.asset.rpt.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.asset.rpt.dao.AssetRptWorkHistListDAO;
import dream.asset.rpt.dto.AssetRptWorkHistCommonDTO;

/**
 * 설비이력(과거) - List DAO implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="assetRptWorkHistListDAOTarget"
 * @spring.txbn id="assetRptWorkHistListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetRptWorkHistListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AssetRptWorkHistListDAO
{
	public List findRptWorkHistList(AssetRptWorkHistCommonDTO assetRptWorkHistCommonDTO, User user) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;              	");
        query.append("  SELECT    ''                                                                                                                    AS ISDELCHECK   		");
        query.append("            , ''                                                                                                                  AS SEQNO        		");
        query.append("            , x.ITEM_NO                                                                                                           AS ITEMNO       		");
        query.append("            , y.DESCRIPTION                                                                                                       AS EQUIPNAME    		");
        query.append("            , (SELECT FULL_DESC FROM TAEQLOC WHERE EQLOC_ID = y.EQLOC_ID)                                                         AS EQLOCDESC    		");
        query.append("            , (SELECT FULL_DESC FROM TAEQCTG WHERE EQCTG_ID = y.EQCTG_ID)                                                         AS EQTYPE       		");
        query.append("            , CONVERT(nvarchar(10), convert(DATETIME,x.WKOR_DATE), 120)                                                            AS WKORDATE     		");
        query.append("            , dbo.SFACODE_TO_DESC(x.WO_TYPE, 'WO_TYPE','SYS','','"+user.getLangId()+"')                                           AS WOTYPE       		");
        query.append("            , x.DESCRIPTION                                                                                                       AS DESCRIPTION  		");
        query.append("            , x.PERFORM                                                                                                           AS PERFORM      		");
        query.append("            , SUBSTRING(convert(nvarchar(20), x.start_date), 1, 4) + '-' + SUBSTRING(convert(nvarchar(20), x.start_date), 5, 2) + '-' + SUBSTRING(convert(nvarchar(20), x.start_date), 7, 2) 		");
        query.append("            + ' ' + SUBSTRING(convert(nvarchar(20), x.start_time), 1, 2) + ':' + SUBSTRING(convert(nvarchar(20), x.start_time), 3, 2)  AS STARTTIME		");
        query.append("               , SUBSTRING(convert(nvarchar(20), x.end_date), 1, 4) + '-' + SUBSTRING(convert(nvarchar(20), x.end_date), 5, 2) + '-' + SUBSTRING(convert(nvarchar(20), x.end_date), 7, 2) 		");
        query.append("            + ' ' + SUBSTRING(convert(nvarchar(20), x.end_time), 1, 2) + ':' + SUBSTRING(convert(nvarchar(20), x.end_time), 3, 2)  AS ENDTIME		");
        query.append("            , x.WORK_TIME                                                                                                         AS WORKTIME     		");
        query.append("            , (SELECT DESCRIPTION FROM TADEPT WHERE DEPT_ID = x.DEPT_ID AND COMP_NO = x.COMP_NO)                                     AS DEPTDESC     		");
        query.append("            , ISNULL(x.EMP_NAME, (SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = x.emp_id AND COMP_NO = x.COMP_NO))                AS EMPNAME        		");
        query.append("            , x.EQASMB_DESC                                                                                                       AS EQASMBDESC   		");
        query.append("            , x.CA_DESC                                                                                                           AS CADESC       		");
        query.append("            , x.RE_DESC                                                                                                           AS REDESC       		");
        query.append("            , x.VENDOR_NAME                                                                                                       AS VENDORNAME   		");
        query.append("            , x.TOT_AMT                                                                                                           AS TOTAMT       		");
        query.append("            , (SELECT WO_NO FROM TAWORKORDER WHERE WKOR_ID = x.WKOR_ID AND COMP_NO = x.COMP_NO)                                   AS WONO         		");
        query.append("            , x.EQHISTORY_ID                                                                                                      AS EQHISTORYID  		");
        query.append("            , x.eqhist_gen_type                                                                                                   AS EQHISTGENTYPEID  	");
        query.append("            , dbo.SFACODE_TO_DESC(x.eqhist_gen_type, 'EQHIST_GEN_TYPE','SYS', '"+ user.getCompNo() +"', '"+user.getLangId()+"')   AS EQHISTGENTYPEDESC    ");
        query.append("  FROM TAEQHISTORY x LEFT OUTER JOIN TAEQUIPMENT y                                                                                                		");
        query.append("  ON y.ITEM_NO = x.ITEM_NO AND y.IS_LAST_VERSION = 'Y' AND y.COMP_NO = x.COMP_NO                                                                  		");
        query.append("  WHERE  1=1                                                                                                                                         		");
        query.append(this.getWhere(assetRptWorkHistCommonDTO, user));
        
        query.getOrderByQuery("x.eqhistory_id", "x.eqhistory_id desc", assetRptWorkHistCommonDTO.getOrderBy(), assetRptWorkHistCommonDTO.getDirection());
        
    	return getJdbcTemplate().queryForList(query.toString(assetRptWorkHistCommonDTO.getIsLoadMaxCount(), assetRptWorkHistCommonDTO.getFirstRow()));
    } 

	private String getWhere(AssetRptWorkHistCommonDTO assetRptWorkHistCommonDTO, User user)
    {        
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        if(!"".equals(assetRptWorkHistCommonDTO.getEqHistoryId())){
        	query.getAndQuery("x.eqhistory_id", assetRptWorkHistCommonDTO.getEqHistoryId());
        	return query.toString();
        }
        
        // 작업일자
        query.getAndDateQuery("x.wkor_date", assetRptWorkHistCommonDTO.getFilterStartDate(), assetRptWorkHistCommonDTO.getFilterEndDate());
        
        // 설비
        query.getCodeLikeQuery("y.equip_id", "(SELECT a.description FROM TAEQUIPMENT a WHERE a.equip_id = y.equip_id AND a.comp_no='"+user.getCompNo()+"')", 
        		assetRptWorkHistCommonDTO.getFilterEquipId(), assetRptWorkHistCommonDTO.getFilterEquipDesc());
        
        // 설비위치
        query.getEqLocLevelQuery("y.eqloc_id", assetRptWorkHistCommonDTO.getFilterEqLocId(), assetRptWorkHistCommonDTO.getFilterEqLocDesc(), user.getCompNo());
        
        // 설비종류
        query.getEqCtgLevelQuery("y.eqctg_id", assetRptWorkHistCommonDTO.getFilterEqCtgId(), assetRptWorkHistCommonDTO.getFilterEqCtgDesc(), user.getCompNo());
        
        // 담당부서
        query.getDeptLevelQuery("x.dept_id", assetRptWorkHistCommonDTO.getFilterDeptId(), assetRptWorkHistCommonDTO.getFilterDeptDesc(), user.getCompNo());
        
        // 담당자
        query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no='"+user.getCompNo()+"')", 
        		assetRptWorkHistCommonDTO.getFilterEmpId(), assetRptWorkHistCommonDTO.getFilterEmpDesc());
        
        // 고장원인
        query.getLikeQuery("dbo.SFACODE_TO_DESC(x.CA_DESC, 'CA_DESC','SYS','','"+user.getLangId()+"')", assetRptWorkHistCommonDTO.getFilterCaCdDesc());
        
        // 고장조치
        query.getLikeQuery("dbo.SFACODE_TO_DESC(x.RE_DESC, 'RE_DESC','SYS','','"+user.getLangId()+"')", assetRptWorkHistCommonDTO.getFilterReCdDesc());
        
        // 작업명
        query.getLikeQuery("x.DESCRIPTION", assetRptWorkHistCommonDTO.getFilterWkOrDesc());
        
        //작업종류
    	query.getSysCdQuery("x.wo_type", assetRptWorkHistCommonDTO.getFilterWoTypeId(), assetRptWorkHistCommonDTO.getFilterWoTypeDesc(), "WO_TYPE", user.getCompNo(),user.getLangId());
    	
    	// 설비이력발생구분
    	query.getSysCdQuery("x.eqhist_gen_type", assetRptWorkHistCommonDTO.getFilterEqHistGenTypeId(), assetRptWorkHistCommonDTO.getFilterEqHistGenTypeDesc(), "EQHIST_GEN_TYPE", user.getCompNo(), user.getLangId());
    	
        // W/O#
        query.getLikeQuery("(SELECT WO_NO FROM TAWORKORDER WHERE WKOR_ID = x.WKOR_ID AND COMP_NO = x.COMP_NO)", assetRptWorkHistCommonDTO.getFilterWoNo());
        
    	return query.toString();
    }

    public int deleteRptWorkHistList(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE FROM TAEQHISTORY		");
        query.append("WHERE  comp_no 		= ?		");
        query.append("  AND  eqhistory_id  	= ?		");
        query.append("  AND  eqhist_gen_type = ?	");
        
        Object[] objects = new Object[] {   
                 user.getCompNo()
               , id
               , "MANUAL"
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    public String findTotalCount(AssetRptWorkHistCommonDTO assetRptWorkHistCommonDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;     ");
        query.append("  SELECT count(1)										");
        query.append("  FROM TAEQHISTORY x LEFT OUTER JOIN TAEQUIPMENT y	");
        query.append("  ON y.ITEM_NO = x.ITEM_NO 							");
        query.append("   AND y.IS_LAST_VERSION = 'Y'						");
        query.append("   AND y.COMP_NO = x.COMP_NO							");
        query.append("	WHERE  1=1											");
    	query.append(this.getWhere(assetRptWorkHistCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}