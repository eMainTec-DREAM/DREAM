package dream.consult.program.page.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.consult.program.page.dao.MaPgMngFieldValueDetailDAO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngFieldDetailDTO;
import dream.consult.program.page.dto.MaPgMngFieldValueDetailDTO;
import dream.consult.program.page.dto.MaPgMngFieldValueListDTO;

/**
 * 화면별 필드 기본값 상세 dao
 * @author  kim21017
 * @version $Id: MaPgMngFieldValueDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maPgMngFieldValueDetailDAOTarget"
 * @spring.txbn id="maPgMngFieldValueDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPgMngFieldValueDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPgMngFieldValueDetailDAO
{
	@Override
	public MaPgMngFieldValueDetailDTO findDetail(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldDetailDTO maPgMngFieldDetailDTO,
			MaPgMngFieldValueListDTO maPgMngFieldValueListDTO, User user) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT												");
		query.append("		'' AS isDelCheck								");
		query.append("		,'' AS seqNo									");
		query.append("		,x.pgfieldvalue_id AS pgFieldValueId			");
		query.append("		,x.comp_no AS compNoId							");
		query.append("		,(SELECT a.description							");
		query.append("			FROM TACOMP a								");
		query.append("			WHERE 1=1									");
		query.append("			AND a.comp_no = x.comp_no) AS compNoDesc	");
		query.append("		,x.default_object_id AS defaultObjectId			");
		query.append("		,x.default_value AS defaultValueId				");
		query.append("		,CASE '"+maPgMngFieldDetailDTO.getFormInputTypeId()+"'									");
		query.append("			WHEN 'CODE_INPUT_TYPE'						");
		query.append("			THEN CASE '"+maPgMngFieldDetailDTO.getFormInputDetailTypeId()+"'					");
		query.append("				WHEN 'TACDSYSD'							");
		query.append("				THEN dbo.SFACODE_TO_DESC(x.default_value,'"+maPgMngFieldDetailDTO.getCodeListTypeId()+"','SYS','','"+user.getLangId()+"')			");
		query.append("				WHEN 'TACDUSRD'							");
		query.append("				THEN dbo.SFACODE_TO_DESC(x.default_value,'"+maPgMngFieldDetailDTO.getCodeListTypeId()+"','USR',x.comp_no,'"+user.getLangId()+"')	");
		query.append("				WHEN 'TAPLANT'							");
		query.append("				THEN (SELECT a.description FROM TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.default_value)	");
		query.append("				ELSE x.default_value					");
		query.append("				END										");
		query.append("			WHEN 'DATE_INPUT_TYPE'						");
		query.append("			THEN dbo.SFACODE_TO_DESC(x.default_value,'DATE_INPUT_TYPE','SYS','','"+user.getLangId()+"')				");
		query.append("			WHEN 'MONTH_INPUT_TYPE'						");
		query.append("			THEN dbo.SFACODE_TO_DESC(x.default_value,'MONTH_INPUT_TYPE','SYS','','"+user.getLangId()+"')			");
		query.append("			WHEN 'YEAR_INPUT_TYPE'						");
		query.append("			THEN dbo.SFACODE_TO_DESC(x.default_value,'YEAR_INPUT_TYPE','SYS','','"+user.getLangId()+"')				");
		query.append("			ELSE x.default_value						");
		query.append("		END AS defaultValueDesc							");
		query.append("		,x.date_interval AS dateInterval				");
		query.append("FROM TAPGFIELDVALUE x									");
		query.append("WHERE 1=1												");
		query.append("AND x.pgfield_id			= ?							");
		query.append("AND x.pgfieldvalue_id		= ?							");
		
		Object[] objects = new Object[] {
				maPgMngFieldDetailDTO.getPgFieldId()
				,maPgMngFieldValueListDTO.getPgFieldValueId()
    	};
		
		MaPgMngFieldValueDetailDTO maPgMngFieldValueDetailDTO = (MaPgMngFieldValueDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new MaPgMngFieldValueDetailDTO()));
		
		return maPgMngFieldValueDetailDTO;
	}

	@Override
	public int updateDetail(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldDetailDTO maPgMngFieldDetailDTO,
			MaPgMngFieldValueDetailDTO maPgMngFieldValueDetailDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAPGFIELDVALUE SET			");
    	query.append("		comp_no 			= ?		");
    	query.append("		,default_object_id 	= ?		");
    	query.append("		,default_value 		= ?		");
    	query.append("		,date_interval 		= ?		");
    	query.append("WHERE 1=1							");
    	query.append("AND pgfieldvalue_id = ?			");
    	
    	Object[] objects = new Object[] {
    			maPgMngFieldValueDetailDTO.getCompNoId()
    			,maPgMngFieldValueDetailDTO.getDefaultObjectId()
    			,maPgMngFieldValueDetailDTO.getDefaultValueId()
    			,maPgMngFieldValueDetailDTO.getDateInterval()
    			,maPgMngFieldValueDetailDTO.getPgFieldValueId()
    	};
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
	}

	@Override
	public int insertDetail(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldDetailDTO maPgMngFieldDetailDTO,
			MaPgMngFieldValueDetailDTO maPgMngFieldValueDetailDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAPGFIELDVALUE(	");
    	query.append("		pgfieldvalue_id			");
    	query.append("		,comp_no				");
    	query.append("		,pgfield_id				");
    	query.append("		,file_name				");
    	query.append("		,field_id				");
    	query.append("		,default_object_id		");
    	query.append("		,default_value			");
    	query.append("		,date_interval			");
    	query.append(")VALUES(						");
    	query.append("		?						");
    	query.append("		,?						");
    	query.append("		,?						");
    	query.append("		,(SELECT file_name FROM TAPAGE WHERE page_id = ? )	");
    	query.append("		,?						");
    	query.append("		,?						");
    	query.append("		,?						");
    	query.append("		,?						");
    	query.append("	)							");
    	Object[] objects = new Object[] {
    		maPgMngFieldValueDetailDTO.getPgFieldValueId()
    		,maPgMngFieldValueDetailDTO.getCompNoId()
    		,maPgMngFieldDetailDTO.getPgFieldId()
    		,maPgMngCommonDTO.getPageId()
    		,maPgMngFieldDetailDTO.getFieldId()
    		,maPgMngFieldValueDetailDTO.getDefaultObjectId()
    		,maPgMngFieldValueDetailDTO.getDefaultValueId()
    		,maPgMngFieldValueDetailDTO.getDateInterval()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
	}

	@Override
	public String validCompNo(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldDetailDTO maPgMngFieldDetailDTO,
			MaPgMngFieldValueDetailDTO maPgMngFieldValueDetailDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT count(*)															");
		query.append("FROM TAPGFIELDVALUE														");
		query.append("WHERE 1=1																	");
		query.append("AND pgfield_id = "+maPgMngFieldDetailDTO.getPgFieldId()+"					");
		query.append("AND pgfieldvalue_id != "+maPgMngFieldValueDetailDTO.getPgFieldValueId()+"	");
		query.append("AND comp_no = '"+maPgMngFieldValueDetailDTO.getCompNoId()+"'				");
		
		return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
	}
}