package dream.invt.list.dao.oraImpl;

import java.io.IOException;
import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.invt.list.dao.InvtDetailDAO;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtDetailDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * 상세 dao
 * 
 * @author kim21017
 * @version $Id: InvtDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="invtDetailDAOTarget"
 * @spring.txbn id="invtDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements InvtDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: InvtDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtCommonDTO
     * @return
     */
    public InvtDetailDTO findDetail(InvtCommonDTO invtCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                                         ");
        query.append("       ''                                                                                  SEQNO               ");
        query.append("       ,''                                                                                 ISDELCHECK          ");
        query.append("       ,invtlist_no                                                                        INVTLISTNO          ");
        query.append("       ,x.description                                                                      DESCRIPTION         ");
        query.append("       ,invt_categ                                                                         INVTCATEG           ");
        query.append("       ,SFACODE_TO_DESC(invt_categ,'INVT_CATEG','SYS','','"+user.getLangId()+"')           INVTCATEGDESC       ");
        query.append("       ,(SELECT aa.full_desc FROM TAEQCTG aa                                                                   ");
        query.append("         WHERE aa.comp_no = x.comp_no and aa.eqctg_id = x.eqctg_id)                        EQCTGDESC           ");
        query.append("       ,(SELECT aa.full_desc FROM TAEQLOC aa                                                                   ");
        query.append("         WHERE aa.comp_no = x.comp_no AND aa.eqloc_id = x.eqloc_id)                        EQLOCDESC           ");
        query.append("       ,x.eqloc_id                                                                         EQLOCID             ");
        query.append("       ,x.eqctg_id                                                                         EQCTGID             ");
        query.append("       ,x.equip_id                                                                         EQUIPID             ");
        query.append("       ,y.description                                                                      EQUIPDESC           ");
        query.append("       ,x.dept_id                                                                          DEPTID              ");
        query.append("       ,(SELECT a.description FROM TADEPT a                                                                    ");
        query.append("         WHERE  a.comp_no = x.comp_no and a.dept_id = x.dept_id)                           DEPTDESC            ");
        query.append("       ,x.usage_dept                                                                       usageDept           ");
        query.append("       ,(SELECT a.description FROM TADEPT a                                                                    ");
        query.append("         WHERE  a.comp_no = x.comp_no and a.dept_id = x.usage_dept)                        usageDeptDesc       ");
        query.append("       ,emp_id                                                                             EMPID               ");        
        query.append("       ,(SELECT a.emp_name FROM TAEMP a                                                                        ");
        query.append("         WHERE a.comp_no = x.comp_no and a.emp_id = x.emp_id)                              EMPDESC             ");
        query.append("       ,plan_amt                                                                           PLANAMT             ");
        query.append("       ,cont_amt                                                                           contAmt             ");
        query.append("       ,vendor_id                                                                          vendorId            ");
        query.append("       ,(SELECT a.description FROM TAVENDOR a                                                                  ");
        query.append("         WHERE a.comp_no = x.comp_no and a.vendor_id = x.vendor_id)                        vendorDesc          ");
        query.append("       ,plan_sdate                                                                         PLANSDATE           ");
        query.append("       ,plan_edate                                                                         PLANEDATE           ");
        query.append("       ,invtlist_status                                                                    INVTLISTSTATUS      ");
        query.append("       ,SFACODE_TO_DESC(invtlist_status,'INVTLIST_STATUS','SYS','','"+user.getLangId()+"') INVTLISTSTATUSDESC  ");
        query.append("       ,invt_Type                                                                          INVTTYPEID          ");
        query.append("       ,SFACODE_TO_DESC(invt_Type,'INVT_TYPE','SYS','','"+user.getLangId()+"')             INVTTYPEDESC        ");
        query.append("       ,invtlist_id                                                                        INVTLISTID          ");
        query.append("       ,invtprctp_id                                                                       INVTPRCTPID         ");
        query.append("       ,(SELECT a.description FROM TAINVTPRCTP a                                                               ");
        query.append("        WHERE a.comp_no = x.comp_no and a.invtprctp_id = x.invtprctp_id)                   INVTDESC            ");
        query.append("       ,end_date                                                                           ENDDATE             ");
        query.append("       ,x.remark as                                                                        REMARK              ");
        query.append("       ,(SELECT SUM(NVL(ACTUAL_AMT,0)) FROM TAINVTPHASE                                                        ");
        query.append("        WHERE comp_no=x.comp_no AND invtlist_id=x.invtlist_id)                             INVTAMT             ");
        query.append("       ,x.plant                                                                            PLANTID             ");
        query.append("       ,(SELECT description                                                                                    ");
        query.append("         FROM TAPLANT                                                                                          ");
        query.append("         WHERE comp_no = x.comp_no                                                                             ");
        query.append("           AND plant = x.plant)                                                            PLANTDESC           ");
        query.append("       ,x.invt_kind                                                                        INVTKINDID          ");
        query.append("       ,SFACODE_TO_DESC(x.invt_kind,'INVT_KIND','SYS','','"+user.getLangId()+"')           INVTKINDDESC        ");
        query.append("       ,(SELECT MAX(a.woreq_id) FROM TAWOREQRES a WHERE a.comp_no = x.comp_no AND a.invtlist_id = x.invtlist_id)  woReqId ");
        query.append("FROM   TAINVTLIST x LEFT OUTER JOIN TAEQUIPMENT y                                                              ");
        query.append("                  ON x.equip_id = y.equip_id  and x.comp_no = y.comp_no                                        ");
        query.append("WHERE  1=1                                                                                                     ");
        query.append("  AND  x.invtlist_id = ?                                                                                       ");
        query.append("  AND  x.comp_no = ?                                                                                           ");

        Object[] objects = new Object[] {
                invtCommonDTO.getInvtlistId()
                ,user.getCompNo()
        };
        
        InvtDetailDTO invtDetailDTO = 
                (InvtDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new InvtDetailDTO()));
        
        return invtDetailDTO;
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: InvtDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtDetailDTO
     * @return
     */
    public int insertDetail(InvtDetailDTO invtDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("INSERT INTO TAINVTLIST                                    ");
        query.append("    (  comp_no        , invtlist_id        , invtlist_no  ");
        query.append("     , description    , invtlist_status    , invtprctp_id ");
        query.append("     , eqloc_id       , eqctg_id           , invt_categ   ");
        query.append("     , equip_id       , dept_id            , emp_id       ");
        query.append("     , plan_amt       , plan_sdate         , plan_edate   ");
        query.append("     , end_date       , remark             , invt_type    ");
        query.append("     , plant          , invt_kind          , usage_dept   ");
        query.append("    )    VALUES                                           ");
        query.append("    (  ?              , ?                  , ?            ");
        query.append("     , ?              , ?                  , ?            ");
        query.append("     , ?              , ?                  , ?            ");
        query.append("     , ?              , ?                  , ?            ");
        query.append("     , ?              , ?                  , ?            ");
        query.append("     , ?              , ?                  , ?            ");
        query.append("     , ?              , ?                  , ?            ");
        query.append("    )                                                     ");
        
        Object[] objects = new Object[] {
                  user.getCompNo()
                , invtDetailDTO.getInvtlistId()
                , invtDetailDTO.getInvtlistNo()
                , invtDetailDTO.getDescription()
                , invtDetailDTO.getInvtlistStatus()
                , invtDetailDTO.getInvtprctpId()
                , invtDetailDTO.getEqlocId()
                , invtDetailDTO.getEqctgId()
                , invtDetailDTO.getInvtCateg()
                , invtDetailDTO.getEquipId()
                , invtDetailDTO.getDeptId()
                , invtDetailDTO.getEmpId()
                , invtDetailDTO.getPlanAmt()
                , invtDetailDTO.getPlanSdate()
                , invtDetailDTO.getPlanEdate()
                , invtDetailDTO.getEndDate()
                , invtDetailDTO.getRemark()
                , invtDetailDTO.getInvtTypeId()
                , invtDetailDTO.getPlantId()
                , invtDetailDTO.getInvtKindId()
                , invtDetailDTO.getUsageDept()
        };
        return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: InvtDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtDetailDTO
     * @return
     * @throws IOException 
     */
    public int updateDetail(InvtDetailDTO invtDetailDTO, User user) throws IOException
    {
        QueryBuffer query = new QueryBuffer();

        query.append("UPDATE TAINVTLIST SET                     ");
        query.append("       description            = ?,        ");
        query.append("       invtlist_status        = ?,        ");
        query.append("       invtprctp_id           = ?,        ");
        query.append("       eqloc_id               = ?,        ");
        query.append("       eqctg_id               = ?,        ");
        query.append("       invt_categ             = ?,        ");
        query.append("       equip_id               = ?,        ");
        query.append("       dept_id                = ?,        ");
        query.append("       emp_id                 = ?,        ");
        query.append("       plan_amt               = ?,        ");
        query.append("       plan_sdate             = ?,        ");
        query.append("       plan_edate             = ?,        ");
        query.append("       end_date               = ?,        ");
        query.append("       remark                 = ?,        ");
        query.append("       invt_type              = ?,        ");
        query.append("       invt_kind              = ?,        ");
        query.append("       usage_dept             = ?,        ");
        query.append("       plant                  = ?         ");
        query.append("WHERE  invtlist_id            = ?         ");
        query.append("  AND  comp_no                = ?         ");

        
        Object[] objects = new Object[] {
                  invtDetailDTO.getDescription()
                , invtDetailDTO.getInvtlistStatus()
                , invtDetailDTO.getInvtprctpId()
                , invtDetailDTO.getEqlocId()
                , invtDetailDTO.getEqctgId()
                , invtDetailDTO.getInvtCateg()
                , invtDetailDTO.getEquipId()
                , invtDetailDTO.getDeptId()
                , invtDetailDTO.getEmpId()
                , invtDetailDTO.getPlanAmt()
                , invtDetailDTO.getPlanSdate()
                , invtDetailDTO.getPlanEdate()
                , invtDetailDTO.getEndDate()
                , invtDetailDTO.getRemark()
                , invtDetailDTO.getInvtTypeId()
                , invtDetailDTO.getInvtKindId()
                , invtDetailDTO.getUsageDept()
                , invtDetailDTO.getPlantId()
                , invtDetailDTO.getInvtlistId()
                , user.getCompNo()
        };
        
//        CommonUtil.jdbcConnUpdate("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://ora.emaintec.com:1433;databaseName=dream", 
//                "dream", "dream", query.toString(),objects);
//
//        return 1;
        return getJdbcTemplate().update(query.toString(), objects);
    }

    @Override
    public int insertPhase(InvtDetailDTO invtDetailDTO, User user) {
        QueryBuffer query = new QueryBuffer();

        query.append("  INSERT INTO TAINVTPHASE (comp_no,       invtphase_id,   invtlist_id,            ");
        query.append("                      invtprcph_id,   ord_no,         invt_proc_ltype,        ");
        query.append("                      invt_proc_stype,ref_depart,     ref_doc,                ");
        query.append("                      invtphase_status, invt_doc_no )                                     ");
        query.append("  SELECT ?,                   SQAINVTPHASE_ID.nextval,    ?,                  ");
        query.append("         invtprcph_id,        y.ord_no,                   y.invt_proc_ltype,  ");
        query.append("         y.invt_proc_stype,   y.ref_depart,               y.ref_doc,          ");
        query.append("         'W',                     y.doc_prefix                                        ");
        query.append("  FROM   TAINVTPRCPH y    ");
        query.append("  WHERE  y.is_use = 'Y'   ");
        query.append("    AND  invtprctp_id = ? ");


        
        Object[] objects = new Object[] {
                user.getCompNo(),
                invtDetailDTO.getInvtlistId(),
                invtDetailDTO.getInvtprctpId()
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }

    @Override
    public void deletePhase(InvtDetailDTO invtDetailDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TAINVTPHASE                                              ");
        query.append("WHERE  comp_no        = ?                                       ");
        query.append("  AND  invtlist_id    = ?                                       ");      
        
        Object[] objects = new Object[] {   
                user.getCompNo(),
                invtDetailDTO.getInvtlistId()
                };
        
        this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAINVTLIST SET                    ");
        query.append("       invtlist_status   = ?             ");
        query.append("WHERE  invtlist_id       = ?             ");
        query.append("  AND  comp_no    = ?                              ");      
        
        Object[] objects = new Object[] {
                appReqDetailDTO.getParentStatus(),
                appReqDetailDTO.getObjectId(),
                user.getCompNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    public int updateInvtListStatus(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAINVTLIST SET invtlist_status = (                                             ");
        query.append("        SELECT CASE WHEN                                                              ");
        query.append("            (SELECT COUNT(*)                                                          ");
        query.append("            FROM TAINVTPHASE                                                          ");
        query.append("            WHERE invtlist_id = ?                                                     ");
        query.append("            AND comp_no = ?                                                           ");
        query.append("            AND invtphase_status ='C'                                                 ");
        query.append("            ) = (SELECT COUNT(*)                                                      ");
        query.append("                FROM TAINVTPHASE                                                      ");
        query.append("                WHERE invtlist_id = ?                                                 ");
        query.append("                AND comp_no = ?                                                       ");
        query.append("                ) THEN 'C'                                                            ");  //진행항목이 모두 완료되면 투자도 완료 
//        query.append("            WHEN                                                                      ");
//        query.append("            (SELECT COUNT(*)                                                          ");
//        query.append("            FROM TAINVTPHASE                                                          ");
//        query.append("            WHERE invtlist_id = ?                                                          ");
//        query.append("            AND comp_no = ?                                           ");
//        query.append("            AND (invtphase_status ='W' OR invtphase_status ='DA')                  ");
//        query.append("            ) = (SELECT COUNT(*)                                                      ");
//        query.append("                FROM TAINVTPHASE                                                      ");
//        query.append("                WHERE invtlist_id = ?                                                      ");
//        query.append("                AND comp_no = ?                                               ");
//        query.append("                ) THEN 'W'                                                                ");
        query.append("            WHEN                                                                      ");
        query.append("            (SELECT COUNT(*)                                                          ");
        query.append("            FROM TAINVTPHASE                                                          ");
        query.append("            WHERE invtlist_id = ?                                                     ");
        query.append("            AND comp_no = ?                                                           ");
        query.append("            AND invtphase_status !='W'                                                ");
        query.append("            AND invtphase_status !='DA') > 0                                          ");
        query.append("                THEN 'P'                                                              "); //하나라도 진행이면 투자도 진행
        query.append("            END                                                                       ");
        query.append("        FROM DUAL                                                                     ");
        query.append("        )                                                                             ");
        query.append("        WHERE invtlist_id = ?                                                         ");
        query.append("        AND comp_no = ?                                                               ");
        
        Object[] objects = new Object[] {
                appReqDetailDTO.getObjectId()
                ,user.getCompNo()
                ,appReqDetailDTO.getObjectId()
                ,user.getCompNo()
//                ,appReqDetailDTO.getObjectId()
//                ,user.getCompNo()
//                ,appReqDetailDTO.getObjectId()
//                ,user.getCompNo()
                ,appReqDetailDTO.getObjectId()
                ,user.getCompNo()
                ,appReqDetailDTO.getObjectId()
                ,user.getCompNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    @Override
    public String getInvtListId(String invtPhaseId, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT invtlist_id                 ");
        query.append("FROM TAINVTPHASE              ");
        query.append("WHERE invtphase_id = ?        ");
        query.append("AND comp_no = ?              ");
        
        Object[] objects = new Object[] {
                invtPhaseId
                ,user.getCompNo()
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    }

    public String copyDetail(InvtCommonDTO invtCommonDTO, InvtDetailDTO invtDetailDTO, User user) throws Exception 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("INSERT INTO TAINVTLIST                                    ");
        query.append("    (  comp_no        , invtlist_id        , invtlist_no  ");
        query.append("     , description    , invtlist_status    , invtprctp_id ");
        query.append("     , eqloc_id       , eqctg_id           , invt_categ   ");
        query.append("     , equip_id       , dept_id            , emp_id       ");
        query.append("     , plan_amt       , plan_sdate         , plan_edate   ");
        query.append("     , end_date       , remark             , invt_type    ");
        query.append("     , plant          , invt_kind          , usage_dept ) ");
        query.append("SELECT                                                    ");
        query.append("   comp_no            , ?                  , ?            ");
        query.append("     , description || '-Copied'   , ?      , invtprctp_id ");
        query.append("     , eqloc_id       , eqctg_id           , invt_categ   ");
        query.append("     , equip_id       , dept_id            , emp_id       ");
        query.append("     , plan_amt       , plan_sdate         , plan_edate   ");
        query.append("     , end_date       , remark             , invt_type    ");
        query.append("     , plant          , invt_kind          , usage_dept   ");
        query.append("FROM TAINVTLIST                       ");
        query.append("WHERE comp_no      = ?                ");
        query.append("  AND invtlist_id  = ?                ");

        Object[] objects = new Object[] {
                  invtDetailDTO.getInvtlistId()
                , invtDetailDTO.getInvtlistId()
                , "W"
                , user.getCompNo()
                , invtDetailDTO.getOldInvtlistId()
        };
        
        getJdbcTemplate().update(query.toString(), objects);

        return "0";
    }

    @Override
    public int cancelInvtStatus(InvtDetailDTO invtDetailDTO, User user) throws IOException {
        QueryBuffer query = new QueryBuffer();

        query.append("UPDATE TAINVTLIST SET                     ");
        query.append("       invtlist_status          = ?       ");
        query.append("WHERE  invtlist_id              = ?       ");
        query.append("  AND  comp_no                  = ?       ");

        
        Object[] objects = new Object[] {
                invtDetailDTO.getInvtlistStatus(),
                invtDetailDTO.getInvtlistId(),
                user.getCompNo()
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }

    @Override
    public int cancelInvtPhaseStatus(InvtDetailDTO invtDetailDTO, User user) throws IOException {
        QueryBuffer query = new QueryBuffer();

        query.append("UPDATE TAINVTPHASE SET                                    ");
        query.append("       invtphase_status         = 'W'                     ");
        query.append("WHERE  comp_no                  = ?                       ");
        query.append("  AND  invtphase_id = ( SELECT invtphase_id               ");
        query.append("                        FROM (                            ");
        query.append("                              SELECT *                    ");
        query.append("                              FROM TAINVTPHASE            ");
        query.append("                              WHERE invtlist_id = ?       ");
        query.append("                                AND comp_no     = ?       ");
        query.append("                              ORDER BY ord_no DESC        ");
        query.append("                              )                           ");
        query.append("                        WHERE ROWNUM=1)                   ");


        Object[] objects = new Object[] {
                user.getCompNo(),
                invtDetailDTO.getInvtlistId(),
                user.getCompNo()
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }

    @Override
    public String checkStatus(String invtListId, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append(" SELECT                                   ");
        query.append("        CASE WHEN COUNT(1) <= 0 THEN 'C'  ");
        query.append("             ELSE 'P'                     ");
        query.append("              END                         ");
        query.append("   FROM TAINVTPHASE x                     ");
        query.append("  WHERE  x.comp_no            = ?         ");
        query.append("    AND  x.invtList_id        = ?         ");
        query.append("    AND  x.invtphase_status NOT IN ('C')  ");
        
        Object[] objects = new Object[] {
                 user.getCompNo()
               , invtListId
       };
       
       return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
    }
    
    @Override
    public List findReq(InvtCommonDTO invtCommonDTO, User user) throws IOException
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT woreq_id        AS woReqId     ");
        query.append("     , woreqres_id     AS woReqResId  ");
        query.append("  FROM TAWOREQRES                     ");
        query.append(" WHERE 1 = 1                          ");
        query.append("   AND comp_no        = ?             ");
        query.append("   AND invtlist_id    = ?             ");
        
        Object[] objects = new Object[] {
                 invtCommonDTO.getCompNo()
               , invtCommonDTO.getInvtlistId()
       };
       
       return getJdbcTemplate().queryForList(query.toString(), objects);
    }
}