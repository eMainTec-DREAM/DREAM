package dream.asset.rpt.eqpartprecon.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.rpt.eqpartprecon.dao.AssetRptEqPartPreConDetailDAO;
import dream.asset.rpt.eqpartprecon.dto.AssetRptEqPartPreConDetailDTO;

/**
 * AssetRptEqPartPreCon Page - Detail DAO implements
 * @author youngjoo38
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="assetRptEqPartPreConDetailDAOTarget"
 * @spring.txbn id="assetRptEqPartPreConDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetRptEqPartPreConDetailDAOOraImpl  extends BaseJdbcDaoSupportOra implements AssetRptEqPartPreConDetailDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptEqPartPreConDetailDTO
     * @return List
     */
    public List findPartDetail(AssetRptEqPartPreConDetailDTO assetRptEqPartPreConDetailDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                    ");
        query.append("		 '' 				seqNo           ");
        query.append("     , x.equip_id         equipId			");
        query.append("     , (SELECT a.description FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.equip_id) equipDesc		");
        query.append("     , x.eqasmb_id 		eqAsmbId		");
        query.append("     , y.eqpart_id        eqPartId		");
        query.append("     , z.part_id          partId			");
        query.append("     , x.full_desc       	eqAsmbFullDesc  "); // 부위
        query.append("     , z.part_no        	partNo          "); // 부품번호
        query.append("     , (SELECT NVL(description,'')||', '||NVL(pt_size,'')  ");
        query.append("        FROM TAPARTS                      ");
        query.append("        WHERE comp_no = x.comp_no         ");
        query.append("          AND part_id = y.part_id) 							 partDesc	"); // 부품명/규격
        query.append("     , (SELECT a.maker FROM TAPARTS a 	");
        query.append("        WHERE a.comp_no = x.comp_no AND a.part_id = y.part_id) maker		"); // 제작사
        query.append("     , (SELECT a.MODEL FROM TAPARTS a 	");
        query.append("        WHERE a.comp_no = x.comp_no AND a.part_id = y.part_id) model		"); // 모델
        query.append("     , z.pt_abc_class     partGrade		");
        query.append("     , SFACODE_TO_DESC(z.pt_abc_class, 'PT_ABC_CLASS', 'SYS', x.comp_no,'"+user.getLangId()+"')  ptAbcClassDesc		"); // 부품중요도
        query.append("     , z.vendor_code 		vendorCode		"); // Vendor코드
        query.append("     , (select a.plant from taequipment a where a.comp_no = x.comp_no and a.equip_id = x.equip_id)   plantId  			");
        query.append("FROM TAEQASMB x LEFT JOIN TAEQPART y		");
        query.append("ON x.comp_no = y.comp_no					");
        query.append("AND x.equip_id = y.equip_id				");
        query.append("AND x.eqasmb_id = y.eqasmb_id				");
        query.append("LEFT JOIN TAPARTS z						");
        query.append("ON y.comp_no = z.comp_no					");
        query.append("AND y.part_id = z.part_id					");
        query.append("WHERE 1=1									");
        query.append(this.getWhere(assetRptEqPartPreConDetailDTO, user));
        query.getOrderByQuery("x.full_desc", assetRptEqPartPreConDetailDTO.getOrderBy(), assetRptEqPartPreConDetailDTO.getDirection());        
 
        return getJdbcTemplate().queryForList(query.toString());
    }
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptEqPartPreConDetailDTO
     * @return List
     */
    public List findStockDetail(AssetRptEqPartPreConDetailDTO assetRptEqPartPreConDetailDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 										              ");
        query.append("      ''                    			SEQNO                 ");
        query.append("    , (SELECT a.wname 						              ");
        query.append("       FROM TAWAREHOUSE a  					              ");
        query.append("       WHERE a.comp_no = x.comp_no 			              ");
        query.append("         AND a.wcode_id = x.wcode_id) warehouseDesc		  ");
        query.append("    , x.stock_qty 					stockQty			  ");
        query.append("    , x.bin_no   						binNo				  ");
        query.append("    , NVL((SELECT aa.max_safty_qty                       ");
        query.append("              FROM TAPTSAFTYSTOCK aa                        ");
        query.append("              WHERE aa.comp_no = x.comp_no                  ");
        query.append("                AND aa.wcode_id = x.wcode_id                ");
        query.append("                AND aa.part_id = x.part_id),0) maxSaftyQty  ");
        query.append("    , NVL((SELECT aa.safty_qty                           ");
        query.append("              FROM TAPTSAFTYSTOCK aa                        ");
        query.append("              WHERE aa.comp_no = x.comp_no                  ");
        query.append("                AND aa.wcode_id = x.wcode_id                ");
        query.append("                AND aa.part_id = x.part_id),0) minSaftyQty  ");
        query.append("FROM TAPTSTOCK x											  ");
        query.append("WHERE 1=1													  ");
        query.append("AND x.comp_no 	= ?										  ");
        query.append("AND x.part_id 	= ?		                                  ");
        if(!"".equals(assetRptEqPartPreConDetailDTO.getPartGrade()))
        //query.append("AND x.part_grade 	= '"+assetRptEqPartPreConDetailDTO.getPartGrade()+"'	");
        //query.append("AND x.wcode_id 	= ?		                                  ");
        query.getOrderByQuery("x.bin_no", assetRptEqPartPreConDetailDTO.getOrderBy(), assetRptEqPartPreConDetailDTO.getDirection());
        
        Object[] objects = new Object[] {   
                user.getCompNo()
              , assetRptEqPartPreConDetailDTO.getPartId()
        };
        
        return getJdbcTemplate().queryForList(query.toString(),objects);
    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     *   
     * @param assetRptEqPartPreConDetailDTO
     * @return
     * @throws Exception
     */
    private String getWhere(AssetRptEqPartPreConDetailDTO assetRptEqPartPreConDetailDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.equip_id", assetRptEqPartPreConDetailDTO.getEquipId());

        return query.toString();
    }
}
