package intf.dream.bee.doc.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import intf.dream.bee.doc.dao.BeeDocListDAO;
import intf.dream.bee.doc.dto.BeeDocCommonDTO;

/**
 * dao
 * 
 * @author
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="beeDocListDAOTarget"
 * @spring.txbn id="beeDocListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BeeDocListDAOOraImpl extends BaseJdbcDaoSupportOra implements BeeDocListDAO {
	public List findDocList(BeeDocCommonDTO beeDocCommonDTO, Map map) throws Exception {
		String compNo = CommonUtil.convertString(String.valueOf(map.get("compNo")));
		String lang = CommonUtil.convertString(String.valueOf(map.get("lang")));
		String fileName = CommonUtil.convertString(String.valueOf(map.get("fileName")));
		String docDataId = CommonUtil.convertString(String.valueOf(map.get("docDataId")));
		String plant = CommonUtil.convertString(String.valueOf(map.get("plant")));
		String filterDeptId = CommonUtil.convertString(String.valueOf(map.get("filterDeptId")));

		QueryBuffer query = new QueryBuffer();
		
		if("".equals(docDataId)){
			query.append("select comp_no      						                    ");
			query.append("        ,docdata_id      					                    ");
			query.append("        ,doc_id      						                    ");
			query.append("        ,file_name      					                    ");
			query.append("        ,file_ext      					                    ");
			query.append("        ,file_size      					                    ");
			query.append("        ,nf_file_path      				                    ");
			query.append("        ,doc_no      						                    ");
			query.append("        ,description      				                    ");
			query.append("        ,dept_id      					                    ");
			query.append("        ,dept_desc      					                    ");
			query.append("        ,reg_id      						                    ");
			query.append("        ,reg_desc      					                    ");
			query.append("        ,object_type      				                    ");
			query.append("        ,object_type_desc      			                    ");
			query.append("        ,url      						                    ");
			query.append("        ,ID      							                    ");
			query.append("        ,PID      						                    ");
			query.append("        ,isFolder      					                    ");
			query.append("        ,ordNo      						                    ");
			query.append("        ,level lvl      					                    ");
			query.append("FROM       								                    ");
			query.append("(      									                    ");
			query.append("SELECT x.comp_no AS comp_no                      				");
			query.append("        ,null AS docdata_id                  					");
			query.append("        ,null AS doc_id                          				");
			query.append("        ,x.description AS file_name                  			");
			query.append("        ,'' AS file_ext                      					");
			query.append("        ,null AS file_size                  					");
			query.append("        ,'' AS nf_file_path              						");
			query.append("        ,'' AS doc_no                          				");
			query.append("        ,x.description AS description              			");
			query.append("        ,null AS dept_id                      				");
			query.append("        ,'' AS dept_desc          							");
			query.append("        ,null AS reg_id                          				");
			query.append("        ,'' AS reg_desc                  						");
			query.append("        ,'' AS object_type              						");
			query.append("        ,'' AS object_type_desc                          		");
			query.append("        ,'' as url      										");
			query.append("        ,to_char(x.docctg_id) AS ID      						");
			query.append("        ,x.p_docctg_id as PID             					");
			query.append("        ,'Y' as isFolder      								");
			query.append("        ,'0' as ordNo      									");
			query.append("from tadocctg x       										");
			query.append("WHERE  1=1      												");
			query.append("		  AND x.comp_no = '"+compNo+"'							");
			query.append("		  AND x.is_use = 'Y'									");
			query.append("UNION ALL      												");
		}
		
		query.append("SELECT x.comp_no AS comp_no                      				");
		query.append("            ,x.docdata_id AS docdata_id                  		");
		query.append("            ,x.doc_id AS doc_id                          		");
		query.append("            ,x.file_name AS file_name                  		");
		query.append("            ,x.file_ext AS file_ext                      		");
		query.append("            ,x.file_size AS file_size                  		");
		query.append("            ,x.nf_file_path AS nf_file_path              		");
		query.append("            ,y.doc_no AS doc_no                          		");
		query.append("            ,y.description AS description              		");
		query.append("            ,y.dept_id AS dept_id                      		");
		query.append("            ,(SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = y.dept_id) AS dept_desc          ");
		query.append("            ,y.reg_id AS reg_id                          		");
		query.append("            ,(SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_id = y.reg_id) AS reg_desc                  ");
		query.append("            ,y.object_type AS object_type              		");
		query.append("            ,(SELECT SFACODE_TO_DESC(y.object_type,'OBJECT_TYPE','SYS','','ko') FROM DUAL) AS object_type_desc                          ");
		query.append("            ,'dream/android/'||x.docdata_id as url      		");
		query.append("           ,'-2'  as ID      									");
		query.append("           ,case when y.docctg_id is null then 0 else y.docctg_id end as PID      ");
		query.append("           ,'N' as isFolder      								");
		query.append("          ,'1' as ordNo      									");
		query.append("        FROM TADOCDATA x INNER JOIN TADOCUMENT y          	");
		query.append("        ON x.comp_no = y.comp_no                          	");
		query.append("        AND x.doc_id = y.doc_id                          		");
		query.append("        WHERE 1=1                                          	");
		query.append("		  AND x.comp_no = '"+compNo+"'							");
        query.getAndNumKeyQuery("x.docdata_id", docDataId);
        query.getDeptLevelQuery("y.dept_id", filterDeptId, "", compNo);
        if(!"".equals(fileName)){
            query.append("AND (y.doc_no = '"+fileName+"' OR x.file_name like '%"+fileName+"%' )		");
        }
		
        if("".equals(docDataId)){
        	query.append("        )      												");
			query.append("        START WITH PID=0      								");
			query.append("        CONNECT BY PRIOR ID = PID      						");
			query.append("        ORDER SIBLINGS BY ordNo ASC      						");
			query.append("              												");
		}

        
		return getJdbcTemplate().queryForList(query.toString());
	}
	
	public List findDocCount(BeeDocCommonDTO beeDocCommonDTO, Map map) throws Exception {
		String compNo = CommonUtil.convertString(String.valueOf(map.get("compNo")));
		String lang = CommonUtil.convertString(String.valueOf(map.get("lang")));
		String fileName = CommonUtil.convertString(String.valueOf(map.get("fileName")));
		String docDataId = CommonUtil.convertString(String.valueOf(map.get("docDataId")));
		String plant = CommonUtil.convertString(String.valueOf(map.get("plant")));
		String filterDeptId = CommonUtil.convertString(String.valueOf(map.get("filterDeptId")));

		QueryBuffer query = new QueryBuffer();
		
		if("".equals(docDataId)){
			query.append("select comp_no      						                    ");
			query.append("        ,docdata_id      					                    ");
			query.append("        ,doc_id      						                    ");
			query.append("        ,file_name      					                    ");
			query.append("        ,file_ext      					                    ");
			query.append("        ,file_size      					                    ");
			query.append("        ,nf_file_path      				                    ");
			query.append("        ,doc_no      						                    ");
			query.append("        ,description      				                    ");
			query.append("        ,dept_id      					                    ");
			query.append("        ,dept_desc      					                    ");
			query.append("        ,reg_id      						                    ");
			query.append("        ,reg_desc      					                    ");
			query.append("        ,object_type      				                    ");
			query.append("        ,object_type_desc      			                    ");
			query.append("        ,url      						                    ");
			query.append("        ,ID      							                    ");
			query.append("        ,PID      						                    ");
			query.append("        ,isFolder      					                    ");
			query.append("        ,ordNo      						                    ");
			query.append("        ,level lvl      					                    ");
			query.append("FROM       								                    ");
			query.append("(      									                    ");
			query.append("SELECT x.comp_no AS comp_no                      				");
			query.append("        ,null AS docdata_id                  					");
			query.append("        ,null AS doc_id                          				");
			query.append("        ,x.description AS file_name                  			");
			query.append("        ,'' AS file_ext                      					");
			query.append("        ,null AS file_size                  					");
			query.append("        ,'' AS nf_file_path              						");
			query.append("        ,'' AS doc_no                          				");
			query.append("        ,x.description AS description              			");
			query.append("        ,null AS dept_id                      				");
			query.append("        ,'' AS dept_desc          							");
			query.append("        ,null AS reg_id                          				");
			query.append("        ,'' AS reg_desc                  						");
			query.append("        ,'' AS object_type              						");
			query.append("        ,'' AS object_type_desc                          		");
			query.append("        ,'' as url      										");
			query.append("        ,to_char(x.docctg_id) AS ID      						");
			query.append("        ,x.p_docctg_id as PID             					");
			query.append("        ,'Y' as isFolder      								");
			query.append("        ,'0' as ordNo      									");
			query.append("from tadocctg x       										");
			query.append("WHERE  1=1      												");
			query.append("		  AND x.comp_no = '"+compNo+"'							");
			query.append("		  AND x.is_use = 'Y'									");
			query.append("UNION ALL      												");
		}
		
		query.append("SELECT x.comp_no AS comp_no                      				");
		query.append("            ,x.docdata_id AS docdata_id                  		");
		query.append("            ,x.doc_id AS doc_id                          		");
		query.append("            ,x.file_name AS file_name                  		");
		query.append("            ,x.file_ext AS file_ext                      		");
		query.append("            ,x.file_size AS file_size                  		");
		query.append("            ,x.nf_file_path AS nf_file_path              		");
		query.append("            ,y.doc_no AS doc_no                          		");
		query.append("            ,y.description AS description              		");
		query.append("            ,y.dept_id AS dept_id                      		");
		query.append("            ,(SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = y.dept_id) AS dept_desc          ");
		query.append("            ,y.reg_id AS reg_id                          		");
		query.append("            ,(SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_id = y.reg_id) AS reg_desc                  ");
		query.append("            ,y.object_type AS object_type              		");
		query.append("            ,(SELECT SFACODE_TO_DESC(y.object_type,'OBJECT_TYPE','SYS','','ko') FROM DUAL) AS object_type_desc                          ");
		query.append("            ,'dream/android/'||x.docdata_id as url      		");
		query.append("           ,'-2'  as ID      									");
		query.append("           ,case when y.docctg_id is null then 0 else y.docctg_id end as PID      ");
		query.append("           ,'N' as isFolder      								");
		query.append("          ,'1' as ordNo      									");
		query.append("        FROM TADOCDATA x INNER JOIN TADOCUMENT y          	");
		query.append("        ON x.comp_no = y.comp_no                          	");
		query.append("        AND x.doc_id = y.doc_id                          		");
		query.append("        WHERE 1=1                                          	");
		query.append("		  AND x.comp_no = '"+compNo+"'							");
        query.getAndNumKeyQuery("x.docdata_id", docDataId);
        query.getDeptLevelQuery("y.dept_id", filterDeptId, "", compNo);
        if(!"".equals(fileName)){
            query.append("AND (y.doc_no = '"+fileName+"' OR x.file_name like '%"+fileName+"%' )		");
        }
		
        if("".equals(docDataId)){
        	query.append("        )      												");
			query.append("        START WITH PID=0      								");
			query.append("        CONNECT BY PRIOR ID = PID      						");
			query.append("        ORDER SIBLINGS BY ordNo ASC      						");
			query.append("              												");
		}

        
		return getJdbcTemplate().queryForList(query.toString());
	}
	
}