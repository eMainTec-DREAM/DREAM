package dream.consult.program.page.dao.sqlImpl;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.consult.program.page.dao.MaPgMngFieldDetailDAO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngFieldDetailDTO;

/**
 * 화면별 필드 상세 dao
 * @author  kim21017
 * @version $Id: MaPgMngFieldDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maPgMngFieldDetailDAOTarget"
 * @spring.txbn id="maPgMngFieldDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPgMngFieldDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPgMngFieldDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPgMngFieldDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @param pgFieldId
     * @return
     */
    public MaPgMngFieldDetailDTO findDetail(MaPgMngFieldDetailDTO maPgMngFieldDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("SELECT																							");
		query.append("       x.page_id										     				pageId					");
        query.append("       ,x.pgfield_id									     				pgFieldId				");
        query.append("       ,x.field_id									     				fieldId					");
        query.append("       ,(SELECT key_name																			");
        query.append("        FROM TALANG																				");
        query.append("        WHERE key_no = x.key_no																	");
        query.append("          AND key_type = x.key_type																");
        query.append("          AND lang = '"+maPgMngFieldDetailDTO.getUserLang()+"'									");
        query.append("        ) 											     				keyName					");
        query.append("       ,x.key_type										     			keyType					");
        query.append("       ,x.key_no										     				keyNo					");
        query.append("       ,x.hidden_yn									     				hiddenYn				");
        query.append("       ,dbo.SFACODE_TO_DESC(x.hidden_yn,'IS_USE','SYS','','"+maPgMngFieldDetailDTO.getUserLang()+"')	hiddenYnDesc ");
        query.append("       ,x.ord_no										     				ordNo		     		");
        query.append("       ,x.display_yn   								     				displayYn				");
        query.append("       ,dbo.SFACODE_TO_DESC(x.display_yn,'IS_USE','SYS','','"+maPgMngFieldDetailDTO.getUserLang()+"')	displayYnDesc ");
        query.append("       ,x.readonly_yn  								     				readonlyYn              ");
        query.append("       ,dbo.SFACODE_TO_DESC(x.readonly_yn,'READONLY_YN','SYS','','"+maPgMngFieldDetailDTO.getUserLang()+"')	readonlyYnDesc ");
        query.append("       ,x.check_yn   									     				checkYn					");
        query.append("       ,dbo.SFACODE_TO_DESC(x.check_yn,'CHECK_YN','SYS','','"+maPgMngFieldDetailDTO.getUserLang()+"')    checkYnDesc ");
        query.append("       ,dbo.SFACODE_TO_DESC(x.field_option,'FIELD_OPTION','SYS','','"+maPgMngFieldDetailDTO.getUserLang()+"')			fieldOptionDesc	");
        query.append("       ,x.field_option 								     				fieldOption             ");
        query.append("       ,(SELECT a.description FROM TAPAGE a WHERE a.page_id = x.page_id)	pageDesc				");
        query.append("       ,x.description  								     				fieldDesc				");
        query.append("       ,x.str_length 									     				strLength 				");
        query.append("       ,x.form_input_type 								     			formInputTypeId 		");
        query.append("       ,dbo.SFACODE_TO_DESC(x.form_input_type,'FORM_INPUT_TYPE','SYS','','"+maPgMngFieldDetailDTO.getUserLang()+"')	formInputTypeDesc	");
        query.append("       ,x.form_input_detail_type 											formInputDetailTypeId	");
        query.append("       ,CASE  x.form_input_type																	");
        query.append("       	WHEN 'CODE_INPUT_TYPE' THEN																");
        query.append("       		dbo.SFACODE_TO_DESC(x.form_input_detail_type,'CODE_INPUT_TYPE','SYS','','"+maPgMngFieldDetailDTO.getUserLang()+"')			");
        query.append("       END	formInputDetailTypeDesc																");
        query.append("       ,x.code_list_type codeListTypeId	 														");
        query.append("       ,CASE WHEN x.form_input_type = 'CODE_INPUT_TYPE' AND  x.form_input_detail_type = 'TACDSYSD' THEN									");
        query.append("       	(SELECT (SELECT b.key_name FROM TALANG b WHERE b.lang = '"+maPgMngFieldDetailDTO.getUserLang()+"' AND b.key_type = a.key_type AND b.key_no = a.key_no )	");
        query.append("       		FROM TACDSYSM a																		");
        query.append("       		WHERE 1=1																			");
        query.append("       		AND a.list_type = x.code_list_type) 												");
        query.append("            WHEN x.form_input_type = 'CODE_INPUT_TYPE' AND  x.form_input_detail_type = 'TACDUSRD' THEN									");
        query.append("			(SELECT MAX(a.description) FROM TACDUSRM a WHERE a.dir_type = x.code_list_type group by a.dir_type )");
        query.append("       END codeListTypeDesc																		");
        query.append("       ,x.validation_jscript validationJscript													");
        query.append("       ,x.group_key_no													groupKeyNo				");
        query.append("       ,x.group_key_type													groupKeyType			");
        query.append("       ,(SELECT key_name                                           								");
    	query.append("         FROM TALANG                                                								");
    	query.append("         WHERE key_no = x.group_key_no						          							");
    	query.append("          AND key_type = x.group_key_type							   								");
    	query.append("          AND lang = '"+maPgMngFieldDetailDTO.getUserLang()+"' 									");
    	query.append("        ) 																groupKeyName			");
    	query.append("       ,x.group_option													groupOption				");
        query.append("       ,dbo.SFACODE_TO_DESC(x.group_option,'GROUP_OPTION','SYS','','"+maPgMngFieldDetailDTO.getUserLang()+"')		groupOptionDesc	");
        query.append("       ,x.table_id 							     						tableId					");
        query.append("       ,x.table_name 							     						tableName				");
        query.append("       ,(SELECT y.description FROM TATABLE y WHERE y.table_id=x.table_id)	tableDesc				");
        query.append("       ,x.column_name 							     					columnName				");
        query.append("       ,(SELECT y.description                                        								");
    	query.append("         FROM TATABCOL y                                            								");
    	query.append("         WHERE y.table_id=x.table_id                                 								");
    	query.append("          AND y.column_name=x.column_name                           								");
    	query.append("         )																columnDesc				");
        query.append("FROM   TAPGFIELD x																				");
        query.append("WHERE  1 = 1   																					");
        query.getAndQuery("x.page_id", maPgMngFieldDetailDTO.getPageId());
        query.getAndQuery("x.pgfield_id", maPgMngFieldDetailDTO.getPgFieldId());
        
        MaPgMngFieldDetailDTO resultDTO = 
        		(MaPgMngFieldDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPgMngFieldDetailDTO()));
        
        return resultDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPgMngFieldDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngFieldDetailDTO
     * @param maPgMngCommonDTO
     * @return
     */
    public int updateDetail(MaPgMngFieldDetailDTO maPgMngFieldDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAPGFIELD SET				  ");
    	query.append("	page_id	                   = ?	  ");
    	query.append("	,field_id	               = ?	  ");
    	query.append("	,hidden_yn	               = ?	  ");
    	query.append("	,ord_no		               = ?	  ");
    	query.append("	,display_yn	               = ?	  ");
    	query.append("  ,readonly_yn               = ?    ");
    	query.append("	,check_yn	               = ?	  ");
    	query.append("  ,description               = ?    ");
    	query.append("  ,key_no                    = ?    ");
    	query.append("  ,field_option              = ?    ");
    	query.append("  ,str_length                = ?    ");
    	query.append("  ,key_type                  = ?	  ");
    	query.append("  ,form_input_type           = ?	  ");
    	query.append("  ,form_input_detail_type    = ?	  ");
    	query.append("  ,code_list_type            = ?	  ");
    	query.append("  ,validation_jscript        = ?	  ");
    	query.append("  ,group_option        	   = ?	  ");
    	query.append("  ,group_key_no        	   = ?	  ");
    	query.append("  ,group_key_type        	   = ?	  ");
    	query.append("  ,table_id        		   = ?	  ");
    	query.append("  ,table_name        		   = ?	  ");
    	query.append("  ,column_name        	   = ?	  ");
    	query.append("WHERE pgfield_id = ?				  ");
    	
    	Object[] objects = new Object[] {
    	        maPgMngCommonDTO.getPageId()
    			,maPgMngFieldDetailDTO.getFieldId()
    			,maPgMngFieldDetailDTO.getHiddenYn()
    			,maPgMngFieldDetailDTO.getOrdNo()
    			,maPgMngFieldDetailDTO.getDisplayYn()
    			,maPgMngFieldDetailDTO.getReadonlyYn()
    			,maPgMngFieldDetailDTO.getCheckYn()
    			,maPgMngFieldDetailDTO.getFieldDesc()
    			,maPgMngFieldDetailDTO.getKeyNo()
    			,maPgMngFieldDetailDTO.getFieldOption()
    			,maPgMngFieldDetailDTO.getStrLength()
    			,maPgMngFieldDetailDTO.getKeyType()
    			,maPgMngFieldDetailDTO.getFormInputTypeId()
    			,maPgMngFieldDetailDTO.getFormInputDetailTypeId()
    			,maPgMngFieldDetailDTO.getCodeListTypeId()
    			,maPgMngFieldDetailDTO.getValidationJscript()
    			,maPgMngFieldDetailDTO.getGroupOption()
    			,maPgMngFieldDetailDTO.getGroupKeyNo()
    			,maPgMngFieldDetailDTO.getGroupKeyType()
    			,maPgMngFieldDetailDTO.getTableId()
    			,maPgMngFieldDetailDTO.getTableName()
    			,maPgMngFieldDetailDTO.getColumnName()
    			,maPgMngFieldDetailDTO.getPgFieldId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPgMngFieldDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngFieldDetailDTO
     * @param maPgMngCommonDTO
     * @return
     */
    public int insertDetail(MaPgMngFieldDetailDTO maPgMngFieldDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAPGFIELD									");
    	query.append("	( page_id					,pgfield_id			");
    	query.append("	 ,field_id					,hidden_yn			");
    	query.append("	 ,ord_no					,display_yn			");
    	query.append("	 ,description				,key_no		    	");
    	query.append("	 ,key_type					,check_yn    		");
    	query.append("	 ,field_option				,readonly_yn   		");
    	query.append("	 ,str_length				,form_input_type	");
    	query.append("	 ,form_input_detail_type	,code_list_type		");
    	query.append("	 ,validation_jscript		,group_option       ");
    	query.append("   ,group_key_no        		,group_key_type   	");
    	query.append("   ,table_id        			,table_name    		");
    	query.append("   ,column_name    								");
    	query.append("	)	VALUES										");
    	query.append("	( ?							,?					");
    	query.append("	 ,?							,?					");
    	query.append("	 ,?							,?					");
    	query.append("   ,?							,?                  ");
    	query.append("	 ,?							,?  				");
    	query.append("	 ,?             			,? 					");
    	query.append("	 ,?             			,?					");
    	query.append("	 ,?             			,?					");
    	query.append("	 ,?							,?					");
    	query.append("	 ,?							,?					");
    	query.append("	 ,?							,?					");
    	query.append("	 ,?												");
    	query.append("	)												");
    	
    	Object[] objects = new Object[] {
    			maPgMngCommonDTO.getPageId()
    			,maPgMngFieldDetailDTO.getPgFieldId()
    			,maPgMngFieldDetailDTO.getFieldId()
    			,maPgMngFieldDetailDTO.getHiddenYn()
    			,maPgMngFieldDetailDTO.getOrdNo()
    			,maPgMngFieldDetailDTO.getDisplayYn()
    			,maPgMngFieldDetailDTO.getFieldDesc()
    			,maPgMngFieldDetailDTO.getKeyNo()
    			,maPgMngFieldDetailDTO.getKeyType()
    			,maPgMngFieldDetailDTO.getCheckYn()
    			,maPgMngFieldDetailDTO.getFieldOption()
    			,maPgMngFieldDetailDTO.getReadonlyYn()
    			,maPgMngFieldDetailDTO.getStrLength()
    			,maPgMngFieldDetailDTO.getFormInputTypeId()
    			,maPgMngFieldDetailDTO.getFormInputDetailTypeId()
    			,maPgMngFieldDetailDTO.getCodeListTypeId()
    			,maPgMngFieldDetailDTO.getValidationJscript()
    			,maPgMngFieldDetailDTO.getGroupOption()
    			,maPgMngFieldDetailDTO.getGroupKeyNo()
    			,maPgMngFieldDetailDTO.getGroupKeyType()
    			,maPgMngFieldDetailDTO.getTableId()
    			,maPgMngFieldDetailDTO.getTableName()
    			,maPgMngFieldDetailDTO.getColumnName()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
}