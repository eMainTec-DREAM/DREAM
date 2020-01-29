package dream.work.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
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
public class WorkListSclCavalListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkListSclCavalListDAO
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
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
    	QuerySqlBuffer query = new QuerySqlBuffer();


    	query.append("DECLARE @t1 TABLE( 																	");
    	query.append("	comp_no NVARCHAR(1000), 															");
    	query.append("	wocalibsclvalue_id NVARCHAR(1000),  												");
    	query.append("	wkor_id NVARCHAR(1000),																");
    	query.append("	li0_value NVARCHAR(1000),															");
    	query.append("	li25_value NVARCHAR(1000),															");
    	query.append("	li50_value NVARCHAR(1000),															");
    	query.append("	li75_value NVARCHAR(1000),															");
    	query.append("	li100_value NVARCHAR(1000),															");
    	query.append("	ld75_value NVARCHAR(1000),															");
    	query.append("	ld50_value NVARCHAR(1000),															");
    	query.append("	ld25_value NVARCHAR(1000),															");
    	query.append("	ld0_value NVARCHAR(1000),															");
    	query.append("	ecntr_value NVARCHAR(1000),															");
    	query.append("	ebef_value NVARCHAR(1000),															");
    	query.append("	eaft_value NVARCHAR(1000),															");
    	query.append("	elft_value NVARCHAR(1000),															");
    	query.append("	erig_value NVARCHAR(1000),															");
    	query.append("	degree1 NVARCHAR(1000),																");
    	query.append("	degree2 NVARCHAR(1000),																");
    	query.append("	degree3 NVARCHAR(1000),																");
    	query.append("	remark NVARCHAR(1000)  																");
    	query.append(") 																					");
    	query.append("INSERT INTO @t1(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)							");
    	query.append("IF EXISTS( 																			");
    	query.append("	SELECT 1 																			");
    	query.append("	FROM TAWOCALIBSCLVALUE A, @t1 b 													");
    	query.append("	WHERE A.comp_no = b.comp_no 														");
    	query.append("	AND A.wkor_id = wkor_id				 												");
    	query.append(") 																					");
    	query.append("BEGIN  																				");
    	query.append("	UPDATE TAWOCALIBSCLVALUE SET 														");
    	query.append("				A.li0_value = b.li0_value												");
    	query.append("				,A.li50_value = b.li50_value											");
    	query.append("				,A.li25_value = b.li25_value											");
    	query.append("				,A.li75_value = b.li75_value											");
    	query.append("				,A.li100_value = b.li100_value											");
    	query.append("				,A.ld75_value = b.ld75_value											");
    	query.append("				,A.ld50_value = b.ld50_value											");
    	query.append("				,A.ld25_value = b.ld25_value											");
    	query.append("				,A.ld0_value = b.ld0_value												");
    	query.append("				,A.ecntr_value = b.ecntr_value											");
    	query.append("				,A.ebef_value = b.ebef_value											");
    	query.append("				,A.eaft_value = b.eaft_value											");
    	query.append("				,A.elft_value = b.elft_value											");
    	query.append("				,A.erig_value = b.erig_value											");
    	query.append("				,A.degree1 = b.degree1													");
    	query.append("				,A.degree2 = b.degree2													");
    	query.append("				,A.degree3 = b.degree3													");
    	query.append("				,A.remark = b.remark													");
    	query.append("	FROM TAWOCALIBGNLVALUE A JOIN @t1 b													");
    	query.append("	ON A.comp_no = b.comp_no 															");
    	query.append("	AND A.wkor_id = b.wkor_id		 													");
    	query.append(" END 																					");
    	query.append("ELSE 																					");
    	query.append("BEGIN 																				");
    	query.append("  INSERT INTO TAWOCALIBSCLVALUE 														");
    	query.append("  (a.comp_no,		a.wocalibsclvalue_id,	a.wkor_id,			a.li0_value, a.li25_value,						");
    	query.append("			a.li50_value, 	a.li75_value,		a.li100_value,	a.ld75_value, a.ld50_value,						");
    	query.append("			a.ld25_value, 	a.ld0_value,		a.ecntr_value,	a.ebef_value, a.eaft_value,						");
    	query.append("			a.elft_value, 	a.erig_value,		a.degree1,	a.degree2, a.degree3,								");
    	query.append("			a.remark		)																					");
    	query.append("SELECT 																										");
    	query.append("			b.comp_no,		NEXT VALUE FOR sqawocalibsclvalue_id,	b.wkor_id,	b.li0_value, b.li25_value,		");
    	query.append("			b.li50_value, 	b.li75_value,		b.li100_value,	b.ld75_value, b.ld50_value,						");
    	query.append("			b.ld25_value, 	b.ld0_value,		b.ecntr_value,	b.ebef_value, b.eaft_value,						");
    	query.append("			b.elft_value, 	b.erig_value,		b.degree1,	b.degree2, b.degree3,								");
    	query.append("			b.remark																							");
    	query.append("FROM 	@t1 b 																									");
    	query.append("	END 																										");
    	
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