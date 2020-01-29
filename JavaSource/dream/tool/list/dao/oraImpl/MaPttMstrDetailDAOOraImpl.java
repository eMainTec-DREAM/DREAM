package dream.tool.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.tool.list.dao.MaPttMstrDetailDAO;
import dream.tool.list.dto.MaPttMstrCommonDTO;
import dream.tool.list.dto.MaPttMstrDetailDTO;

/**
 * 보전자재분류(마스터) - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="maPttMstrDetailDAOTarget"
 * @spring.txbn id="maPttMstrDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPttMstrDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPttMstrDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param deptNo
     * @return
     */
    public MaPttMstrDetailDTO findDetail(MaPttMstrCommonDTO maPttMstrCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT								                    ");
        query.append("       x.comp_no		                        compNo,     ");
        query.append("       x.part_id                              partId,     ");
        query.append("       x.part_no                              partNo,     ");
        query.append("       x.description		                    description,");
        query.append("       x.pt_size                              ptSize,     ");
        query.append("       x.uom                                  uom,        ");
        query.append("       x.full_desc                            fullDesc,   ");
        query.append("       x.model                                model,      ");
        query.append("       x.maker                                maker,      ");
        query.append("       x.usage                                usage,      ");
        query.append("       x.last_price                           lastPrice,  ");
        query.append("       x.plf_type                             plfType,    ");
        query.append("       SFACODE_TO_DESC(x.plf_type, 'PLF_TYPE', 'SYS', x.comp_no,'"+loginUser.getLangId()+"') plfTypeDesc,");
        query.append("       x.mro_type                             mroType,    ");
        query.append("       SFACODE_TO_DESC(x.mro_type, 'MRO_TYPE', 'SYS', x.comp_no,'"+loginUser.getLangId()+"') mroTypeDesc,");
        query.append("       x.part_categ                           partCateg,    ");
        query.append("       SFACODE_TO_DESC(x.part_categ, 'PART_CATEG', 'SYS', x.comp_no,'"+loginUser.getLangId()+"') partCategDesc,");
        query.append("       x.pco_type                             pcoType,    ");
        //query.append("       SFAIDTODESC(x.pco_type, '', 'CDUSR', x.comp_no) pcdTypeDesc,");
        query.append("       x.seller                               seller,     ");
        query.append("       x.lead_time                            leadTime,   ");
        query.append("       x.is_use                                isUse,     ");
        query.append("       x.is_serial_part                       isSerial,   ");
        query.append("       x.kind,                                            ");
        query.append("       x.vendor_code                      vendorPtCode,   ");
        query.append("       x.part_group partGroup,                            ");
        query.append("       SFACODE_TO_DESC(x.part_group, 'PART_GROUP', 'USR', x.comp_no,'"+loginUser.getLangId()+"') partGroupDesc,");
        query.append("       x.var_class varClass                               ");
        query.append("FROM   TAPARTS x						                    ");
        query.append("WHERE  x.comp_no = '"+loginUser.getCompNo()+"'	");
        query.getAndQuery("x.part_categ", "TOOL");
        query.append("  AND  x.part_id = '"+maPttMstrCommonDTO.getPartId()+"'	");
    
        MaPttMstrDetailDTO maPttMstrDetailDTO = 
        		(MaPttMstrDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPttMstrDetailDTO()));
        
        return maPttMstrDetailDTO;
    }
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPttMstrDetailDTO
     * @return
     */
    public int insertDetail(MaPttMstrDetailDTO maPttMstrDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAPARTS(                                      ");
    	query.append("   comp_no,      part_id,    part_no,     description,	");
    	query.append("   pt_size,      uom,        full_desc,   model,		    ");
    	query.append("   maker,        usage,      last_price,  plf_type,		");
    	query.append("   pco_type,     seller,     lead_time,   is_use,mro_type,");
    	query.append("   upd_date,	   upd_by,     kind,        var_class,  	");
    	query.append("   part_group,   is_inpart,  vendor_code, part_categ,     ");
    	query.append("   is_serial_part                                         ");
    	query.append(")VALUES(							                        ");
    	query.append("	 ?,			   ?,		   ?,			upper(?),       ");
    	query.append("	 upper(?),	   ?,		   ?,		    ?,              ");
    	query.append("	 ?,			   ?,		   ?,		    ?,              ");
    	query.append("	 ?,	           ?,          ?,			?,		?,      ");
    	query.append("	 to_char(sysdate,'yyyymmdd'),	 ?,  ?, ?,              ");
    	query.append("   ?,            ?, ?,?,                                  ");
    	query.append("   ?                               					 ");
    	query.append(")													        ");
    	
    	Object[] objects = new Object[] {
    			maPttMstrDetailDTO.getCompNo(),
    			maPttMstrDetailDTO.getPartId(),
    			maPttMstrDetailDTO.getPartNo(),
    			maPttMstrDetailDTO.getDescription(),
    			maPttMstrDetailDTO.getPtSize(),
    			maPttMstrDetailDTO.getUom(),
    			maPttMstrDetailDTO.getFullDesc(),
    			maPttMstrDetailDTO.getModel(),
    			maPttMstrDetailDTO.getMaker(),
    			maPttMstrDetailDTO.getUsage(),
    			maPttMstrDetailDTO.getLastPrice(),
    			maPttMstrDetailDTO.getPlfType(),
    			maPttMstrDetailDTO.getPcoType(),
    			maPttMstrDetailDTO.getSeller(),
    			maPttMstrDetailDTO.getLeadTime(),
    			maPttMstrDetailDTO.getIsUse(),
    			maPttMstrDetailDTO.getMroType(),
    			loginUser.getUserId(),
    			maPttMstrDetailDTO.getKind(),
    			maPttMstrDetailDTO.getVarClass(),
    			maPttMstrDetailDTO.getPartGroup(),
    			"Y",
    			maPttMstrDetailDTO.getVendorPtCode(),
    			maPttMstrDetailDTO.getPartCateg(),
    			maPttMstrDetailDTO.getIsSerial()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPttMstrDetailDTO
     * @return
     */
    public int updateDetail(MaPttMstrDetailDTO maPttMstrDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAPARTS SET	                ");
    	query.append("	     description    = upper(?),     ");
    	query.append("       pt_size        = upper(?),     ");
        query.append("       uom            = ?,            ");
        query.append("       full_desc      = ?,            ");
        query.append("       model          = ?,            ");
        query.append("       maker          = ?,            ");
        query.append("       usage          = ?,            ");
        query.append("       last_price     = ?,            ");
        query.append("       plf_type       = ?,            ");
        query.append("       pco_type       = ?,            ");
        query.append("       seller         = ?,            ");
        query.append("       lead_time      = ?,            ");
        query.append("       mro_type       = ?,            ");
        query.append("       upd_date       = to_char(sysdate,'yyyymmdd'),            ");
        query.append("       upd_by         = ?,            ");
        query.append("       kind           = ?,            ");
        query.append("       var_class      = ?,            ");
        query.append("       part_group     = ?,            ");
        query.append("       part_categ     = ?,            ");
        query.append("       vendor_code    = ?,            ");
    	query.append("	     is_use		    = ?             ");
    	query.append("WHERE  comp_no        = ?	            ");
    	query.append("  AND  part_id        = ?	            ");
    	
    	Object[] objects = new Object[] {
                maPttMstrDetailDTO.getDescription(),
                maPttMstrDetailDTO.getPtSize(),
                maPttMstrDetailDTO.getUom(),
                maPttMstrDetailDTO.getFullDesc(),
                maPttMstrDetailDTO.getModel(),
                maPttMstrDetailDTO.getMaker(),
                maPttMstrDetailDTO.getUsage(),
                maPttMstrDetailDTO.getLastPrice(),
                maPttMstrDetailDTO.getPlfType(),
                maPttMstrDetailDTO.getPcoType(),
                maPttMstrDetailDTO.getSeller(),
                maPttMstrDetailDTO.getLeadTime(),
                maPttMstrDetailDTO.getMroType(),
                loginUser.getUserId(),
                maPttMstrDetailDTO.getKind(),
                maPttMstrDetailDTO.getVarClass(),
                maPttMstrDetailDTO.getPartGroup(),
                maPttMstrDetailDTO.getPartCateg(),
                maPttMstrDetailDTO.getVendorPtCode(),
                maPttMstrDetailDTO.getIsUse(),
                maPttMstrDetailDTO.getCompNo(),
                maPttMstrDetailDTO.getPartId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttMstrDetailDTO
     * @return
     */
    public String validPartNo(MaPttMstrDetailDTO maPttMstrDetailDTO, User loginUser)
    {
        
    	QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(*)                        ");
        query.append("FROM   TAPARTS x                        ");
        query.append("WHERE  x.comp_no = '" + maPttMstrDetailDTO.getCompNo() + "'");
        query.append("  AND  x.part_no = '" + maPttMstrDetailDTO.getPartNo() + "'");
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    
    }
    
    public void SP_IF_UPD_TXPARTS(User loginUser) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                 ");
        query.append("SP_IF_UPD_TXPARTS('"+loginUser.getCompNo()+"','"+loginUser.getUserNo()+"');         ");
        query.append("end;                                                  ");
        
        this.getJdbcTemplate().execute(query.toString());
    }
}