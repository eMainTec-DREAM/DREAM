package dream.asset.rpt.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.rpt.dao.AssetRptWorkHistListDAO;
import dream.asset.rpt.dto.AssetRptWorkHistCommonDTO;

/**
 * �����̷�(����) - List DAO implements
 * @author js.lee
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="assetRptWorkHistListDAOTarget"
 * @spring.txbn id="assetRptWorkHistListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetRptWorkHistListDAOOraImpl extends BaseJdbcDaoSupportOra implements AssetRptWorkHistListDAO
{
	public List findRptWorkHistList(AssetRptWorkHistCommonDTO assetRptWorkHistCommonDTO, User user) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();
       
        query.append("  SELECT 		''                                                                                      						AS ISDELCHECK	");
        query.append("            , ''                                                                                      						AS SEQNO		");
        query.append("            , x.ITEM_NO                                                                        								AS ITEMNO		");
        query.append("            , y.DESCRIPTION                                                           										AS EQUIPNAME	");
        query.append("            , (SELECT FULL_DESC FROM TAEQLOC WHERE EQLOC_ID = y.EQLOC_ID)                             						AS EQLOCDESC	");
        query.append("            , (SELECT FULL_DESC FROM TAEQCTG WHERE EQCTG_ID = y.EQCTG_ID)                             						AS EQTYPE		");
        query.append("            , to_char(to_date(x.WKOR_DATE,'yyyymmdd'), 'yyyy-mm-dd')                                     						AS WKORDATE		");
        query.append("            , SFACODE_TO_DESC(x.WO_TYPE, 'WO_TYPE','SYS','','"+user.getLangId()+"')                   						AS WOTYPE		");
        query.append("            , x.DESCRIPTION                                                                           						AS DESCRIPTION	");
        query.append("            , x.PERFORM                                                                               						AS PERFORM		");
        query.append("            , to_char(to_date(x.start_date,'yyyymmdd'),'yyyy-mm-dd')||' '||to_char(to_date(x.START_TIME,'hh24mi'),'HH24:mm')	AS STARTTIME	");
        query.append("            , to_char(to_date(x.END_date,'yyyymmdd'),'yyyy-mm-dd')||' '||to_char(to_date(x.END_TIME,'hh24mi'),'HH24:mm')		AS ENDTIME		");
        query.append("            , x.WORK_TIME                                                                             						AS WORKTIME		");
        query.append("            , (SELECT DESCRIPTION FROM TADEPT WHERE DEPT_ID = x.DEPT_ID AND COMP_NO = x.COMP_NO)								AS DEPTDESC		");
        query.append("			  , NVL(x.EMP_NAME, (SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = x.emp_id AND COMP_NO = x.COMP_NO)) 			AS EMPNAME		");
        query.append("            , x.EQASMB_DESC                                                                             						AS EQASMBDESC	");
        query.append("            , x.CA_DESC                   																					AS CADESC		");
        query.append("            , x.RE_DESC                   																					AS REDESC		");
        query.append("            , x.VENDOR_NAME                                                                           						AS VENDORNAME	");
        query.append("            , x.TOT_AMT                                                                               						AS TOTAMT		");
        query.append("            , (SELECT WO_NO FROM TAWORKORDER WHERE WKOR_ID = x.WKOR_ID AND COMP_NO = x.COMP_NO)								AS WONO			");
        query.append("     		  , x.EQHISTORY_ID 																									AS EQHISTORYID  ");
        query.append("            , x.eqhist_gen_type                                                                                                   AS EQHISTGENTYPEID  	");
        query.append("            , SFACODE_TO_DESC(x.eqhist_gen_type, 'EQHIST_GEN_TYPE','SYS', '"+ user.getCompNo() +"', '"+user.getLangId()+"')   AS EQHISTGENTYPEDESC    ");
        query.append("  FROM TAEQHISTORY x LEFT OUTER JOIN TAEQUIPMENT y																							");
        query.append("  ON y.ITEM_NO = x.ITEM_NO AND y.IS_LAST_VERSION = 'Y' AND y.COMP_NO = x.COMP_NO																");
        query.append("	WHERE  1=1																																	");
        query.append(this.getWhere(assetRptWorkHistCommonDTO, user));

        query.getOrderByQuery("x.eqhistory_id", "x.eqhistory_id desc", assetRptWorkHistCommonDTO.getOrderBy(), assetRptWorkHistCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assetRptWorkHistCommonDTO.getIsLoadMaxCount(), assetRptWorkHistCommonDTO.getFirstRow()));
        } 

	private String getWhere(AssetRptWorkHistCommonDTO assetRptWorkHistCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        if(!"".equals(assetRptWorkHistCommonDTO.getEqHistoryId())){
        	query.getAndQuery("x.eqhistory_id", assetRptWorkHistCommonDTO.getEqHistoryId());
        	return query.toString();
        }
        
        // �۾�����
        query.getAndDateQuery("x.wkor_date", assetRptWorkHistCommonDTO.getFilterStartDate(), assetRptWorkHistCommonDTO.getFilterEndDate());
        
        // ����
        query.getCodeLikeQuery("y.equip_id", "(SELECT a.description FROM TAEQUIPMENT a WHERE a.equip_id = y.equip_id AND a.comp_no='"+user.getCompNo()+"')", 
        		assetRptWorkHistCommonDTO.getFilterEquipId(), assetRptWorkHistCommonDTO.getFilterEquipDesc());
        
        // ������ġ
        query.getEqLocLevelQuery("y.eqloc_id", assetRptWorkHistCommonDTO.getFilterEqLocId(), assetRptWorkHistCommonDTO.getFilterEqLocDesc(), user.getCompNo());
        
        // ��������
        query.getEqCtgLevelQuery("y.eqctg_id", assetRptWorkHistCommonDTO.getFilterEqCtgId(), assetRptWorkHistCommonDTO.getFilterEqCtgDesc(), user.getCompNo());
        
        // ���μ�
        query.getDeptLevelQuery("x.dept_id", assetRptWorkHistCommonDTO.getFilterDeptId(), assetRptWorkHistCommonDTO.getFilterDeptDesc(), user.getCompNo());
        
        // �����
        query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no='"+user.getCompNo()+"')", 
        		assetRptWorkHistCommonDTO.getFilterEmpId(), assetRptWorkHistCommonDTO.getFilterEmpDesc());
        
        // �������
        query.getLikeQuery("SFACODE_TO_DESC(x.CA_DESC, 'CA_DESC','SYS','','"+user.getLangId()+"')", assetRptWorkHistCommonDTO.getFilterCaCdDesc());
        
        // ������ġ
        query.getLikeQuery("SFACODE_TO_DESC(x.RE_DESC, 'RE_DESC','SYS','','"+user.getLangId()+"')", assetRptWorkHistCommonDTO.getFilterReCdDesc());
        //�۾�����
    	query.getSysCdQuery("x.wo_type", assetRptWorkHistCommonDTO.getFilterWoTypeId(), assetRptWorkHistCommonDTO.getFilterWoTypeDesc(), "WO_TYPE", user.getCompNo(),user.getLangId());
      
        // �۾���
        query.getLikeQuery("x.DESCRIPTION", assetRptWorkHistCommonDTO.getFilterWkOrDesc());
        
        // �����̷¹߻�����
    	query.getSysCdQuery("x.eqhist_gen_type", assetRptWorkHistCommonDTO.getFilterEqHistGenTypeId(), assetRptWorkHistCommonDTO.getFilterEqHistGenTypeDesc(), "EQHIST_GEN_TYPE", user.getCompNo(), user.getLangId());
        
        // W/O#
        query.getLikeQuery("(SELECT WO_NO FROM TAWORKORDER WHERE WKOR_ID = x.WKOR_ID AND COMP_NO = x.COMP_NO)", assetRptWorkHistCommonDTO.getFilterWoNo());
        
        
    	return query.toString();
    }

    public int deleteRptWorkHistList(String id, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

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
        QueryBuffer query = new QueryBuffer();
        
        query.append("  SELECT count(1)										");
        query.append("  FROM TAEQHISTORY x LEFT OUTER JOIN TAEQUIPMENT y	");
        query.append("  ON y.ITEM_NO = x.ITEM_NO 							");
        query.append("   AND y.IS_LAST_VERSION = 'Y'						");
        query.append("   AND y.COMP_NO = x.COMP_NO							");
        query.append("	WHERE  1=1											");
    	query.append(this.getWhere(assetRptWorkHistCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}