package dream.consult.program.page.dao.sqlImpl;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.consult.program.page.dao.MaPgMngBtnDetailDAO;
import dream.consult.program.page.dto.MaPgMngBtnDetailDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * 화면별 버튼 상세 dao
 * @author  kim21017
 * @version $Id: MaPgMngBtnDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maPgMngBtnDetailDAOTarget"
 * @spring.txbn id="maPgMngBtnDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPgMngBtnDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPgMngBtnDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPgMngBtnDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @param pgBtnId
     * @return
     */
    public MaPgMngBtnDetailDTO findDetail(MaPgMngBtnDetailDTO maPgMngBtnDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	String lang = maPgMngBtnDetailDTO.getUserLang();
        
    	query.append("SELECT														");
        query.append("       x.page_id			pageId								");
        query.append("       ,x.pgbtn_id		pgBtnId							    ");
        query.append("       ,x.button_id		buttonId							");
        query.append("       ,(SELECT button_no										");
        query.append("       	FROM TABUTTON										");
        query.append("         WHERE button_id = x.button_id) buttonNo				");
        query.append("       ,(SELECT description									");
        query.append("       	FROM TABUTTON										");
        query.append("         WHERE button_id = x.button_id) buttonDesc 			");
        query.append("       ,x.button_loc		buttonLoc							");
        query.append("       ,dbo.SFACODE_TO_DESC(x.button_loc,'BUTTON_LOC','SYS','','"+lang+"') buttonLocDesc");
        query.append("       ,(SELECT description									");
        query.append("          FROM TABUTTON										");
        query.append("         WHERE button_id=x.button_id) buttonRemark			");
        query.append("       ,x.is_use			isUse								");
        query.append("       ,x.ord_no			ordNo		     					");
        query.append("       ,x.is_chkauth       isChkauth							");
        query.append("       ,x.is_set_group isSetGroup								");
        query.append("       ,x.is_basic isBasic									");
        query.append("       ,x.key_no keyNo										");
        query.append("       ,x.key_type keyType                                    ");
        query.append("       ,x.remark remark                                       ");
        query.append("       ,(SELECT a.key_name								    ");
        query.append("        FROM   TALANG a										");
        query.append("        WHERE  a.key_no = x.key_no							");
        query.append("          AND  a.key_type = x.key_type						");
        query.append("          AND  a.lang = '"+lang+"' ) pgButtonDesc			    ");
        query.append("FROM   TAPGBTN x												");
        query.append("WHERE  1 = 1   												");
        query.append("    and x.page_id = ?											");
        query.append("    and x.pgbtn_id = ?										");
        
        Object[] objects = new Object[] {
        		maPgMngBtnDetailDTO.getPageId()
   			,maPgMngBtnDetailDTO.getPgBtnId()
   	    };
        
        
        MaPgMngBtnDetailDTO resultDTO = 
        		(MaPgMngBtnDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MaPgMngBtnDetailDTO()));
        
        return resultDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPgMngBtnDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngBtnDetailDTO
     * @param maPgMngCommonDTO
     * @return
     */
    public int updateDetail(MaPgMngBtnDetailDTO maPgMngBtnDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAPGBTN SET	");
    	query.append("	button_id	 = ?	");
    	query.append("	,button_loc	 = ?	");
    	query.append("	,key_no		 = ?	");
    	query.append("	,key_type	 = ?	");
    	query.append("	,is_use		 = ?	");
    	query.append("  ,is_chkauth   = ?   ");
    	query.append("  ,is_set_group = ?   ");
    	query.append("  ,is_basic     = ?   ");
    	query.append("  ,remark       = ?   ");
    	query.append("	,ord_no		 = ?	");
    	query.append("WHERE pgbtn_id = ?	");
    	query.append("  AND page_id  = ?	");
    	
    	Object[] objects = new Object[] {
    			maPgMngBtnDetailDTO.getButtonId()
    			,maPgMngBtnDetailDTO.getButtonLoc()
    			,maPgMngBtnDetailDTO.getKeyNo()
    			,maPgMngBtnDetailDTO.getKeyType()
    			,maPgMngBtnDetailDTO.getIsUse()
    			,maPgMngBtnDetailDTO.getIsChkauth()
    			,maPgMngBtnDetailDTO.getIsSetGroup()
    			,maPgMngBtnDetailDTO.getIsBasic()
    			,maPgMngBtnDetailDTO.getRemark()
    			,maPgMngBtnDetailDTO.getOrdNo()
    			,maPgMngBtnDetailDTO.getPgBtnId()
    			,maPgMngCommonDTO.getPageId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPgMngBtnDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngBtnDetailDTO
     * @param maPgMngCommonDTO
     * @return
     */
    public int insertDetail(MaPgMngBtnDetailDTO maPgMngBtnDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAPGBTN					");
    	query.append("	(page_id,		pgbtn_id,			");
    	query.append("	 button_id,		button_loc,			");
    	query.append("	 key_no,   		ord_no,				");
    	query.append("	 is_set_group,  is_basic,		    ");
    	query.append("	 is_use,        is_chkauth,   		");
    	query.append("	 key_type, remark			   		");
    	query.append("	)	VALUES							");
    	query.append("	(?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("   ?,             ?,                  ");
    	query.append("	 ?,             ?,    				");
    	query.append("	 ?, ?				   				");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    			maPgMngCommonDTO.getPageId()
    			,maPgMngBtnDetailDTO.getPgBtnId()
    			,maPgMngBtnDetailDTO.getButtonId()
    			,maPgMngBtnDetailDTO.getButtonLoc()
    			,maPgMngBtnDetailDTO.getKeyNo()
    			,maPgMngBtnDetailDTO.getOrdNo()
    			,maPgMngBtnDetailDTO.getIsSetGroup()
    			,maPgMngBtnDetailDTO.getIsBasic()
    			,maPgMngBtnDetailDTO.getIsUse()
    			,maPgMngBtnDetailDTO.getIsChkauth()
    			,maPgMngBtnDetailDTO.getKeyType()
    			,maPgMngBtnDetailDTO.getRemark()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}