package dream.asset.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.asset.list.dao.AssetListPartOpQtyDAO;
import dream.asset.list.dto.AssetListPartOpQtyDTO;

/**
 * 설비운용기간사용예상수량
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="assetListPartOpQtyDAOTarget"
 * @spring.txbn id="assetListPartOpQtyDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetListPartOpQtyDAOSqlImpl extends BaseJdbcDaoSupportSql implements AssetListPartOpQtyDAO
{
    @Override
    public List find(AssetListPartOpQtyDTO assetListPartOpQtyDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                    ");
        query.append("    ''                                AS ISDELCHECK       ");
        query.append("    ,''                               AS SEQNO            ");
        query.append("    ,a.eqpartopqty_id                 AS EQPARTOPQTYID    ");
        query.append("    ,a.eqpart_id                      AS EQPARTID         ");
        query.append("    ,a.equip_id                       AS EQUIPID          ");
        query.append("    ,a.eqasmb_id                      AS EQASMBID         ");
        query.append("    ,a.part_id                        AS PARTID           ");
        query.append("    ,a.op_period                      AS OPPERIOD         ");
        query.append("    ,a.op_qty                         AS OPQTY            ");
        query.append("    ,a.ven_po_no                      AS VENPONO          ");
        query.append("    ,a.vendor_id                      AS VENDORID         ");
        query.append("    ,(SELECT vendor_no FROM TAVENDOR                      ");
        query.append("      WHERE comp_no = a.comp_no                           ");
        query.append("      AND vendor_id = a.vendor_id)    AS VENDORNO         ");
        query.append("    ,(SELECT description FROM TAVENDOR                    ");
        query.append("      WHERE comp_no = a.comp_no                           ");
        query.append("      AND vendor_id = a.vendor_id)    AS VENDORDESC       ");
        query.append("    ,a.ord_no                         AS ORDNO            ");
        query.append("    ,a.remark                         AS REMARK           ");
        query.append("FROM TAEQPARTOPQTY a                                      ");
        query.append("WHERE 1=1                                                 ");
        query.append(this.getWhere(assetListPartOpQtyDTO, user));
        query.getOrderByQuery("a.eqpartopqty_id", "a.ord_no", assetListPartOpQtyDTO.getOrderBy(), assetListPartOpQtyDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assetListPartOpQtyDTO.getIsLoadMaxCount(), assetListPartOpQtyDTO.getFirstRow()));
    }
    
    @Override
    public int insert(AssetListPartOpQtyDTO assetListPartOpQtyDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("INSERT INTO TAEQPARTOPQTY(                    ");
        query.append("   comp_no        ,eqpartopqty_id ,eqpart_id  ");
        query.append("   ,equip_id      ,eqasmb_id      ,part_id    ");
        query.append("   ,op_period     ,op_qty         ,ven_po_no  ");
        query.append("   ,vendor_id     ,ord_no         ,remark     ");
        query.append(")VALUES(                                      ");
        query.append("   ?              ,?              ,?          ");
        query.append("   ,?             ,?              ,?          ");
        query.append("   ,?             ,?              ,?          ");
        query.append("   ,?             ,?              ,?          ");
        query.append(")                                             ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
                ,assetListPartOpQtyDTO.getEqPartOpQtyId()
                ,assetListPartOpQtyDTO.getEqPartId()
                ,assetListPartOpQtyDTO.getEquipId()
                ,assetListPartOpQtyDTO.getEqAsmbId()
                ,assetListPartOpQtyDTO.getPartId()
                ,assetListPartOpQtyDTO.getOpPeriod()
                ,assetListPartOpQtyDTO.getOpQty()
                ,assetListPartOpQtyDTO.getVenPoNo()
                ,assetListPartOpQtyDTO.getVendorId()
                ,assetListPartOpQtyDTO.getOrdNo()
                ,assetListPartOpQtyDTO.getRemark()
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    @Override
    public int update(AssetListPartOpQtyDTO assetListPartOpQtyDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAEQPARTOPQTY SET          ");
        query.append("       eqpart_id          = ?     ");
        query.append("     , equip_id           = ?     ");
        query.append("     , eqasmb_id          = ?     ");
        query.append("     , part_id            = ?     ");
        query.append("     , op_period          = ?     ");
        query.append("     , op_qty             = ?     ");
        query.append("     , ven_po_no          = ?     ");
        query.append("     , vendor_id          = ?     ");
        query.append("     , ord_no             = ?     ");
        query.append("     , remark             = ?     ");
        query.append("WHERE  comp_no            = ?     ");
        query.append("  AND  eqpartopqty_id     = ?     ");
        
        Object[] objects = new Object[] {
                assetListPartOpQtyDTO.getEqPartId()
                ,assetListPartOpQtyDTO.getEquipId()
                ,assetListPartOpQtyDTO.getEqAsmbId()
                ,assetListPartOpQtyDTO.getPartId()
                ,assetListPartOpQtyDTO.getOpPeriod()
                ,assetListPartOpQtyDTO.getOpQty()
                ,assetListPartOpQtyDTO.getVenPoNo()
                ,assetListPartOpQtyDTO.getVendorId()
                ,assetListPartOpQtyDTO.getOrdNo()
                ,assetListPartOpQtyDTO.getRemark()
                ,user.getCompNo()
                ,assetListPartOpQtyDTO.getEqPartOpQtyId()
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }

    @Override
    public int delete(AssetListPartOpQtyDTO assetListPartOpQtyDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TAEQPARTOPQTY     ");
        query.append("WHERE  comp_no        = ?     ");
        query.append("  AND  eqpartopqty_id = ?     ");
        
        Object[] objects = new Object[] {   
            user.getCompNo()
            ,assetListPartOpQtyDTO.getEqPartOpQtyId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }

    @Override
    public String findTotalCount(AssetListPartOpQtyDTO assetListPartOpQtyDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                            ");
        query.append("    COUNT(1)                                      ");
        query.append("FROM TAEQPARTOPQTY a                              ");
        query.append("WHERE 1=1                                         ");
        query.append(this.getWhere(assetListPartOpQtyDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
    
    private String getWhere(AssetListPartOpQtyDTO assetListPartOpQtyDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getStringEqualQuery("a.comp_no", user.getCompNo());
        query.getStringEqualQuery("a.eqpart_id", assetListPartOpQtyDTO.getEqPartId());
        if(!"".equals(assetListPartOpQtyDTO.getEqPartOpQtyId())){
            query.getAndQuery("a.eqpartopqty_id", assetListPartOpQtyDTO.getEqPartOpQtyId());
            return query.toString();
        }
        
        return query.toString();
    }
}