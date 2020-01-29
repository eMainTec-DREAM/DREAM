package dream.consult.program.page.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.program.page.dao.MaPgMngFieldValueListDAO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngFieldDetailDTO;
import dream.consult.program.page.dto.MaPgMngFieldListDTO;
import dream.consult.program.page.dto.MaPgMngFieldValueListDTO;

/**
 * 화면별 필드 기본값 목록 dao
 * @author  kim21017
 * @version $Id: MaPgMngFieldValueListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maPgMngFieldValueListDAOTarget"
 * @spring.txbn id="maPgMngFieldValueListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPgMngFieldValueListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPgMngFieldValueListDAO
{

	@Override
	public List findList(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldListDTO maPgMngFieldListDTO,
			MaPgMngFieldDetailDTO maPgMngFieldDetailDTO, MaPgMngFieldValueListDTO maPgMngFieldValueListDTO, User user) {
		
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
		query.append("AND x.pgfield_id	= "+maPgMngFieldListDTO.getPgFieldId()+"			");
		
		if(!"".equals(maPgMngFieldValueListDTO.getPgFieldValueId())){
			query.getAndQuery("x.pgfieldvalue_id", maPgMngFieldValueListDTO.getPgFieldValueId());
		}
		query.getOrderByQuery("x.pgfieldvalue_id","x.comp_no", maPgMngFieldValueListDTO.getOrderBy(), maPgMngFieldValueListDTO.getDirection());
		
		return getJdbcTemplate().queryForList(query.toString(maPgMngFieldValueListDTO.getIsLoadMaxCount(), maPgMngFieldValueListDTO.getFirstRow()));
	}

	@Override
	public int deleteList(String id, User user){
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("DELETE FROM TAPGFIELDVALUE			");
        query.append("WHERE  1=1							");
        query.append("  AND  pgfieldvalue_id  		= ?		");
        
        Object[] objects = new Object[] {   
                	id
                };

        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
	}

	@Override
	public String findTotalCount(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldListDTO maPgMngFieldListDTO,
			MaPgMngFieldDetailDTO maPgMngFieldDetailDTO, MaPgMngFieldValueListDTO maPgMngFieldValueListDTO, User user) throws Exception {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT COUNT(*)														");
		query.append("FROM TAPGFIELDVALUE x													");
		query.append("WHERE 1=1																");
		query.append("AND x.pgfield_id	= "+maPgMngFieldListDTO.getPgFieldId()+"			");
		
    	List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
	}
    
}