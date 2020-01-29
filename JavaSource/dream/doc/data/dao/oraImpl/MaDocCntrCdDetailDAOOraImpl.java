package dream.doc.data.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.doc.data.dao.MaDocCntrCdDetailDAO;
import dream.doc.data.dto.MaDocCntrCdCommonDTO;
import dream.doc.data.dto.MaDocCntrCdDetailDTO;

/**
 * 일반자료실 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="maDocCntrCdDetailDAOTarget"
 * @spring.txbn id="maDocCntrCdDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaDocCntrCdDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaDocCntrCdDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param docCntrId
     * @return
     */
    public MaDocCntrCdDetailDTO findDetail(MaDocCntrCdCommonDTO maDocCntrCdCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT								                    ");
        query.append("       x.comp_no		                      compNo,       ");
        query.append("       x.doccntr_id                         docCntrId,    ");
        query.append("       x.doccntr_no                         docCntrNo,    ");
        query.append("       x.description		                  description,  ");
        query.append("       x.eqctg_id                           eqCtgId,      ");
        query.append("      (SELECT full_desc FROM TAEQCTG                      ");
        query.append("       WHERE  comp_no  = x.comp_no                        ");
        query.append("         AND  eqctg_id = x.eqctg_id )       eqCtgDesc,    ");        
        query.append("       x.dept_id                            deptId,       ");
        query.append("       x.reg_date                           regDate,      ");
        query.append("       x.user_id                            userId,       ");
        query.append("       x.doccntr_categ                  docCntrCateg,        ");
        query.append("       SFACODE_TO_DESC(x.doccntr_categ, 'DOCCNTR_CATEG', 'USR', x.comp_no,'"+loginUser.getLangId()+"') docCntrCategDesc, ");
        query.append("       SFAIDTODESC(x.dept_id, '', 'DEPT', x.comp_no)  deptDesc,   ");
        query.append("       SFAIDTODESC(x.user_id, '', 'EMP', x.comp_no)   userName,   ");
        query.append("       remark,                                            ");
        query.append("       doccntr_type docCntrType                           ");
        query.append("FROM   TADOCCNTR x						                ");
        query.append("WHERE  1=1	                                            ");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.doccntr_id", maDocCntrCdCommonDTO.getDocCntrId());
    
        MaDocCntrCdDetailDTO maDocCntrCdDetailDTO = 
        		(MaDocCntrCdDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaDocCntrCdDetailDTO()));
        
        return maDocCntrCdDetailDTO;
    }
    
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDocCntrCdDetailDTO
     * @return
     */
    public int insertDetail(MaDocCntrCdDetailDTO maDocCntrCdDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TADOCCNTR(                                     ");
    	query.append("   comp_no,   doccntr_id,     doccntr_no,   doccntr_type,	 ");
    	query.append("   eqctg_id,  doccntr_categ,  description,  remark,	     ");
    	query.append("   dept_id,   user_id,        reg_date,                    ");
    	query.append("   plant                                                   ");
    	query.append(")VALUES(							                         ");
    	query.append("	 ?,			?,		        ?,            ?,             ");
    	query.append("	 ?,			?,		        ?,            ?,             ");
    	query.append("	 ?,			?,		        TO_CHAR(SYSDATE, 'YYYYMMDD'),");
    	query.append("   ?                                                       ");
    	query.append(")													         ");
    	
    	Object[] objects = new Object[] {
    			loginUser.getCompNo(),
    			maDocCntrCdDetailDTO.getDocCntrId(),
    			maDocCntrCdDetailDTO.getDocCntrNo(),
    			maDocCntrCdDetailDTO.getDocCntrType(),
    			maDocCntrCdDetailDTO.getEqCtgId(),
    			maDocCntrCdDetailDTO.getDocCntrCateg(),
    			maDocCntrCdDetailDTO.getDescription(),
    			maDocCntrCdDetailDTO.getRemark(),
    			maDocCntrCdDetailDTO.getDeptId(),
    			maDocCntrCdDetailDTO.getUserId(),
    			loginUser.getPlant()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDocCntrCdDetailDTO
     * @return
     */
    public int updateDetail(MaDocCntrCdDetailDTO maDocCntrCdDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TADOCCNTR SET	                ");
    	query.append("	     description    = ?,	        ");
    	query.append("       eqCtg_id       = ?,            ");
        query.append("       doccntr_categ  = ?,            ");
        query.append("       remark         = ?             ");
    	query.append("WHERE  comp_no        = ?	            ");
    	query.append("  AND  doccntr_id     = ?	            ");
    	
    	Object[] objects = new Object[] {
                maDocCntrCdDetailDTO.getDescription(),
                maDocCntrCdDetailDTO.getEqCtgId(),
                maDocCntrCdDetailDTO.getDocCntrCateg(),
                maDocCntrCdDetailDTO.getRemark(),
                loginUser.getCompNo(),
                maDocCntrCdDetailDTO.getDocCntrId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
}