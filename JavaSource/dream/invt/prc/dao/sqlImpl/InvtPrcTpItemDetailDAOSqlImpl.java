package dream.invt.prc.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.invt.prc.dao.InvtPrcTpItemDetailDAO;
import dream.invt.prc.dto.InvtPrcTpCommonDTO;
import dream.invt.prc.dto.InvtPrcTpItemDetailDTO;
import dream.invt.prc.dto.InvtPrcTpItemListDTO;

/**
 * 구매절차 Item 상세 dao
 * @author  hyosung
 * @version $Id: InvtPrcTpItemDetailDAO.java,v 1.0 2015/12/04 08:10:27 hyosung Exp $
 * @since   1.0
 * @spring.bean id="invtPrcTpItemDetailDAOTarget"
 * @spring.txbn id="invtPrcTpItemDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtPrcTpItemDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements InvtPrcTpItemDetailDAO
{
    /**
     * detail find
     * @author hyosung
     * @version $Id: InvtPrcTpItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 hyosung Exp $
     * @since   1.0
     * 
     * @param invtPrcTpItemListDTO
     * @param invtPrcTpCommonDTO
     * @return
     */
    public InvtPrcTpItemDetailDTO findDetail(InvtPrcTpItemListDTO invtPrcTpItemListDTO, InvtPrcTpCommonDTO invtPrcTpCommonDTO,User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = invtPrcTpCommonDTO.getCompNo();
        
        query.append("SELECT                                                                                                                        ");
        query.append("         a.comp_no                        compNO                                                                              ");
        query.append("        ,a.ord_no                         ordNo                                                                               ");
        query.append("        ,dbo.SFACODE_TO_DESC(a.invt_proc_ltype,'INVT_PROC_LTYPE','USR','"+compNo+"','"+user.getLangId()+"')  invt_LTypeDesc       ");
        query.append("        ,dbo.SFACODE_TO_DESC(a.invt_proc_stype,'INVT_PROC_STYPE','USR','"+compNo+"','"+user.getLangId()+"')  invt_STypeDesc       ");
        query.append("        ,a.invt_proc_ltype                invt_LTypeNo                                                                        ");
        query.append("        ,a.invt_proc_stype                invt_STypeNo                                                                        ");
        query.append("        ,a.ref_depart                     invtRefDept                                                                         ");
        query.append("        ,a.ref_doc                        invtRefDoc                                                                          ");
        query.append("        ,a.is_use                         isUseDesc                                                                           ");
        query.append("        ,a.is_use                         isUse                                                                               ");
        query.append("        ,a.remark                         remark                                                                              ");
        query.append("        ,a.invtprcph_id                   invtPrcPhId                                                                         ");
        query.append("        ,a.invtprctp_id                   invtPrcTpId                                                                         ");
        query.append("        ,a.doc_prefix                   docPrefix                                                                             ");
        query.append("FROM TAINVTPRCPH a                                                                                                            ");
        query.append("WHERE 1=1                                                                                                                     ");
        query.append("       AND a.comp_no=?                                                                                                        ");
        query.append("       AND a.invtprcph_id=?                                                                                                   ");
        
        Object[] objects = new Object[] {
        		compNo
        		,invtPrcTpItemListDTO.getInvtPrcPhId()
        };
                                                                                                                                  
    
        InvtPrcTpItemDetailDTO invtPrcTpItemDetailDTO1 = 
        		(InvtPrcTpItemDetailDTO)getJdbcTemplate().query(query.toString(),objects,  new MwareExtractor(new InvtPrcTpItemDetailDTO()));
        
        return invtPrcTpItemDetailDTO1;
    }
    /**
     * detail update
     * @author hyosung
     * @version $Id: InvtPrcTpItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 hyosung Exp $
     * @since   1.0
     * 
     * @param invtPrcTpItemDetailDTO
     * @param invtPrcTpCommonDTO
     * @return
     */
    public int updateDetail(InvtPrcTpItemDetailDTO invtPrcTpItemDetailDTO, InvtPrcTpCommonDTO invtPrcTpCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

    	
    	query.append("UPDATE TAINVTPRCPH                           ");
    	query.append("SET                                          ");
    	query.append("        ord_no                  = ?          ");
    	query.append("        ,invt_proc_ltype        = ?          ");
    	query.append("        ,invt_proc_stype        = ?          ");
    	query.append("        ,ref_depart             = ?          ");
    	query.append("        ,ref_doc                = ?          ");
    	query.append("        ,is_use                 = ?          ");
    	query.append("        ,remark                 = ?          ");
    	query.append("        ,doc_prefix              = ?          ");
    	query.append("WHERE  comp_no                  = ?          ");
    	query.append("       AND invtprcph_id         = ?          ");
    	
    	
    	
    	Object[] objects = new Object[] {
    	        invtPrcTpItemDetailDTO.getOrdNo()
    	        ,invtPrcTpItemDetailDTO.getInvt_LTypeNo()
    	        ,invtPrcTpItemDetailDTO.getInvt_STypeNo()
    	        ,invtPrcTpItemDetailDTO.getInvtRefDept()
    	        ,invtPrcTpItemDetailDTO.getInvtRefDoc()
    	        ,invtPrcTpItemDetailDTO.getIsUse()
    	        ,invtPrcTpItemDetailDTO.getRemark()
    	        ,invtPrcTpItemDetailDTO.getDocPrefix()
    	        ,user.getCompNo()
    	        ,invtPrcTpItemDetailDTO.getInvtPrcPhId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author hyosung
     * @version $Id: InvtPrcTpItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 hyosung Exp $
     * @since   1.0
     * 
     * @param invtPrcTpItemDetailDTO
     * @param invtPrcTpCommonDTO
     * @return
     */
    public int insertDetail(InvtPrcTpItemDetailDTO invtPrcTpItemDetailDTO, InvtPrcTpCommonDTO invtPrcTpCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAINVTPRCPH(                                 ");
    	query.append("         comp_no                                         ");
    	query.append("         ,invtprcph_id                                   ");
    	query.append("         ,invtprctp_id                                   ");
    	query.append("         ,ord_no                                         ");
    	query.append("         ,invt_proc_ltype                                ");
    	query.append("         ,invt_proc_stype                                ");
    	query.append("         ,ref_depart                                     ");
    	query.append("         ,ref_doc                                        ");
    	query.append("         ,is_use                                         ");
    	query.append("         ,remark                                         ");
    	query.append("         ,doc_prefix                                      ");
    	query.append(")VALUES(                                                 ");
    	query.append("         ?                                               ");
    	query.append("         ,?                                              ");
    	query.append("         ,?                                              ");
        query.append("         ,?                                              ");
        query.append("         ,?                                              ");
        query.append("         ,?                                              ");
        query.append("         ,?                                              ");
        query.append("         ,?                                              ");
        query.append("         ,?                                              ");
        query.append("         ,?                                              ");
        query.append("         ,?                                              ");
    	query.append(")                                                        ");

    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,invtPrcTpItemDetailDTO.getInvtPrcPhId()
    			,invtPrcTpCommonDTO.getInvtPrcTpId()
    			,invtPrcTpItemDetailDTO.getOrdNo()
    			,invtPrcTpItemDetailDTO.getInvt_LTypeNo()
    			,invtPrcTpItemDetailDTO.getInvt_STypeNo()
    			,invtPrcTpItemDetailDTO.getInvtRefDept()
    			,invtPrcTpItemDetailDTO.getInvtRefDoc()
    			,invtPrcTpItemDetailDTO.getIsUse()
    			,invtPrcTpItemDetailDTO.getRemark()
    			,invtPrcTpItemDetailDTO.getDocPrefix()
    	};
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	return rtnValue;
    }
}