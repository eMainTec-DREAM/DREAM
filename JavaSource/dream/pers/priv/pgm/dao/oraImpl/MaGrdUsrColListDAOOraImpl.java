package dream.pers.priv.pgm.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.pers.priv.pgm.dao.MaGrdUsrColListDAO;
import dream.pers.priv.pgm.dto.MaGrdUsrCommonDTO;
import dream.pers.priv.pgm.dto.MaGrdUsrDetailDTO;

/**
 * Ä®·³ ¸ñ·Ï dao
 * @author  jung7126
 * @version $Id: MaGrdUsrColListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="maGrdUsrColListDAOTarget"
 * @spring.txbn id="maGrdUsrColListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaGrdUsrColListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaGrdUsrColListDAO
{
    /**
     * grid find
     * @author  jung7126
     * @version $Id: MaGrdUsrColListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @param maGrdUsrCommonDTO 
     * @since   1.0
     * 
     * @param page_id
     * @param user 
     * @return List
     */
    public List findColList(MaGrdUsrDetailDTO maGrdUsrDetailDTO, MaGrdUsrCommonDTO maGrdUsrCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                ");
        query.append("        '1' AS seqNo,                                                 ");
        query.append("        z.column_id AS colId,                                         ");
        query.append("       (SELECT a.key_name                                             ");
        query.append("        FROM   TALANG a                                               ");
        query.append("        WHERE  a.key_type = z.key_type                                ");
        query.append("          AND  a.lang = '"+user.getLangId()+"'                        ");
        query.append("          AND  a.key_no = z.key_no) columnDesc,                       ");
        query.append("        z.width,                                                      ");
        query.append("        z.ord_no AS ordNo,                                            ");
        query.append("        CASE UPPER(z.hidden) WHEN 'TRUE' THEN 'N' WHEN 'FALSE' THEN 'Y' ELSE UPPER(z.hidden) END hidden,                      ");
        query.append("        z.display_yn AS displayYn,                                    ");
        query.append("        '' AS usrPgGridColId,                                         ");
        query.append("        z.pggridcol_id AS pggridColId                                 ");
        query.append("FROM   TAPGGRID x ,TAPGGRIDCOL z                                      ");
        query.append("WHERE  x.pggrid_id = z.pggrid_id                                      ");
        query.append("  AND  z.system_col = 'N'                                             ");
        query.append("  AND  UPPER(z.hidden) NOT IN ('Y', 'TRUE')                           ");
        query.getAndQuery("x.pggrid_id", maGrdUsrDetailDTO.getPgGridId());
        query.getOrderByQuery("z.ord_no", maGrdUsrDetailDTO.getOrderBy(), "".equals(maGrdUsrDetailDTO.getDirection())?"des":maGrdUsrDetailDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(maGrdUsrDetailDTO.getIsLoadMaxCount(), maGrdUsrDetailDTO.getFirstRow()));
    }

    public List findUserColList(MaGrdUsrDetailDTO maGrdUsrDetailDTO, MaGrdUsrCommonDTO maGrdUsrCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                                                    ");
        query.append("        '1' AS seqNo,                                                                                                     ");
        query.append("        x.column_id AS colId,                                                                                             ");
        query.append("       (SELECT a.key_name                                                                                                 ");
        query.append("        FROM   TALANG a                                                                                                   ");
        query.append("        WHERE  a.key_type = NVL(y.key_type,x.key_type)                                                                    ");
        query.append("          AND  a.lang = '"+user.getLangId()+"'                                                                            ");
        query.append("          AND  a.key_no = NVL(y.key_no,x.key_no)) columnDesc,                                                             ");
        query.append("        NVL(y.width , x.width) WIDTH,                                                                                     ");
        query.append("        NVL(y.ord_no , x.ord_no) AS ordNo,                                                                                ");
        query.append("        NVL(UPPER(y.display_yn), UPPER(x.display_yn)) AS displayYn,                                                       ");
        query.append("        CASE w.auth_limit_type                                                                                            ");
        query.append("        WHEN 'NV' THEN 'true'                                                                                             ");
        query.append("        ELSE                                                                                                              ");
        query.append("            CASE NVL(UPPER(y.hidden), UPPER(x.hidden)) WHEN 'FALSE' THEN                                                  ");
        query.append("                CASE NVL(UPPER(y.display_yn), UPPER(x.display_yn)) WHEN 'Y' THEN 'false' WHEN 'N' THEN 'true' END         ");
        query.append("            WHEN 'N' THEN                                                                                                 ");
        query.append("                CASE NVL(UPPER(y.display_yn), UPPER(x.display_yn)) WHEN 'Y' THEN 'false' WHEN 'N' THEN 'true' END         ");
        query.append("            WHEN 'TRUE' THEN 'true'                                                                                       ");
        query.append("            WHEN 'Y' THEN 'true' END                                                                                      ");
        query.append("        END HIDDEN,                                                                                                       ");
        query.append("        y.usrpggridcol_id AS usrPgGridColId,                                                                              ");
        query.append("        x.pggridcol_id AS pggridColId                                                                                     ");
        query.append("FROM    TAPGGRID a INNER JOIN TAPGGRIDCOL x ON a.pggrid_id = x.pggrid_id                                                  ");
        query.append("            INNER JOIN TAPAGE c ON a.page_id = c.page_id                                                                  ");
        query.append("            LEFT OUTER JOIN TAUSRPGGRID z ON x.pggrid_id = z.pggrid_id                                                    ");
        query.getAndKeyQuery("z.user_id", user.getUserId());
        query.getAndQuery("z.comp_no", user.getCompNo());
        query.append("            LEFT OUTER JOIN TAUSRPGGRIDCOL y ON  x.pggridcol_id = y.pggridcol_id AND z.usrpggrid_id = y.usrpggrid_id      ");
        query.append("            LEFT OUTER JOIN TAUGPGRIDCOLAU w ON c.page_id = w.page_id AND w.grid_obj_id = a.grid_obj_id AND x.column_id = w.column_id   ");
        query.getAndKeyQuery("w.usrgrp_id", user.getUsrgrpId());
        query.append("            WHERE 1 = 1                                                                                                   ");
        query.getAndQuery("a.grid_obj_id", maGrdUsrCommonDTO.getGridObjId());
        query.getAndQuery("c.file_name", maGrdUsrCommonDTO.getPageId());
        query.append("  and CASE w.auth_limit_type                                                                                              ");
        query.append("        WHEN 'NV' THEN 'true'                                                                                             ");
        query.append("        ELSE                                                                                                              ");
        query.append("            CASE NVL(UPPER(y.hidden), UPPER(x.hidden)) WHEN 'FALSE' THEN                                                  ");
        query.append("                'false'         ");
        query.append("            WHEN 'N' THEN                                                                                                 ");
        query.append("                'false'         ");
        query.append("            WHEN 'TRUE' THEN 'true'                                                                                       ");
        query.append("            WHEN 'Y' THEN 'true' END                                                                                      ");
        query.append("        END <> 'true' ");
        query.getOrderByQuery("x.ord_no", maGrdUsrDetailDTO.getOrderBy(), "".equals(maGrdUsrDetailDTO.getDirection())?"des":maGrdUsrDetailDTO.getDirection());

//        
//        query.append("SELECT                                                                ");
//        query.append("        '1' AS seqNo,                                                 ");
//        query.append("        z.column_id AS colId,                                         ");
//        query.append("       (SELECT a.key_name                                             ");
//        query.append("        FROM   TALANG a                                               ");
//        query.append("        WHERE  a.key_type = z.key_type                                ");
//        query.append("          AND  a.lang = '"+user.getLangId()+"'                        ");
//        query.append("          AND  a.key_no = z.key_no) columnDesc,                       ");
//        query.append("        z.width,                                                      ");
//        query.append("        z.ord_no AS ordNo,                                            ");
//        query.append("        z.displayYn AS displayYn,                                     ");
//        query.append("        CASE UPPER(z.hidden) WHEN 'TRUE' THEN 'N' WHEN 'FALSE' THEN 'Y' ELSE UPPER(z.hidden) END hidden,                      ");
//        query.append("        z.usrpggridcol_id AS usrPgGridColId,                          ");
//        query.append("        z.pggridcol_id AS pggridColId                                 ");
//        query.append("FROM   TAUSRPGGRID x ,(SELECT                                              ");
//        query.append("                              x.pggridcol_id,                              ");
//        query.append("                              x.pggrid_id,                                 ");
//        query.append("                              x.column_id,                                 ");
//        query.append("                              NVL(y.ord_no, x.ord_no) ord_no,              ");
//        query.append("                              NVL(y.key_no,x.key_no) key_no,               ");
//        query.append("                              NVL(y.width , x.width) width,                ");
//        query.append("                              x.TYPE,                                      ");
//        query.append("                              NVL(y.align, x.align) align,                 ");
//        query.append("                              x.SORT,                                      ");
//        query.append("                              NVL(y.hidden, x.hidden) hidden,              ");
//        query.append("                              NVL(y.display_yn, x.display_yn) displayYn,   ");
//        query.append("                              NVL(y.key_type,x.key_type) key_type,         ");
//        query.append("                              x.system_col,                                ");
//        query.append("                              y.usrpggridcol_id,                           ");
//        query.append("                              y.usrpggrid_id                               ");
//        query.append("                      FROM   TAPGGRIDCOL x, (SELECT * FROM TAUSRPGGRIDCOL  ");
//		query.append("                                              WHERE usrpggrid_id IN (      ");
//		query.append("                                                    SELECT usrpggrid_id FROM TAUSRPGGRID          ");
//		query.append("                                                    WHERE (comp_no   =  '"+user.getCompNo()+"'    ");
//        query.append("                                                    AND user_id = '"+user.getUserId()+"' )        ");
//		query.append("                                                    OR usrpggrid_id is null)) y                   ");
//        query.append("                      WHERE  x.pggridcol_id = y.pggridcol_id(+)            ");
//        query.append("                        AND  x.system_col = 'N'                            ");
//        query.append("                        AND  UPPER(x.hidden) NOT IN ('Y', 'TRUE')          ");
//        query.append("                      ) z                                                  ");
//        query.append("WHERE  x.pggrid_id = z.pggrid_id                                           ");
//        query.append("  AND  x.user_id   = '"+user.getUserId()+"'                                ");
//        query.append("  AND  x.comp_no   =  '"+user.getCompNo()+"'                               ");
//        query.getAndQuery("x.usrpggrid_id", maGrdUsrDetailDTO.getUsrPgGridId());

        return getJdbcTemplate().queryForList(query.toString(maGrdUsrDetailDTO.getIsLoadMaxCount(), maGrdUsrDetailDTO.getFirstRow()));
    }

    @Override
    public String findColCount(MaGrdUsrDetailDTO maGrdUsrDetailDTO, MaGrdUsrCommonDTO maGrdUsrCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                ");
        query.append("        COUNT(1)                                                      ");
        query.append("FROM   TAPGGRID x ,TAPGGRIDCOL z                                      ");
        query.append("WHERE  x.pggrid_id = z.pggrid_id                                      ");
        query.append("  AND  z.system_col = 'N'                                             ");
        query.getAndQuery("x.pggrid_id", maGrdUsrDetailDTO.getPgGridId());
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
    @Override
    public String findUserColCount(MaGrdUsrDetailDTO maGrdUsrDetailDTO, MaGrdUsrCommonDTO maGrdUsrCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                                                    ");
        query.append("        COUNT(1)                                                                                                          ");
        query.append("FROM    TAPGGRID a INNER JOIN TAPGGRIDCOL x ON a.pggrid_id = x.pggrid_id                                                  ");
        query.append("            INNER JOIN TAPAGE c ON a.page_id = c.page_id                                                                  ");
        query.append("            LEFT OUTER JOIN TAUSRPGGRID z ON x.pggrid_id = z.pggrid_id                                                    ");
        query.getAndKeyQuery("z.user_id", user.getUserId());
        query.getAndQuery("z.comp_no", user.getCompNo());
        query.append("            LEFT OUTER JOIN TAUSRPGGRIDCOL y ON  x.pggridcol_id = y.pggridcol_id AND z.usrpggrid_id = y.usrpggrid_id      ");
        query.append("            LEFT OUTER JOIN TAUGPGRIDCOLAU w ON c.page_id = w.page_id AND w.grid_obj_id = a.grid_obj_id AND x.column_id = w.column_id   ");
        query.getAndKeyQuery("w.usrgrp_id", user.getUsrgrpId());
        query.append("            WHERE 1 = 1                                                                                                   ");
        query.getAndQuery("a.grid_obj_id", maGrdUsrCommonDTO.getGridObjId());
        query.getAndQuery("c.file_name", maGrdUsrCommonDTO.getPageId());
        query.append("  and CASE w.auth_limit_type                                                                                              ");
        query.append("        WHEN 'NV' THEN 'true'                                                                                             ");
        query.append("        ELSE                                                                                                              ");
        query.append("            CASE NVL(UPPER(y.hidden), UPPER(x.hidden)) WHEN 'FALSE' THEN                                                  ");
        query.append("                'false'         ");
        query.append("            WHEN 'N' THEN                                                                                                 ");
        query.append("                'false'         ");
        query.append("            WHEN 'TRUE' THEN 'true'                                                                                       ");
        query.append("            WHEN 'Y' THEN 'true' END                                                                                      ");
        query.append("        END <> 'true' ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }

}