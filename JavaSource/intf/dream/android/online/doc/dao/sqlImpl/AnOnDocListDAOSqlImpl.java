package intf.dream.android.online.doc.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import intf.dream.android.online.doc.dao.AnOnDocListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anOnDocListDAOTarget"
 * @spring.txbn id="anOnDocListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnOnDocListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AnOnDocListDAO
{
	public List findDocList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String lang = String.valueOf(map.get("lang"));
		String fileName = String.valueOf(map.get("fileName"));
		String docDataId = String.valueOf(map.get("docDataId"));
		String plant = String.valueOf(map.get("plant"));
		String filterDeptId = String.valueOf(map.get("filterDeptId"));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        // * 오라클과 맞춰야합니다. 2019-05-08 김정우
        
        query.append("SELECT x.comp_no AS comp_no				");
        query.append("		,x.docdata_id AS docdata_id			");
        query.append("		,x.doc_id AS doc_id					");
        query.append("		,x.file_name AS file_name			");
        query.append("		,x.file_ext AS file_ext				");
        query.append("		,x.file_size AS file_size			");
        query.append("		,x.nf_file_path AS nf_file_path		");
        query.append("		,y.doc_no AS doc_no					");
        query.append("		,y.description AS description		");
        query.append("		,y.dept_id AS dept_id				");
        query.append("		,(SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = y.dept_id) AS dept_desc	");
        query.append("		,y.reg_id AS reg_id					");
        query.append("		,(SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_id = y.reg_id) AS reg_desc			");
        query.append("		,y.object_type AS object_type		");
        query.append("		,dbo.SFACODE_TO_DESC(y.object_type,'OBJECT_TYPE','SYS','','"+lang+"') AS object_type_desc					");
        query.append("		,'dream/android/'+CONVERT(varchar,x.docdata_id) as url	");
		query.append("      ,'-2'  as ID                        ");
		query.append("      ,case when y.docctg_id is null then 0 else y.docctg_id end as PID      ");
		query.append("      ,'N' as isFolder                    ");
		query.append("      ,'1' as ordNo                       ");
        query.append("FROM TADOCDATA x INNER JOIN TADOCUMENT y	");
        query.append("ON x.comp_no = y.comp_no					");
        query.append("AND x.doc_id = y.doc_id					");
        query.append("WHERE 1=1									");
        query.append("AND x.comp_no = '"+compNo+"'				");
        query.getAndNumKeyQuery("x.docdata_id", docDataId);
        query.getDeptLevelQuery("y.dept_id", filterDeptId, "", compNo);
        if(!"".equals(fileName)&&!"null".equals(fileName)){
            query.append("AND (y.doc_no = '"+fileName+"' OR x.file_name like '%"+fileName+"%' )		");
        }
        
    	return getJdbcTemplate().queryForList(query.toString());
    }

}