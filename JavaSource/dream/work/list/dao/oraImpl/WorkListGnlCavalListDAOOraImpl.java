package dream.work.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.list.dao.WorkListGnlCavalListDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업상세  - 검교정 - 측정값 목록 dao
 * @author  kim21017
 * @version $Id: WorkListGnlCavalListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="workListGnlCavalListDAOTarget"
 * @spring.txbn id="workListGnlCavalListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkListGnlCavalListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkListGnlCavalListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: WorkListGnlCavalListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param workListGnlCavalListDTO
     * @param loginUser
     * @return List
     */
    public List findCavalList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT										");
        query.append("       '' seqNo								");
        query.append("       ,'' isDelCheck							");
        query.append("       ,calib_point_type   CALIBPOINTTYPE     ");
        query.append("       ,SFACODE_TO_DESC(calib_point_type,'CALIB_POINT_TYPE','SYS','','"+loginUser.getLangId()+"') clibPointTypeDesc		");
//        query.append("       ,calib_point_type calibPointType		");
        query.append("       ,calib_point calibPoint				");
        query.append("       ,allow_value allowValue				");
        query.append("       ,asf_std_value asfStdValue				");
        query.append("       ,asf_cal_value asfCalValue				");
        query.append("       ,asf_diff_value asfDiffValue			");
        query.append("       ,asl_std_value aslStdValue				");
        query.append("       ,asl_cal_value aslCalValue				");
        query.append("       ,asl_diff_value aslDiffValue			");
        query.append("       ,ord_no ordNo							");
        query.append("       ,remark								");
        query.append("       ,wocalibgnlvalue_id wocalibgnlvalueId	");
        query.append("FROM   TAWOCALIBGNLVALUE	x					");
        query.append("WHERE  1=1									");
        query.append(this.getWhere(maWoResultMstrCommonDTO,loginUser));
        query.getOrderByQuery("(SELECT a.ord_no FROM TACDSYSD a WHERE a.list_type = 'CALIB_POINT_TYPE' AND a.cdsysd_no =x.calib_point_type)"
        		, maWoResultMstrCommonDTO.getOrderBy(), maWoResultMstrCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(maWoResultMstrCommonDTO.getIsLoadMaxCount(), maWoResultMstrCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: WorkListGnlCavalListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteCavalList(String id, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("DELETE FROM TAWOCALIBGNLVALUE				");
    	query.append("WHERE  wocalibgnlvalue_id 	= '"+id+"'	");
    	query.append("  AND  comp_no			= '"+compNo+"'	");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    public int copyCavalList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.append("MERGE INTO TAWOCALIBGNLVALUE a																				");
    	query.append("USING(	SELECT 	x.comp_no																					");
    	query.append("					,x.calib_point_type																			");
    	query.append("					,x.calib_point																				");
//    	query.append("					,x.allow_value																				");
    	query.append("                  ,NVL((SELECT REPLACE(tolerance,'N/A','') FROM TAEQTOOL WHERE equip_id = ?   ) , x.allow_value)  allow_value      ");
    	query.append("					,x.asf_std_value																			");
    	query.append("					,x.asl_std_value																			");
    	query.append("					,x.ord_no																					");
    	query.append("			FROM TAPMCALIBSTDVALUE x inner join (SELECT * FROM TAPMCALIBSTDTP a WHERE a.pmc_type = NVL(?,a.pmc_type) AND rownum = 1) y on x.pmcalibstdtp_id = y.pmcalibstdtp_id       ");
    	query.append("			WHERE x.comp_no = ?																					");
    	query.append("          AND   x.pmcalibstdtp_id = NVL(?,x.pmcalibstdtp_id) ) b                                              ");
    	query.append("ON(	a.comp_no = b.comp_no																					");
    	query.append("	AND a.calib_point_type = b.calib_point_type																	");
    	query.append("	AND a.wkor_id = ?																							");
    	query.append("	AND a.calib_point = b.calib_point	)																		");
    	query.append("WHEN MATCHED THEN																								");
    	query.append("	UPDATE SET 	a.allow_value = b.allow_value																	");
    	query.append("WHEN NOT MATCHED THEN																							");
    	query.append("	INSERT (a.comp_no,		a.wocalibgnlvalue_id,			a.wkor_id,	a.calib_point_type, a.calib_point,		");
    	query.append("			a.allow_value, 		a.asf_std_value,	a.asl_std_value,	a.ord_no		)						");
    	query.append("	VALUES (b.comp_no,		SQAWOCALIBGNLVALUE_ID.nextval,	?,			b.calib_point_type,	b.calib_point,		");
    	query.append("			b.allow_value,		b.asf_std_value,	b.asl_std_value,	b.ord_no		)						");
    	
    	Object[] objects = new Object[] {
    	        maWoResultMstrCommonDTO.getEquipId()
    	        ,maWoResultMstrCommonDTO.getSelectedPmType()
    			,loginUser.getCompNo()
    			,maWoResultMstrCommonDTO.getPmCalibStdTpId()
    			,maWoResultMstrCommonDTO.getWkOrId()
    			,maWoResultMstrCommonDTO.getWkOrId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    public int batchCavalList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser, String valId
    		,String calibPoint, String allowValue
			,String asfStdValue, String asfCalValue, String asfDiffValue
			,String aslStdValue, String aslCalValue, String aslDiffValue)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.append("UPDATE TAWOCALIBGNLVALUE SET				");
    	query.append("		calib_point		 = ?				");
    	query.append("		,allow_value	 = ?				");
    	query.append("		,asf_std_value	 = ?				");
    	query.append("		,asf_cal_value	 = ?				");
    	query.append("		,asf_diff_value	 = ?				");
    	query.append("		,asl_std_value	 = ?				");
    	query.append("		,asl_cal_value	 = ?				");
    	query.append("		,asl_diff_value	 = ?				");
//    	query.append("		,asf_diff_value = ROUND((asf_std_value-?),2)	");
//    	query.append("		,asl_diff_value = ROUND((asl_std_value-?),2)	");
    	query.append("WHERE comp_no = ?						");
    	query.append("AND	wocalibgnlvalue_id = ?			");
    	
    	Object[] objects = new Object[] {
    			calibPoint
    			,allowValue
    			,asfStdValue
    			,asfCalValue
    			,asfDiffValue
    			,aslStdValue
    			,aslCalValue
    			,aslDiffValue
    			,loginUser.getCompNo()
    			,valId
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    private String getWhere(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	if (!"".equals(maWoResultMstrCommonDTO.getWocalibgnlvalueId()))
    	{
    		query.getAndQuery("wocalibgnlvalue_id", maWoResultMstrCommonDTO.getWocalibgnlvalueId());
    		return query.toString();
    	}
    	query.getAndQuery("wkor_id", maWoResultMstrCommonDTO.getWkOrId());
    	query.getAndQuery("comp_no", loginUser.getCompNo());
    	
    	return query.toString();
    }

	public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser) throws Exception 
	{
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT                            ");
        query.append("       COUNT(1)                   ");
        query.append("FROM   TAWOCALIBGNLVALUE	x		");
        query.append("WHERE  1=1						");
        query.append(this.getWhere(maWoResultMstrCommonDTO,loginUser));

		List resultList=  getJdbcTemplate().queryForList(query.toString());
	    return QueryBuffer.listToString(resultList);
	}
}