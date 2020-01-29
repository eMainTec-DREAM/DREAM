package dream.ass.base.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.ass.base.dao.AssBaseGradeDetailDAO;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBaseGradeDetailDTO;
import dream.ass.base.dto.AssBaseGradeListDTO;

/**
 * 등급기준 - Detail DAO implements
 * @author kim21017
 * @version $Id: AssBaseGradeDetailDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="assBaseGradeDetailDAOTarget"
 * @spring.txbn id="assBaseGradeDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssBaseGradeDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements AssBaseGradeDetailDAO
{
	
    public AssBaseGradeDetailDTO findDetail(AssBaseCommonDTO assBaseCommonDTO, AssBaseGradeListDTO assBaseGradeListDTO, User user) 
    {
    	QueryBuffer query = new QueryBuffer();


        query.append("SELECT													");
        query.append("		x.assbasegrade_id				AS assBaseGradeId	");
        query.append("		,x.assbaselist_id				AS assBaseListId	");
        query.append("		,x.eq_grade						AS eqGradeId		");
        query.append("		,SFACODE_TO_DESC(x.eq_grade,'EQ_GRADE','SYS',''		");
        query.append("								,?) 	AS eqGradeDesc		");
        query.append("		,x.grade_from					AS gradeFrom		");
        query.append("		,x.grade_to						AS gradeTo			");
        query.append("		,x.ord_no						AS ordNo			");
        query.append("		,x.remark						AS remark			");
        query.append("      ,x.cycle                        AS CYCLE            ");
        query.append("      ,x.period_type 					AS periodType      ");
        query.append("      ,SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE', 'SYS', '"+user.getCompNo()+"','"+user.getLangId()+"') PERIODTYPEDESC          ");
        query.append("FROM TAASSBASEGRADE x										");
    	query.append("WHERE  1=1												");
    	query.append("AND  x.assbaselist_id		= ?								");
    	query.append("AND  x.assbasegrade_id	= ?								");
    	query.append("AND  x.comp_no    		= ?								");
        
        Object[] objects = new Object[] {
        		user.getLangId()
        		,assBaseCommonDTO.getAssBaseListId()
        		,assBaseGradeListDTO.getAssBaseGradeId()
    			,user.getCompNo()
    	};
    
        AssBaseGradeDetailDTO assBaseGradeDetailDTO = 
        		(AssBaseGradeDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new AssBaseGradeDetailDTO()));
        
        return assBaseGradeDetailDTO;
        
    }

    public int insertDetail(AssBaseCommonDTO assBaseCommonDTO,AssBaseGradeDetailDTO assBaseGradeDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TAASSBASEGRADE(	");
    	query.append("	comp_no						");
    	query.append("	,assbasegrade_id			");
    	query.append("	,assbaselist_id				");
    	query.append("	,eq_grade					");
    	query.append("	,grade_from					");
    	query.append("	,grade_to					");
    	query.append("	,ord_no						");
    	query.append("	,remark						");
    	query.append("	,cycle   					");
    	query.append("	,period_type				");
    	query.append(")VALUES(						");
    	query.append("	?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append(")								");
    	
    	Object[] objects = new Object[] {
    			 user.getCompNo()
     			,assBaseGradeDetailDTO.getAssBaseGradeId()
    			,assBaseCommonDTO.getAssBaseListId()
    			,assBaseGradeDetailDTO.getEqGradeId()
    			,assBaseGradeDetailDTO.getGradeFrom()
    			,assBaseGradeDetailDTO.getGradeTo()
    			,assBaseGradeDetailDTO.getOrdNo()
    			,assBaseGradeDetailDTO.getRemark()
    			,assBaseGradeDetailDTO.getCycle()
    			,assBaseGradeDetailDTO.getPeriodType()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int updateDetail(AssBaseCommonDTO assBaseCommonDTO,AssBaseGradeDetailDTO assBaseGradeDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TAASSBASEGRADE SET					");
    	query.append("	eq_grade				= ?				");
    	query.append("	,grade_from				= ?				");
    	query.append("	,grade_to				= ?				");
    	query.append("	,ord_no					= ?				");
    	query.append("	,remark					= ?				");
    	query.append("	,cycle					= ?				");
    	query.append("	,period_type			= ?				");
    	query.append("WHERE  comp_no			= ?				");
    	query.append("  AND  assbaselist_id		= ?				");
    	query.append("  AND  assbasegrade_id	= ?				");
    	
    	Object[] objects = new Object[] {
    			assBaseGradeDetailDTO.getEqGradeId()
    			,assBaseGradeDetailDTO.getGradeFrom()
    			,assBaseGradeDetailDTO.getGradeTo()
    			,assBaseGradeDetailDTO.getOrdNo()
    			,assBaseGradeDetailDTO.getRemark()
    			,assBaseGradeDetailDTO.getCycle()
    			,assBaseGradeDetailDTO.getPeriodType()
    			,user.getCompNo()
    			,assBaseCommonDTO.getAssBaseListId()
    			,assBaseGradeDetailDTO.getAssBaseGradeId()
    			
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    public String validGrade(AssBaseCommonDTO assBaseCommonDTO,AssBaseGradeDetailDTO assBaseGradeDetailDTO, User user){
    	QueryBuffer query = new QueryBuffer();
    	query.append("SELECT count(*) 				");
    	query.append("FROM TAASSBASEGRADE			");
    	query.append("WHERE 1=1						");
    	query.append("AND   assbasegrade_id!="+assBaseGradeDetailDTO.getAssBaseGradeId()+"	");
    	query.getAndQuery("assbaselist_id", assBaseCommonDTO.getAssBaseListId());
    	query.getAndQuery("eq_grade", assBaseGradeDetailDTO.getEqGradeId());
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    public String validFromTo(AssBaseCommonDTO assBaseCommonDTO,AssBaseGradeDetailDTO assBaseGradeDetailDTO, User user){
    	QueryBuffer query = new QueryBuffer();
    	query.append("SELECT count(*) 				");
    	query.append("FROM TAASSBASEGRADE			");
    	query.append("WHERE 1=1						");
    	query.append("AND   assbasegrade_id!="+assBaseGradeDetailDTO.getAssBaseGradeId()+"											");
    	query.append("AND   (grade_from BETWEEN "+assBaseGradeDetailDTO.getGradeFrom()+" AND "+assBaseGradeDetailDTO.getGradeTo()+"	");
    	query.append("OR    grade_to BETWEEN "+assBaseGradeDetailDTO.getGradeFrom()+" AND "+assBaseGradeDetailDTO.getGradeTo()+")	");
    	query.getAndQuery("assbaselist_id", assBaseCommonDTO.getAssBaseListId());
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
}