package dream.pers.priv.pgm.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;

import dream.pers.priv.pgm.dao.PersPrivUsrFieldDetailDAO;
import dream.pers.priv.pgm.dto.PersPrivUsrFieldCommonDTO;
import dream.pers.priv.pgm.dto.PersPrivUsrFieldDetailDTO;

/**
 * 화면별 필드 상세 dao
 * @author  kim21017
 * @version $Id: MaPgUsrFieldDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="persPrivUsrFieldDetailDAOTarget"
 * @spring.txbn id="persPrivUsrFieldDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PersPrivUsrFieldDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements PersPrivUsrFieldDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPgUsrFieldDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @param pgFieldId
     * @return
     */
    public PersPrivUsrFieldDetailDTO findDetail(PersPrivUsrFieldDetailDTO persPrivUsrFieldDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
        
    	query.append("SELECT                                                           ");
    	query.append("       '' seqNo,                                                 ");
    	query.append("       x.pgfield_id pgFieldId,                                   ");
    	query.append("       y.usrpgfield_id usrPgFieldId,                             ");
    	query.append("       x.page_id pageId,                                         ");
    	query.append("       (SELECT key_name                                          ");
    	query.append("        FROM TALANG                                              ");
    	query.append("        WHERE key_no = NVL(y.key_no,x.key_no)                    ");
    	query.append("          AND key_type = NVL(y.key_type, x.key_type)             ");
    	query.append("          AND lang = '"+user.getLangId()+"' ");
    	query.append("        ) keyName,                                               ");
    	query.append("       x.description fieldDesc,                                  ");
    	query.append("       NVL(y.display_yn, x.display_yn) displayYn,                ");
        query.append("       NVL(y.key_type, x.key_type)        keyType,               ");
        query.append("       NVL(y.key_no,x.key_no)       keyNo,                       ");
        query.append("       x.field_id  fieldId,                                      ");
        query.append("       NVL(x.check_yn,'N') checkYn,                              ");
        query.append("       SFACODE_TO_DESC(NVL(y.field_option, x.field_option),'FIELD_OPTION','SYS','','"+user.getLangId()+"') fieldOptionDesc,		");
        query.append("       NVL(y.field_option, x.field_option) fieldOption,          ");
        query.append("       NVL(y.group_key_type, x.group_key_type)        groupKeyType,               ");
        query.append("       NVL(y.group_key_no,x.group_key_no)       groupKeyNo,                       ");
    	query.append("       (SELECT key_name                                          ");
    	query.append("        FROM TALANG                                              ");
    	query.append("        WHERE key_no = NVL(y.group_key_no,x.group_key_no)                    ");
    	query.append("          AND key_type = NVL(y.group_key_type, x.group_key_type)             ");
    	query.append("          AND lang = '"+user.getLangId()+"' ");
    	query.append("        ) groupKeyName,                                          ");
        query.append("       SFACODE_TO_DESC(NVL(y.group_option, x.group_option),'GROUP_OPTION','SYS','','"+user.getLangId()+"') groupOptionDesc,		");
        query.append("       NVL(y.group_option, x.group_option) groupOption,          ");
    	query.append("       NVL(y.ord_no, x.ord_no) ordNo                             ");
    	query.append("FROM   TAPGFIELD x, TAUSRPGFIELD y                               ");
    	query.append("WHERE  x.pgfield_id = y.pgfield_id(+)                            ");
    	query.append("    AND  x.hidden_yn = 'N'                                       ");
    	query.getAndQuery("x.pgfield_id", persPrivUsrFieldDetailDTO.getPgFieldId());
        query.getAndQuery("y.user_id(+)", user.getUserId());
        if(persPrivUsrFieldDetailDTO.getPageId() != "")
            query.append("    AND  x.page_id = (SELECT a.page_id FROM TAPAGE a WHERE a.file_name = '"+persPrivUsrFieldDetailDTO.getPageId()+"')                                        ");
    	query.append("ORDER BY NVL(y.ord_no, x.ord_no)                                 ");

        PersPrivUsrFieldDetailDTO resultDTO = 
        		(PersPrivUsrFieldDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new PersPrivUsrFieldDetailDTO()));
        
        return resultDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPgUsrFieldDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param persPrivUsrFieldDetailDTO
     * @param maPgMngCommonDTO
     * @return
     */
    public int updateDetail(PersPrivUsrFieldDetailDTO persPrivUsrFieldDetailDTO, PersPrivUsrFieldCommonDTO persPrivUsrFieldCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAUSRPGFIELD SET	      ");
    	query.append("	ord_no		 	= ?,	      ");
    	query.append("	display_yn	 	= ?,	      ");
    	query.append("  key_no      	= ?,          ");
    	query.append("  field_option 	= ?,          ");
    	query.append("  key_type     	= ?,          ");
    	query.append("  group_key_no 	= ?,          ");
    	query.append("  group_option 	= ?,          ");
    	query.append("  group_key_type  = ?	          ");
    	query.append("WHERE usrpgfield_id = ?	      ");
    	
    	Object[] objects = new Object[] {
    			persPrivUsrFieldDetailDTO.getOrdNo(),
    			persPrivUsrFieldDetailDTO.getDisplayYn(),
    			persPrivUsrFieldDetailDTO.getKeyNo(),
    			persPrivUsrFieldDetailDTO.getFieldOption(),
    			persPrivUsrFieldDetailDTO.getKeyType(),
    			persPrivUsrFieldDetailDTO.getGroupKeyNo(),
    			persPrivUsrFieldDetailDTO.getGroupOption(),
    			persPrivUsrFieldDetailDTO.getGroupKeyType(),
    			persPrivUsrFieldDetailDTO.getUsrPgFieldId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPgUsrFieldDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param persPrivUsrFieldDetailDTO
     * @param maPgMngCommonDTO
     * @return
     */
    public int insertDetail(PersPrivUsrFieldDetailDTO persPrivUsrFieldDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAUSRPGFIELD                              ");
    	query.append("    (usrpgfield_id,    pgfield_id,                    ");
    	query.append("     comp_no,          user_id,                       ");
    	query.append("     ord_no,           display_yn,                    ");
    	query.append("     key_type,         key_no,                        ");
    	query.append("     field_option,     group_option,                  ");
    	query.append("     group_key_type,   group_key_no                   ");
    	query.append("    )VALUES                                           ");
    	query.append("    (?,                ?,                             ");
    	query.append("     ?,                ?,                             ");
    	query.append("     ?,                ?,                             ");
    	query.append("     ?,                ?,                             ");
    	query.append("     ?,                ?,                             ");
    	query.append("     ?,                ?                              ");
    	query.append("    )                                                 ");

    	
    	Object[] objects = new Object[] {
    	        persPrivUsrFieldDetailDTO.getUsrPgFieldId(),
    			persPrivUsrFieldDetailDTO.getPgFieldId(),
//    			persPrivUsrFieldDetailDTO.getCompNo(),
    			user.getCompNo(),
    			user.getUserId(),
    			persPrivUsrFieldDetailDTO.getOrdNo(),
    			persPrivUsrFieldDetailDTO.getDisplayYn(),
    			persPrivUsrFieldDetailDTO.getKeyType(),
    			persPrivUsrFieldDetailDTO.getKeyNo(),
    			persPrivUsrFieldDetailDTO.getFieldOption(),
    			persPrivUsrFieldDetailDTO.getGroupOption(),
    			persPrivUsrFieldDetailDTO.getGroupKeyType(),
    			persPrivUsrFieldDetailDTO.getGroupKeyNo()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
}