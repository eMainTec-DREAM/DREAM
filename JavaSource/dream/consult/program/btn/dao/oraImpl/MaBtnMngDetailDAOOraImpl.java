package dream.consult.program.btn.dao.oraImpl;

import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.consult.program.btn.dao.MaBtnMngDetailDAO;
import dream.consult.program.btn.dto.MaBtnMngDetailDTO;

/**
 * 버튼 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaBtnMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maBtnMngDetailDAOTarget"
 * @spring.txbn id="maBtnMngDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaBtnMngDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaBtnMngDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaBtnMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param buttonId
     * @return
     */
    public MaBtnMngDetailDTO findDetail(String buttonId)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT								");
        query.append("       x.button_no		buttonNo	");
        query.append("       ,x.button_id		buttonId	");
        query.append("       ,x.description		buttonDesc	");
        query.append("       ,x.btn_img			btnImg		");
        query.append("       ,x.ord_no			ordNo		");
        query.append("       ,x.is_use			isUse		");
        query.append("       ,x.remark			remark		");
        query.append("FROM   TABUTTON x						");
        query.append("WHERE  x.button_id = ?             	");
        
        Object[] objects = new Object[] {
        		buttonId
   	    };
    
        MaBtnMngDetailDTO maBtnMngDetailDTO = 
        		(MaBtnMngDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new MaBtnMngDetailDTO()));
        
        return maBtnMngDetailDTO;
    }
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaBtnMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBtnMngDetailDTO
     * @return
     */
    public int insertDetail(MaBtnMngDetailDTO maBtnMngDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TABUTTON(								");
    	query.append("	button_id                                       ");
    	query.append("  ,button_no                                      ");
    	query.append("  ,description                                	");
    	query.append("	,btn_img                                	    ");
    	query.append("  ,is_use                                	        ");
    	query.append("  ,ord_no  		                                ");
    	query.append("	,remark                                 		");
    	query.append("	)	VALUES (									");
    	query.append("	 ?                                              ");
    	query.append("  ,?                                              ");
    	query.append("  ,?                                	            ");
    	query.append("	,?                                	            ");
    	query.append("  ,?                                	            ");
    	query.append("  ,?  		                                    ");
    	query.append("	)												");
    	
    	Object[] objects = new Object[] {
    			maBtnMngDetailDTO.getButtonId()
    			,maBtnMngDetailDTO.getButtonNo()
    			,maBtnMngDetailDTO.getButtonDesc()
    			,maBtnMngDetailDTO.getBtnImg()
    			,maBtnMngDetailDTO.getIsUse()
    			,maBtnMngDetailDTO.getOrdNo()
    			,maBtnMngDetailDTO.getRemark()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaBtnMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBtnMngDetailDTO
     * @return
     */
    public int updateDetail(MaBtnMngDetailDTO maBtnMngDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TABUTTON SET	");
    	query.append("	button_no	 = ?    ");
    	query.append("	,description = ?	");
    	query.append("	,btn_img	 = ?	");
    	query.append("	,is_use		 = ?    ");
    	query.append("	,ord_no		 = ?	");
    	query.append("	,remark		 = ?	");
    	query.append("WHERE button_id = ?	");
    	
    	Object[] objects = new Object[] {
    			maBtnMngDetailDTO.getButtonNo()
    			,maBtnMngDetailDTO.getButtonDesc()
    			,maBtnMngDetailDTO.getBtnImg()
    			,maBtnMngDetailDTO.getIsUse()
    			,maBtnMngDetailDTO.getOrdNo()
    			,maBtnMngDetailDTO.getRemark()
    			,maBtnMngDetailDTO.getButtonId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}