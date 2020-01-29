package dream.part.rec.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.part.rec.dao.MaPtRecDetailDAO;
import dream.part.rec.dto.MaPtRecDetailDTO;

/**
 * 구매입고 - 상세 dao
 * 
 * @author ssong
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="maPtRecDetailDAOTarget"
 * @spring.txbn id="maPtRecDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtRecDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPtRecDetailDAO
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param prRecListId
     * @return
     */
    public MaPtRecDetailDTO findDetail(User user, String prRecListId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String compNo = user.getCompNo();
        
        query.append("SELECT x.comp_no                             compNo,      ");
        query.append("       x.prreclist_id                        prRecListId, ");
        query.append("       x.prreclist_no                        prRecListNo, ");
        query.append("       x.prreclist_status                    prRecListStatus,");
        query.append("       dbo.SFACODE_TO_DESC(x.prreclist_status, 'PRRECLIST_STATUS', 'SYS', x.comp_no, ? ) prRecListStatusDesc, "); 
        query.append("       x.rec_date                            recDate,     "); 
        query.append("       x.vendor_id                           vendorId,    "); 
        query.append("       dbo.SFAIDTODESC(x.vendor_id, '', 'VENDOR', x.comp_no) vendorDesc,  "); 
        query.append("       x.part_id                             partId,      "); 
        query.append("       CASE WHEN x.part_id IS NULL THEN ''                ");
        query.append("            ELSE (SELECT a.part_no FROM TAPARTS a WHERE a.comp_no = x.comp_no AND a.part_id = x.part_id AND a.part_categ = 'SPPT') ");
        query.append("            END                              partNo,      ");
        query.append("       x.dept_id                             deptId,      "); 
        query.append("       dbo.SFAIDTODESC(x.dept_id, '', 'DEPT', x.comp_no) deptDesc, "); 
        query.append("       CASE WHEN x.part_id IS NULL THEN x.pt_desc         ");
        query.append("            ELSE (SELECT a.description FROM TAPARTS a WHERE a.comp_no = x.comp_no AND a.part_id = x.part_id AND a.part_categ = 'SPPT')  END  partDesc,            ");
        query.append("       CASE WHEN x.part_id IS NULL THEN x.pt_size         ");
        query.append("            ELSE (SELECT a.pt_size FROM TAPARTS a WHERE a.comp_no = x.comp_no AND a.part_id = x.part_id AND a.part_categ = 'SPPT') END     partSize,              ");
        query.append("      (SELECT a.model FROM TAPARTS a                      ");
        query.append("       WHERE a.comp_no = x.comp_no                        ");
        query.append("       AND a.part_id = x.part_id                          ");
        query.append("       AND a.part_categ = 'SPPT')            model,       ");
        query.append("       x.wcode_id                            wcodeId,     ");
        query.append("      (SELECT a.wname FROM TAWAREHOUSE a                  ");
        query.append("       WHERE  a.comp_no  = x.comp_no                      ");
        query.append("         AND  a.wcode_id = x.wcode_id)       wname,       "); //창고명         
        query.append("       ISNULL(x.rec_qty, 0)                  recQty,      "); 
        query.append("       ISNULL(x.unit_price, 0)               unitPrice,   "); 
        query.append("       x.inspector                           inspector,   ");  
        query.append("       dbo.SFAIDTODESC(x.inspector, '', 'EMP', x.comp_no) inspectorName, ");  
        query.append("       ISNULL(x.tot_price, 0)                totPrice,    "); 
        query.append("       x.part_grade partGrade,                            ");
        query.append("       x.remark                              remark,      "); 
        query.append("      (SELECT a.is_serial_part FROM TAPARTS a WHERE a.comp_no = x.comp_no AND a.part_id = x.part_id)                      isSerial,           ");
        query.append("      (SELECT COUNT(*) FROM TAEQUIPMENT z WHERE x.comp_no =z.comp_no AND z.equip_id IN (SELECT z1.equip_id FROM TAPTPRRECLIST_SERIAL z1 WHERE z1.prreclist_id=x.prreclist_id AND x.comp_no=z1.comp_no ) AND eq_status='R') countWo        ");
        query.append("      ,x.is_make_part_no                     isMakePartNoId       ");
        query.append("      ,dbo.sfacode_to_desc(x.is_make_part_no,'IS_USE','SYS',x.comp_no, ? )    isMakePartNo    ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.part_grade,'PART_GRADE','SYS','', ? )        AS  partGradeDesc   ");
        query.append("      ,x.plant                               plantId      ");
        query.append("      ,(SELECT description                                ");
        query.append("         FROM TAPLANT                                     ");
        query.append("         WHERE comp_no = x.comp_no                        ");
        query.append("           AND plant = x.plant)              plantDesc    ");
        query.append("      ,x.uom                                 AS uom       ");
        query.append("      ,x.polist_id                           AS poListId  ");
        query.append("      ,x.ptpritem_id                         AS ptprItemId  ");
        query.append("      ,x.ptpoItem_id                         AS poItemId  ");
        query.append("      , x.currency                        														AS currencyId  	");
        query.append("      , dbo.SFACODE_TO_DESC(x.currency, 'CURRENCY', 'SYS', x.comp_no,'"+ user.getLangId() +"')	AS currencyDesc	");
        query.append("FROM   TAPTPRRECLIST x                                    ");
        query.append("WHERE  1 = 1                                              ");
        query.append("  AND  x.comp_no      = ?                                 ");
        query.append("  AND  x.prreclist_id = ?                                 ");
    
        Object[] objects = new Object[] {
                user.getLangId()
               ,user.getLangId()
               ,user.getLangId()
               ,user.getCompNo()
               ,prRecListId
       };

        MaPtRecDetailDTO maPtRecDetailDTO = 
                (MaPtRecDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MaPtRecDetailDTO()));
        
        return maPtRecDetailDTO;
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public int insertDetail(MaPtRecDetailDTO maPtRecDetailDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append(" INSERT INTO TAPTPRRECLIST (                                      ");
        query.append("   comp_no    ,prreclist_id  ,prreclist_no      ,prreclist_status ");
        query.append("  ,dept_id    ,wcode_id      ,vendor_id         ,rec_date         ");
        query.append("  ,part_id    ,rec_qty       ,unit_price        ,tot_price        ");
        query.append("  ,inspector  ,remark        ,pt_desc           ,pt_size          ");
        query.append("  ,uom        ,plant         ,is_make_part_no   ,part_grade       ");
        query.append("  ,polist_id  ,ptpoitem_id   ,ptpritem_id       ,currency         ");
        query.append(" ) VALUES (                                                       ");
        query.append("   ?         ,?            ,?            ,?                       ");
        query.append("  ,?         ,?            ,?            ,?                       ");
        query.append("  ,?         ,?            ,?            ,?                       ");
        query.append("  ,?         ,?            ,?            ,?                       ");
        query.append("  ,?         ,?            ,?            ,?                       ");
        query.append("  ,?         ,?            ,?            ,?                       ");
        query.append(" )                                                                ");
        
        Object[] objects = new Object[] {
                loginUser.getCompNo(),
                maPtRecDetailDTO.getPrRecListId(),
                maPtRecDetailDTO.getPrRecListNo(),
                maPtRecDetailDTO.getPrRecListStatus(),
                maPtRecDetailDTO.getDeptId(),
                maPtRecDetailDTO.getWcodeId(),
                maPtRecDetailDTO.getVendorId(),
                CommonUtil.getRowDateToNum(maPtRecDetailDTO.getRecDate()),
                maPtRecDetailDTO.getPartId(),
                CommonUtil.getRowMoneyToNum(maPtRecDetailDTO.getRecQty()),
                CommonUtil.getRowMoneyToNum(maPtRecDetailDTO.getUnitPrice()),
                CommonUtil.getRowMoneyToNum(maPtRecDetailDTO.getTotPrice()),
                maPtRecDetailDTO.getInspector(),
                maPtRecDetailDTO.getRemark(),
                maPtRecDetailDTO.getPartDesc(),
                maPtRecDetailDTO.getPartSize(),
                maPtRecDetailDTO.getUom(),
                maPtRecDetailDTO.getPlantId(),
                maPtRecDetailDTO.getIsMakePartNoId(),
                maPtRecDetailDTO.getPartGrade(),
                maPtRecDetailDTO.getPolistId(),
                maPtRecDetailDTO.getPoitemId(),
                maPtRecDetailDTO.getPtpritemId(),
                maPtRecDetailDTO.getCurrencyId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    

    public int insertPart(MaPtRecDetailDTO maPtRecDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
//      String nextPartId = "";
//      
//      query.append("SELECT NEXT VALUE FOR sqapart_id      ");
//
//      nextPartId = QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
//
//      maPtRecDetailDTO.setPartId(nextPartId);
//      maPtRecDetailDTO.setPartNo(nextPartId);
        
//      query.setClear();
        
        query.append("INSERT INTO TAPARTS (            ");
        query.append("    comp_no   , part_id          ");
        query.append("  , part_no   , description      ");
        query.append("  , pt_size   , uom              ");
        query.append("  , full_desc , is_use           ");
        query.append("  , part_categ , upd_date        ");
        query.append("  , upd_by    , is_stock_control ");
        query.append(") VALUES (                       ");
        query.append("   ?          , ?                ");
        query.append("  ,?          , ?                ");
        query.append("  ,?          , ?                ");
        query.append("  ,?          , ?                ");
        query.append("  ,?          , ?                ");
        query.append("  ,?          , ?                ");
        query.append("  )                              ");
        
        Object[] objects = new Object[] {
                  user.getCompNo()
                , maPtRecDetailDTO.getPartId()
                , maPtRecDetailDTO.getPartNo()
                , maPtRecDetailDTO.getPartDesc()
                , maPtRecDetailDTO.getPartSize()
                , maPtRecDetailDTO.getUom()
                , maPtRecDetailDTO.getPartNo()+", "
                  + maPtRecDetailDTO.getPartDesc()+", "
                  + maPtRecDetailDTO.getPartSize()
                , "Y"
                , "SPPT"
                , DateUtil.getDate()
                , user.getEmpId()
                , "Y"
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail stock
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public int updateDetail(MaPtRecDetailDTO maPtRecDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAPTPRRECLIST SET            ");
        query.append("       prreclist_status   = ?,      ");
        query.append("       dept_id            = ?,      ");
        query.append("       wcode_id           = ?,      ");
        query.append("       vendor_id          = ?,      ");    
        query.append("       rec_date           = ?,      ");    
        query.append("       part_id            = ?,      ");
        query.append("       pt_desc            = ?,      ");
        query.append("       pt_size            = ?,      ");
        query.append("       uom                = ?,      ");
        query.append("       rec_qty            = ?,      ");     
        query.append("       unit_price         = ?,      ");  
        query.append("       tot_price          = ?,      ");
        query.append("       inspector          = ?,      ");
        query.append("       prreclist_no       = ?,      ");
        query.append("       is_make_part_no    = ?,      ");
        query.append("       part_grade         = ?,      ");
        query.append("       plant              = ?,      ");
        query.append("       currency           = ?,      ");
        query.append("       remark             = ?       ");
        query.append("WHERE  comp_no            = ?       ");
        query.append("  AND  prreclist_id       = ?       ");
        
        Object[] objects = new Object[] {
                maPtRecDetailDTO.getPrRecListStatus(),
                maPtRecDetailDTO.getDeptId(),
                maPtRecDetailDTO.getWcodeId(),
                maPtRecDetailDTO.getVendorId(),
                maPtRecDetailDTO.getRecDate().replaceAll("-", ""),
                maPtRecDetailDTO.getPartId(),
                maPtRecDetailDTO.getPartDesc(),
                maPtRecDetailDTO.getPartSize(),
                maPtRecDetailDTO.getUom(),
                maPtRecDetailDTO.getRecQty(),
                maPtRecDetailDTO.getUnitPrice(),
                CommonUtil.getRowMoneyToNum(maPtRecDetailDTO.getTotPrice()),
                maPtRecDetailDTO.getInspector(),
                maPtRecDetailDTO.getPrRecListNo(),
                maPtRecDetailDTO.getIsMakePartNo(),
                maPtRecDetailDTO.getPartGrade(),
                maPtRecDetailDTO.getPlantId(),
                maPtRecDetailDTO.getCurrencyId(),
                maPtRecDetailDTO.getRemark(),
                user.getCompNo(),
                maPtRecDetailDTO.getPrRecListId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail 품번 Update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public int updatePartNo(MaPtRecDetailDTO maPtRecDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAPTPRRECLIST SET          ");
        query.append("       part_id   = ?              ");
        query.append("WHERE  comp_no            = ?     ");
        query.append("  AND  prreclist_id       = ?     ");
        
        Object[] objects = new Object[] {
                maPtRecDetailDTO.getPartId()
                ,user.getCompNo()
                ,maPtRecDetailDTO.getPrRecListId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail 상태 Update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public int updatePtRecListStatus(MaPtRecDetailDTO maPtRecDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAPTPRRECLIST SET            ");
        query.append("       prreclist_status   = ?       ");
        query.append("WHERE  comp_no            = ?       ");
        query.append("  AND  prreclist_id       = ?       ");
        
        Object[] objects = new Object[] {
                maPtRecDetailDTO.getPrRecListStatus(),
                user.getCompNo(),
                maPtRecDetailDTO.getPrRecListId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
       
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public String validPrRecListNo(MaPtRecDetailDTO maPtRecDetailDTO)
    {
        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(*)                                               ");
        query.append("FROM   TAPTPRRECLIST x                                        ");
        query.append("WHERE  comp_no      = '"+maPtRecDetailDTO.getCompNo()+"'      ");
        query.append("  AND  prreclist_no = '"+maPtRecDetailDTO.getPrRecListNo()+"' ");
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    /**
     * 상태 조회
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public String findPrRecListStatus(String compNo, String prRecListId)
    {
        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT prreclist_status                 ");
        query.append("FROM   TAPTPRRECLIST x                  ");
        query.append("WHERE  comp_no      = '"+compNo+"'      ");
        query.append("  AND  prreclist_id = '"+prRecListId+"' ");
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
      
    /**
     * 입고 History insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public int insertRecHistory(String ptRecHistId, MaPtRecDetailDTO maPtRecDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("INSERT INTO TAPTRECHIST                                         ");
        query.append("SELECT x.comp_no,     '"+ptRecHistId+"',                        ");
        query.append("       '"+maPtRecDetailDTO.getPtRecMode()+"' ptrecMode,         ");
        query.append("       'PRREC'                               ptrecType,         ");
        query.append("       prreclist_id,  dept_id,    x.wcode_id,    vendor_id,     ");
        query.append("       rec_date,      x.part_id,  x.part_grade,  rec_qty,       ");
        query.append("       unit_price,    tot_price,  x.currency                    ");
        query.append("FROM   TAPTPRRECLIST x                                          ");
        query.append("WHERE  x.comp_no      = '"+user.getCompNo()+"'                  ");
        query.append("  AND  x.prreclist_id = '"+maPtRecDetailDTO.getPrRecListId()+"' ");
        
        return this.getJdbcTemplate().update(query.toString());
    }
    
    /**
     * 입고완료 전에 SP_PT_INSTOCK 프로시져 호출 
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param ptRecHistId
     * @return
     * @throws Exception
     */
    public int executeSpPtInstock(String compNo, String ptRecHistId) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("EXEC dbo.SP_PT_INSTOCK '"+compNo+"', '"+ptRecHistId+"';          ");
      
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
    }

    public String checkExistValue(String ptRecListId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TAPTPRRECLIST x                                    ");
        query.append("WHERE  x.prreclist_id = '"+ptRecListId+"'                 ");
    
        List resultList= getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
    
    public String checkExistPartNo(MaPtRecDetailDTO maPtRecDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                        ");
        query.append("  MAX(part_id) AS existPartId ");
        query.append("FROM   TAPARTS x              ");
        query.append("WHERE 1=1                     ");
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.description", maPtRecDetailDTO.getPartDesc());
        query.getAndQuery("x.pt_size", maPtRecDetailDTO.getPartSize());
        
        List resultList= getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
    
    public String checkExistNonValue(String fcRecListId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TAPTFCRECLIST x                                    ");
        query.append("WHERE  x.fcreclist_id = '"+fcRecListId+"'                 ");
    
        List resultList= getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }

    
    /**
     * 시리얼 리스트를 조회한다.
     * @author  javaworker
     * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public List findSerialList(MaPtRecDetailDTO maPtRecDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT        ");
        query.append("      x.prreclist_serial_id prreclistSerialId     ");
        query.append("      ,x.part_id partId       ");
        query.append("      ,x.serial_no serialNo       ");
        query.append("      ,x.equip_id equipId ");
        query.append("FROM TAPTPRRECLIST_SERIAL x   ");
        query.append("WHERE  x.prreclist_id = '"+maPtRecDetailDTO.getPrRecListId()+"'                 ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public int insertEquipment(String serialNo, String compNo, String equipId,String partId, String deptId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("INSERT INTO TAEQUIPMENT (                               ");
        query.append("  comp_no,   equip_id, item_no, description,");
        query.append("  eq_status, serial_no, eqctg_type, dept_id,                                      ");
        query.append("  revision_status, is_last_version                                    ");
        query.append(") VALUES (                                                ");
        query.append("  ?,         ?,             ?,      (SELECT description FROM TAPARTS WHERE part_id=?),              ");
        query.append("  'S',   ?,           'PT', ?,             ");
        query.append("  'C',   'Y'            ");
        query.append(")                                                         ");
        
        Object[] objects = new Object[] {
                compNo
                ,equipId
                ,equipId
                ,partId
                ,serialNo
                ,deptId
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public int insertEqDevice(String compNo, String equipId,String partId,String wcodeId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("INSERT INTO TAEQDEVICE (                               ");
        query.append("  comp_no,   equip_id, part_id, wcode_id");
        query.append(") VALUES (                                                ");
        query.append("  ?,         ?,             ? , ?                ");
        query.append(")                                                         ");
        
        Object[] objects = new Object[] {
                compNo
                ,equipId
                ,partId
                ,wcodeId
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteSerial(MaPtRecDetailDTO maPtRecDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        
        query = new QuerySqlBuffer();
        
        query.append("DELETE FROM TAPTPRRECLIST_SERIAL                    ");
        query.append("WHERE  comp_no       = '"+maPtRecDetailDTO.getCompNo()+"'       ");
        query.append("  AND  prreclist_id  = '"+maPtRecDetailDTO.getPrRecListId()+"'    ");
        
        rtnValue = this.getJdbcTemplate().update(query.toString());
        
        return rtnValue;
    }
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public String validSerialCount(MaPtRecDetailDTO maPtRecDetailDTO)
    {
        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(*) serialCount                               ");
        query.append("FROM   TAPTPRRECLIST_SERIAL x                             ");
        query.append("WHERE  comp_no      = '"+maPtRecDetailDTO.getCompNo()+"'  ");
        query.append("  AND  prreclist_id = '"+maPtRecDetailDTO.getPrRecListId()+"'  ");
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }

    @Override
    public int updateSerial(String compNo, String equipId, String prreclistSerialId) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAPTPRRECLIST_SERIAL SET             ");
        query.append("       equip_id            = ?      ");
        query.append("WHERE  comp_no            = ?       ");
        query.append("  AND  prreclist_serial_id       = ?       ");
        
        Object[] objects = new Object[] {
                equipId
                ,compNo
                ,prreclistSerialId
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    public int updateEquipment(String compNo, String isEquipId,String status) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAEQUIPMENT SET              ");
        query.append("       eq_status               = ?     ");
        query.append("WHERE  comp_no            = ?       ");
        query.append("  AND  equip_id       = ?       ");
        
        Object[] objects = new Object[] {
                status
                ,compNo
                ,isEquipId
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    
    /**
     * detail stock
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public int updateSerialPart(MaPtRecDetailDTO maPtRecDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAPTPRRECLIST_SERIAL SET             ");
        query.append("       part_id             = ?      ");
        query.append("WHERE  comp_no            = ?       ");
        query.append("  AND  prreclist_id       = ?       ");
        
        Object[] objects = new Object[] {
                maPtRecDetailDTO.getPartId()
                ,maPtRecDetailDTO.getCompNo()
                ,maPtRecDetailDTO.getPrRecListId()
        };
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
}