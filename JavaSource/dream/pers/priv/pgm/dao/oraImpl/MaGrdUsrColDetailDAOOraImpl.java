package dream.pers.priv.pgm.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.consult.program.page.dto.MaGrdUsrColDetailDTO;
import dream.pers.priv.pgm.dao.MaGrdUsrColDetailDAO;
import dream.pers.priv.pgm.dto.MaGrdUsrCommonDTO;

/**
 * Į�� �� dao
 * @author  jung7126
 * @version $Id: MaGrdUsrColDetailDAO.java,v 1.0 2015/12/04 08:10:27 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="maGrdUsrColDetailDAOTarget"
 * @spring.txbn id="maGrdUsrColDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaGrdUsrColDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaGrdUsrColDetailDAO
{
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaGrdUsrColDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @param grdColId
     * @param user 
     * @return
     */
    public MaGrdUsrColDetailDTO findDetail(String pgGridColId, String usrGridColId, User user)
    {
    	QueryBuffer query = new QueryBuffer();
        
    	query.append("SELECT                                                                       ");
    	query.append("       '' seqNo,                                                             ");
    	query.append("       '' isDelCheck,                                                        ");
    	query.append("       column_id columnId,                                                   ");
    	query.append("       (SELECT a.key_name                                                    ");
    	query.append("        FROM   TALANG a                                                      ");
    	query.append("        WHERE  a.key_type = x.key_type                                       ");
    	query.append("          AND  a.lang = '"+user.getLangId()+"'                               ");
    	query.append("          AND a.key_no = x.key_no) columnDesc,                               ");
    	query.append("       x.key_no keyNo,                                                       ");
    	query.append("       x.type,                                                               ");
    	query.append("       SFACODE_TO_DESC(x.type,'COLUMN_TYPE','SYS','','"+user.getLangId()+"') typeDesc,                ");
    	query.append("       x.width,                                                              ");
    	query.append("       x.ord_no ordNo,                                                       ");
    	query.append("       x.hidden,                                                             ");
    	query.append("       x.display_yn displayYn,                                               ");
    	query.append("       x.key_type keyType,                                                   ");
    	query.append("       x.align,                                                              ");
    	query.append("       x.system_col systemCol,                                               ");
    	query.append("       SFACODE_TO_DESC(x.align,'ALIGN_TYPE','SYS','','"+user.getLangId()+"') alignDesc,               ");
    	query.append("       x.pggridcol_id pggridcolId                                            ");
    	query.append("FROM   TAPGGRIDCOL x                                                         ");
        query.append("WHERE  x.pggridcol_id 		= '"+pgGridColId+"'							   ");
    
        MaGrdUsrColDetailDTO maGrdUsrColDetailDTO = 
        		(MaGrdUsrColDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaGrdUsrColDetailDTO()));
        
        return maGrdUsrColDetailDTO;
    }
    /**
     * detail update
     * @author jung7126
     * @version $Id: MaGrdUsrColDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdUsrColDetailDTO
     * @param maGrdUsrCommonDTO
     * @return
     */
    public int updateDetail(MaGrdUsrColDetailDTO maGrdUsrColDetailDTO, MaGrdUsrCommonDTO maGrdUsrCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAUSRPGGRIDCOL SET	");
    	query.append("	ord_no	         = ?,	");
    	query.append("	width		     = ?,	");
    	query.append("  align            = ?,   ");
    	query.append("  display_yn       = ?,   ");
    	query.append("  key_type         = ?,   ");
    	query.append("  key_no           = ?    ");
    	query.append("WHERE usrpggridcol_id = ?	");
    	
    	Object[] objects = new Object[] {
    	        maGrdUsrColDetailDTO.getOrdNo(),
    	        maGrdUsrColDetailDTO.getWidth(),
    	        maGrdUsrColDetailDTO.getAlign(),
    	        maGrdUsrColDetailDTO.getDisplayYn(),
    	        maGrdUsrColDetailDTO.getKeyType(),
    	        maGrdUsrColDetailDTO.getKeyNo(),
    	        maGrdUsrColDetailDTO.getUsrPgGridColId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaGrdUsrColDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdUsrColDetailDTO
     * @param maGrdUsrCommonDTO
     * @param user 
     * @return
     */
    public int insertDetail(MaGrdUsrColDetailDTO maGrdUsrColDetailDTO, MaGrdUsrCommonDTO maGrdUsrCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAUSRPGGRIDCOL			");
    	query.append("	(usrpggridcol_id,	pggridcol_id,   ");
    	query.append("	 ord_no,		width,				");
    	query.append("	 display_yn,    align,              ");
    	query.append("   usrpggrid_id,  key_type,           ");
    	query.append("   key_no                             ");
    	query.append("	)	VALUES							");
    	query.append("	(?,				?,					");
    	query.append("   ?,             ?,                  ");
    	query.append("   ?,             ?,                  ");
    	query.append("   ?,             ?,                  ");
    	query.append("	 ? 									");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    	        maGrdUsrColDetailDTO.getUsrPgGridColId(),
    	        maGrdUsrColDetailDTO.getPgGridColId(),
    	        maGrdUsrColDetailDTO.getOrdNo(),
    	        maGrdUsrColDetailDTO.getWidth(),
    	        maGrdUsrColDetailDTO.getDisplayYn(),
    	        maGrdUsrColDetailDTO.getAlign(),
    	        maGrdUsrColDetailDTO.getUsrPgGridId(),
    	        maGrdUsrColDetailDTO.getKeyType(),
    	        maGrdUsrColDetailDTO.getKeyNo()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    public MaGrdUsrColDetailDTO findUsrDetail(String pgGridId, String usrPgGridColId, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                            ");
        query.append("       '' seqNo,                                                                  ");
        query.append("       '' isDelCheck,                                                             ");
        query.append("       column_id columnId,                                                        ");
        query.append("       (SELECT a.key_name                                                         ");
        query.append("        FROM   TALANG a                                                           ");
        query.append("        WHERE  a.key_type = NVL(y.key_type,x.key_type)                            ");
        query.append("          AND  a.lang = '"+user.getLangId()+"'                                    ");
        query.append("          AND a.key_no = NVL(y.key_no,x.key_no)) columnDesc,                      ");
        query.append("       NVL(y.key_no,x.key_no) keyNo,                                              ");
        query.append("       x.TYPE,                                                                    ");
        query.append("       SFACODE_TO_DESC(x.TYPE,'COLUMN_TYPE','SYS','','"+user.getLangId()+"') typeDesc, ");
        query.append("       NVL(y.width, x.width) width,                                               ");
        query.append("       NVL(y.ord_no, x.ord_no) ordNo,                                             ");
        query.append("       NVL(y.hidden, x.hidden) hidden,                                            ");
        query.append("       NVL(y.display_yn, x.display_yn) displayYn,                                 ");
        query.append("       x.key_type keyType,                                                        ");
        query.append("       NVL(y.align, x.align) align,                                               ");
        query.append("       x.system_col systemCol,                                                    ");
        query.append("       SFACODE_TO_DESC(NVL(y.align, x.align),'ALIGN_TYPE','SYS','','"+user.getLangId()+"') alignDesc, ");
        query.append("       y.usrpggridcol_id AS usrPgGridColId,                                       ");
        query.append("       x.pggridcol_id AS pggridcolId                                              ");
        query.append(" FROM   TAPGGRIDCOL x, TAUSRPGGRIDCOL y                                           ");
        query.append(" WHERE  x.pggridcol_id = y.pggridcol_id(+)                                        ");
        query.append("   AND  y.usrpggridcol_id = '"+usrPgGridColId+"'                                  ");

    
        MaGrdUsrColDetailDTO maGrdUsrColDetailDTO = 
                (MaGrdUsrColDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaGrdUsrColDetailDTO()));
        
        return maGrdUsrColDetailDTO;
    }
}