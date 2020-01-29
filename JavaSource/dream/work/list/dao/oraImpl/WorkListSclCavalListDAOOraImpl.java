package dream.work.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.list.dao.WorkListSclCavalListDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.WorkListSclCavalListDTO;

/**
 * 작업상세  - 검교정(저울) - 측정값 목록 dao
 * @author  kim21017
 * @version $Id: WorkListSclCavalListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="workListSclCavalListDAOTarget"
 * @spring.txbn id="workListSclCavalListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkListSclCavalListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkListSclCavalListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: WorkListSclCavalListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param workListSclCavalListDTO
     * @param loginUser
     * @return List
     */
    public WorkListSclCavalListDTO findCavalList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT												");
        query.append("		wocalibsclvalue_id 	AS woCalibSclValueId		");
        query.append("		,wkor_id 			AS wkorId					");
        query.append("		,li0_value 			AS liValue0					");
        query.append("		,li25_value 		AS liValue25				");
        query.append("		,li50_value 		AS liValue50				");
        query.append("		,li75_value 		AS liValue75				");
        query.append("		,li100_value 		AS liValue100				");
        query.append("		,ld75_value 		AS ldValue75				");
        query.append("		,ld50_value 		AS ldValue50				");
        query.append("		,ld25_value 		AS ldValue25				");
        query.append("		,ld0_value 			AS ldValue0					");
        query.append("		,ecntr_value 		AS ecntrValue				");
        query.append("		,ebef_value 		AS ebefValue				");
        query.append("		,eaft_value 		AS eaftValue				");
        query.append("		,elft_value 		AS elftValue				");
        query.append("		,erig_value 		AS erigValue				");
        query.append("		,degree1 			AS degree1					");
        query.append("		,degree2 			AS degree2					");
        query.append("		,degree3 			AS degree3					");
        query.append("		,remark 			AS remark					");
        query.append("FROM   TAWOCALIBSCLVALUE								");
        query.append("WHERE  1=1											");
        query.append("AND    wkor_id	= ?									");
        query.append("AND    comp_no 	= ?									");
        
        Object[] objects = new Object[] {
    			maWoResultMstrCommonDTO.getWkOrId()
    			,loginUser.getCompNo()
    	};
        
        WorkListSclCavalListDTO workListSclCavalListDTO = 
		(WorkListSclCavalListDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new WorkListSclCavalListDTO()));
        return workListSclCavalListDTO;
    }
    
    public int mergeCavalList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, WorkListSclCavalListDTO workListSclCavalListDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.append("MERGE INTO TAWOCALIBSCLVALUE a																				");
    	query.append("USING(	SELECT 	?	AS comp_no																				");
    	query.append("					,?	AS wocalibsclvalue_id																	");
    	query.append("					,?	AS wkor_id																				");
    	query.append("					,?	AS li0_value																			");
    	query.append("					,?	AS li25_value																			");
    	query.append("					,?	AS li50_value																			");
    	query.append("					,?	AS li75_value																			");
    	query.append("					,?	AS li100_value																			");
    	query.append("					,?	AS ld75_value																			");
    	query.append("					,?	AS ld50_value																			");
    	query.append("					,?	AS ld25_value																			");
    	query.append("					,?	AS ld0_value																			");
    	query.append("					,?	AS ecntr_value																			");
    	query.append("					,?	AS ebef_value																			");
    	query.append("					,?	AS eaft_value																			");
    	query.append("					,?	AS elft_value																			");
    	query.append("					,?	AS erig_value																			");
    	query.append("					,?	AS degree1																				");
    	query.append("					,?	AS degree2																				");
    	query.append("					,?	AS degree3																				");
    	query.append("					,?	AS remark																				");
    	query.append("			FROM DUAL x	) b																						");
    	query.append("ON(	a.comp_no = b.comp_no																					");
    	query.append("	AND a.wkor_id = b.wkor_id			)																		");
    	query.append("WHEN MATCHED THEN																								");
    	query.append("	UPDATE SET 	a.li0_value = b.li0_value																		");
    	query.append("				,a.li25_value = b.li25_value																	");
    	query.append("				,a.li50_value = b.li50_value																	");
    	query.append("				,a.li75_value = b.li75_value																	");
    	query.append("				,a.li100_value = b.li100_value																	");
    	query.append("				,a.ld75_value = b.ld75_value																	");
    	query.append("				,a.ld50_value = b.ld50_value																	");
    	query.append("				,a.ld25_value = b.ld25_value																	");
    	query.append("				,a.ld0_value = b.ld0_value																		");
    	query.append("				,a.ecntr_value = b.ecntr_value																	");
    	query.append("				,a.ebef_value = b.ebef_value																	");
    	query.append("				,a.eaft_value = b.eaft_value																	");
    	query.append("				,a.elft_value = b.elft_value																	");
    	query.append("				,a.erig_value = b.erig_value																	");
    	query.append("				,a.degree1 = b.degree1																			");
    	query.append("				,a.degree2 = b.degree2																			");
    	query.append("				,a.degree3 = b.degree3																			");
    	query.append("				,a.remark = b.remark																			");
    	query.append("WHEN NOT MATCHED THEN																							");
    	query.append("	INSERT (a.comp_no,		a.wocalibsclvalue_id,	a.wkor_id,			a.li0_value, a.li25_value,				");
    	query.append("			a.li50_value, 	a.li75_value,		a.li100_value,	a.ld75_value, a.ld50_value,						");
    	query.append("			a.ld25_value, 	a.ld0_value,		a.ecntr_value,	a.ebef_value, a.eaft_value,						");
    	query.append("			a.elft_value, 	a.erig_value,		a.degree1,	a.degree2, a.degree3,								");
    	query.append("			a.remark		)																					");
    	query.append("	VALUES (b.comp_no,		sqawocalibsclvalue_id.nextval,	b.wkor_id,	b.li0_value, b.li25_value,				");
    	query.append("			b.li50_value, 	b.li75_value,		b.li100_value,	b.ld75_value, b.ld50_value,						");
    	query.append("			b.ld25_value, 	b.ld0_value,		b.ecntr_value,	b.ebef_value, b.eaft_value,						");
    	query.append("			b.elft_value, 	b.erig_value,		b.degree1,	b.degree2, b.degree3,								");
    	query.append("			b.remark		)																					");
    	
    	Object[] objects = new Object[] {
    			loginUser.getCompNo()
    			,workListSclCavalListDTO.getWoCalibSclValueId()
    			,maWoResultMstrCommonDTO.getWkOrId()
    			,workListSclCavalListDTO.getLiValue0()
    			,workListSclCavalListDTO.getLiValue25()
    			,workListSclCavalListDTO.getLiValue50()
    			,workListSclCavalListDTO.getLiValue75()
    			,workListSclCavalListDTO.getLiValue100()
    			,workListSclCavalListDTO.getLdValue75()
    			,workListSclCavalListDTO.getLdValue50()
    			,workListSclCavalListDTO.getLdValue25()
    			,workListSclCavalListDTO.getLdValue0()
    			,workListSclCavalListDTO.getEcntrValue()
    			,workListSclCavalListDTO.getEbefValue()
    			,workListSclCavalListDTO.getEaftValue()
    			,workListSclCavalListDTO.getElftValue()
    			,workListSclCavalListDTO.getErigValue()
    			,workListSclCavalListDTO.getDegree1()
    			,workListSclCavalListDTO.getDegree2()
    			,workListSclCavalListDTO.getDegree3()
    			,workListSclCavalListDTO.getRemark()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}