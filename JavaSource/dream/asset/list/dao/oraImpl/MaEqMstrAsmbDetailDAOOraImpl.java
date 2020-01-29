package dream.asset.list.dao.oraImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.asset.list.dao.MaEqMstrAsmbDetailDAO;
import dream.asset.list.dto.MaEqMstrAsmbDetailDTO;
import dream.asset.list.dto.MaEqMstrAsmbListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * 설비부위 상세 dao
 * @author  kim21017
 * @version $Id: MaEqMstrAsmbDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqMstrAsmbDetailDAOTarget"
 * @spring.txbn id="maEqMstrAsmbDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqMstrAsmbDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaEqMstrAsmbDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrAsmbDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param equipId
     * @param eqAsmbId
     * @param compNo
     * @return
     */
    public MaEqMstrAsmbDetailDTO findDetail(MaEqMstrAsmbListDTO maEqMstrAsmbListDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
        
		query.append("SELECT										");
        query.append("       x.equip_id			equipId				");
        query.append("     , x.eqasmb_id		eqAsmbId			");
        query.append("     , x.description		eqAsmbDesc			");
        query.append("     , x.maker            maker               ");
        query.append("     , x.model_no         modelNo             ");
        query.append("     , x.buy_amt          buyAmt              ");
        query.append("     , x.spec             spec                ");
        query.append("     , x.setup_date       setupDate           ");
        query.append("     , x.is_eqtype_asmb	isEqTypeAsmb		");
        query.append("     , x.is_use			isUse				");
        query.append("     , x.ord_no			ordNo				");
        query.append("     , x.p_eqasmb_id      pEqAsmbId           ");
        query.append("     , (SELECT full_desc                    	");
        query.append("        FROM TAEQASMB                         ");
        query.append("        WHERE eqasmb_id = x.p_eqasmb_id) pEqAsmbDesc        ");
        query.append("     , x.eqasmb_no        eqAsmbNo            ");
        query.append("     , x.remark           remark              ");
        query.append("     , x.tag_no           tagNo     			");
        query.append("     , x.as_vendor        asInfo     			");
        query.append("     , x.eq_ctg_asmb_id   eqCtgAsmbId     	");
        query.append("     , x.eq_ctg_asmb_no   eqCtgAsmbNo     	");
        query.append("     , x.p_eq_ctg_asmb_id peqCtgAsmbId     	");
        query.append("     , x.guaranty_date	guarantyDate		");
        query.append("     , x.serial_no 		serialNo			");
        query.append("     , x.eq_size 			eqSize				");
        query.append("     , x.weight 			weight				");
        query.append("	   , x.part_id 			partId				");
        query.append("     , (SELECT a.part_no FROM TAPARTS a WHERE a.comp_no = x.comp_no AND a.part_id = x.part_id)		partNo		");
        query.append("     , (SELECT a.description||'\'||a.pt_size FROM TAPARTS a WHERE a.comp_no = x.comp_no AND a.part_id = x.part_id) 	partNameSize	");
        query.append("     , x.cre_date 			createDate			");
        query.append("	   , x.cre_by				creById				");
        query.append("     , (SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_id = x.cre_by) 			creBy		");
        query.append("     , x.upd_date 			updateDate			");
        query.append("	   , x.upd_by				updById				");
        query.append("     , (SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_id = x.upd_by) 			modifyBy 		");
        query.append("FROM   TAEQASMB x								");
        query.append("WHERE	 1=1									");
        query.append("  and	 x.eqasmb_id 		= ?					");
        query.append("  AND  x.comp_no			= ?					");

    	Object[] objects = new Object[] {
    			maEqMstrAsmbListDTO.getEqAsmbId()
    		  ,	user.getCompNo()
    	};
    
        MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO = 
        		(MaEqMstrAsmbDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new MaEqMstrAsmbDetailDTO()));
        
        return maEqMstrAsmbDetailDTO;
    }
    
    @Override
    public int[] updateDetail(final List<MaEqMstrAsmbDetailDTO> list, final User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("UPDATE TAEQASMB SET           ");
        query.append("  description         = ?,    ");
        query.append("  full_desc           = ?,    ");
        query.append("  maker               = ?,    ");
        query.append("  model_no            = ?,    ");
        query.append("  buy_amt             = ?,    ");
        query.append("  spec                = ?,    ");
        query.append("  setup_date          = ?,    ");
        query.append("  is_use              = ?,    ");
        query.append("  ord_no              = ?,    ");
        query.append("  p_eqasmb_id         = ?,    ");
        query.append("  remark              = ?,    ");
        query.append("  tag_no              = ?,    ");
        query.append("  as_vendor           = ?,    ");
        query.append("  eq_ctg_asmb_id      = ?,    ");
        query.append("  is_eqtype_asmb      = ?,    ");
        query.append("  eq_ctg_asmb_no      = ?,    ");
        query.append("  p_eq_ctg_asmb_id    = ?     ");
        query.append(" , part_id			= ?		");
    	query.append(" , guaranty_date		= ?		");
    	query.append(" , eq_size			= ?		");
    	query.append(" , weight				= ?		");
    	query.append(" , serial_no			= ?		");
    	query.append(" , cre_date			= ?		");
    	query.append(" , cre_by				= ?		");
    	query.append(" , upd_date			= ?		");
    	query.append(" , upd_by				= ?		");
        query.append("WHERE eqasmb_id       = ?     ");
        query.append("  AND comp_no         = ?     ");
        
        return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {
            
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
                MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO = list.get(i);
                
                Object[] objects = new Object[] {
                        maEqMstrAsmbDetailDTO.getEqAsmbDesc(),
                        maEqMstrAsmbDetailDTO.getFullDesc(),
                        maEqMstrAsmbDetailDTO.getMaker(),
                        maEqMstrAsmbDetailDTO.getModelNo(),
                        CommonUtil.getRowMoneyToNum(maEqMstrAsmbDetailDTO.getBuyAmt()),
                        maEqMstrAsmbDetailDTO.getSpec(),
                        CommonUtil.getRowDateToNum(maEqMstrAsmbDetailDTO.getSetupDate()),
                        maEqMstrAsmbDetailDTO.getIsUse(),
                        maEqMstrAsmbDetailDTO.getOrdNo(),
                        maEqMstrAsmbDetailDTO.getPeqAsmbId(),
                        maEqMstrAsmbDetailDTO.getRemark(),
                        maEqMstrAsmbDetailDTO.getTagNo(),
                        maEqMstrAsmbDetailDTO.getAsInfo(),
                        maEqMstrAsmbDetailDTO.getEqCtgAsmbId(),
                        maEqMstrAsmbDetailDTO.getIsEqTypeAsmb(),
                        maEqMstrAsmbDetailDTO.getEqCtgAsmbNo(),
                        maEqMstrAsmbDetailDTO.getPeqCtgAsmbId(),
                        maEqMstrAsmbDetailDTO.getPartId(),
                        CommonUtil.getRowDateToNum(maEqMstrAsmbDetailDTO.getGuarantyDate()),
    					maEqMstrAsmbDetailDTO.getEqSize(),
    					maEqMstrAsmbDetailDTO.getWeight(),
    					maEqMstrAsmbDetailDTO.getSerialNo(),
    					CommonUtil.getRowDateToNum(maEqMstrAsmbDetailDTO.getCreDate()),
    					maEqMstrAsmbDetailDTO.getCreById(),
    					CommonUtil.getRowDateToNum(maEqMstrAsmbDetailDTO.getUpdDate()),
    					maEqMstrAsmbDetailDTO.getModifyById(),
    					maEqMstrAsmbDetailDTO.getEqAsmbId(),
                        user.getCompNo()
                };
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
            
            @Override
            public int getBatchSize()
            {
                return list.size();
            }
        });
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqMstrAsmbDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrAsmbDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     */
    public int insertDetail(MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	if("".equals(maEqMstrAsmbDetailDTO.getPeqAsmbId()))
        {
    	    maEqMstrAsmbDetailDTO.setPeqAsmbId("0");
        }
    	query.append("INSERT INTO TAEQASMB              ");
        query.append("  (  comp_no        , eqasmb_id   ");
        query.append("   , equip_id       , description ");
        query.append("   , is_use         , ord_no      ");
        query.append("   , p_eqasmb_id    , eqasmb_no   ");
        query.append("   , is_eqtype_asmb , remark      ");
        query.append("   , maker          , model_no    ");
        query.append("   , buy_amt        , spec        ");
        query.append("   , setup_date	  , tag_no      ");
        query.append("   , as_vendor      , eq_ctg_asmb_id        ");
        query.append("   , eq_ctg_asmb_no , p_eq_ctg_asmb_id      ");
        query.append("   , eqctg_id                     ");
        query.append("   , part_id		  , guaranty_date");
    	query.append("   , eq_size		  ,	weight		 ");
    	query.append("   , serial_no	  , cre_date	 ");
    	query.append("   , cre_by		  , upd_date	 ");
    	query.append("   , upd_by						 ");
        query.append("  )   VALUES                      ");
        query.append("  (  ?              , ?           ");
        query.append("   , ?              , ?           ");
        query.append("   , ?              , ?           ");
        query.append("   , ?              , ?           ");
        query.append("   , ?              , ?           ");
        query.append("   , ?              , ?           ");
        query.append("   , ?              , ?           ");
        query.append("   , ?              , ?           ");
        query.append("   , ?              , ?           ");
        query.append("   , ?              , ?           ");
        query.append("   , ?                            ");
        query.append("   , ?              , ?           ");
        query.append("   , ?              , ?           ");
        query.append("   , ?              , ?           ");
        query.append("   , ?              , ?           ");
        query.append("   , ?              	            ");
        query.append("  )                               ");
        
        Object[] objects = new Object[] {
                  user.getCompNo()
                , maEqMstrAsmbDetailDTO.getEqAsmbId()
                , maEqMstrCommonDTO.getEquipId()
                , maEqMstrAsmbDetailDTO.getEqAsmbDesc()
                , maEqMstrAsmbDetailDTO.getIsUse()
                , maEqMstrAsmbDetailDTO.getOrdNo()
                , maEqMstrAsmbDetailDTO.getPeqAsmbId()
                , maEqMstrAsmbDetailDTO.getEqAsmbNo()
                , maEqMstrAsmbDetailDTO.getIsEqTypeAsmb()
                , maEqMstrAsmbDetailDTO.getRemark()
                , maEqMstrAsmbDetailDTO.getMaker()
                , maEqMstrAsmbDetailDTO.getModelNo()
                , CommonUtil.getRowMoneyToNum(maEqMstrAsmbDetailDTO.getBuyAmt())
                , maEqMstrAsmbDetailDTO.getSpec()
                , CommonUtil.getRowDateToNum(maEqMstrAsmbDetailDTO.getSetupDate())
                , maEqMstrAsmbDetailDTO.getTagNo()
                , maEqMstrAsmbDetailDTO.getAsInfo()
                , maEqMstrAsmbDetailDTO.getEqCtgAsmbId()
                , maEqMstrAsmbDetailDTO.getEqCtgAsmbNo()
                , maEqMstrAsmbDetailDTO.getPeqCtgAsmbId()
                , maEqMstrAsmbDetailDTO.getEqCtgId()
                , maEqMstrAsmbDetailDTO.getPartId()
    			, CommonUtil.getRowDateToNum(maEqMstrAsmbDetailDTO.getGuarantyDate())
    			, maEqMstrAsmbDetailDTO.getEqSize()
    			, maEqMstrAsmbDetailDTO.getWeight() 
    			, maEqMstrAsmbDetailDTO.getSerialNo()
    			, CommonUtil.getRowDateToNum(maEqMstrAsmbDetailDTO.getCreDate())
    			, maEqMstrAsmbDetailDTO.getCreById()
    			, CommonUtil.getRowDateToNum(maEqMstrAsmbDetailDTO.getUpdDate())
    			, maEqMstrAsmbDetailDTO.getModifyById()
        };
        
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    public int resetEqAsmbFullDesc(String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                               ");
        query.append("SP_EQ_UPD_EQASMB_ALL('"+compNo+"'); ");
        query.append("end;                                ");
        
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
    }
    
    /**
     * detail copy
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * @param maEqMstrAsmbDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     */
    public String copyDetail(String oldEquipId, String newEquipId, String oldKeyId, String newKeyId, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAEQASMB              ");
        query.append("  (  comp_no        			    ");
        query.append("   , eqasmb_id   	  , eqasmb_no 	");
        query.append("   , equip_id       , description ");
        query.append("   , is_use         , ord_no      ");
        query.append("   , p_eqasmb_id      			");
        query.append("   , is_eqtype_asmb , remark      ");
        query.append("   , maker          , model_no    ");
        query.append("   , buy_amt        , spec        ");
        query.append("   , setup_date     , as_vendor   ");
    	query.append("   , part_id		  , guaranty_date");
    	query.append("   , eq_size		  ,	weight		 ");
    	query.append("   , serial_no	  , cre_date	 ");
    	query.append("   , cre_by		  , upd_date	 ");
    	query.append("   , upd_by						 ");
        query.append("	)								");
    	query.append("SELECT 							");
    	query.append("   comp_no	       	      		");
    	
    	if(!"".equals(newKeyId))
    	{	// Unit 복사인 경우
    		query.append("  , '"+newKeyId+"'            ");
    		query.append("  , '"+newKeyId+"'            ");
    	}
    	else
    	{	// 전체 복사인 경우
    		query.append("  , sqaeqasmb_id.NEXTVAL    	");
    		query.append("  , sqaeqasmb_id.NEXTVAL    	");
    	}
    	
    	query.append("   , ?		      , description ");
    	query.append("   , is_use         , ord_no      ");
    	
    	if(!"".equals(newKeyId))
    	{	// Unit 복사인 경우
    		query.append("   , p_eqasmb_id 	      		");
    	}
    	else
    	{	// 전체 복사인 경우
    		query.append("   , '0'		   	      		");
    	}
    	
    	query.append("   , is_eqtype_asmb , remark      ");
    	query.append("   , maker          , model_no    ");
    	query.append("   , buy_amt        , spec        ");
    	query.append("   , setup_date     , as_vendor   ");
    	query.append("   , part_id		  , guaranty_date");
    	query.append("   , eq_size		  ,	weight		 ");
    	query.append("   , serial_no	  , cre_date	 ");
    	query.append("   , cre_by		  , upd_date	 ");
    	query.append("   , upd_by						 ");
    	query.append("FROM TAEQASMB						");
    	query.append("WHERE comp_no   = ?				");
    	query.append("  AND equip_id  = ?				");
    	
    	// Unit 복사인 경우 Key 값을 넣어 하나만 복사한다.
    	query.getAndQuery("eqasmb_id", oldKeyId);
    	
    	Object[] objects = new Object[] {
    			newEquipId,
                user.getCompNo(),
                oldEquipId
        };
    	
    	getJdbcTemplate().update(query.toString(), objects);
    	
    	return "0";
    }
}