package dream.part.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.part.list.dao.MaPtMstrDetailDAO;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrDetailDTO;

/**
 * 보전자재분류(마스터) - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="maPtMstrDetailDAOTarget"
 * @spring.txbn id="maPtMstrDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtMstrDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPtMstrDetailDAO
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
    public MaPtMstrDetailDTO findDetail(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT								                    ");
        query.append("       x.comp_no		                        compNo,     ");
        query.append("       x.part_id                              partId,     ");
        query.append("       x.part_no                              partNo,     ");
        query.append("       x.description		                    description,");
        query.append("       x.pt_size                              ptSize,     ");
        query.append("       x.uom                                  uom,        ");
        query.append("       dbo.SFACODE_TO_DESC(x.uom, 'UOM', 'USR', x.comp_no,'"+loginUser.getLangId()+"') uomDesc,        ");
        query.append("       x.full_desc                            fullDesc,   ");
        query.append("       x.model                                model,      ");
        query.append("       x.maker                                maker,      ");
        query.append("       x.usage                                usage,      ");
        query.append("       x.last_price                           lastPrice,  ");
        query.append("       x.pt_abc_class                         ptAbcClass, ");
        query.append("       x.is_inpart                         	ISINPART,	");
        query.append("       dbo.SFACODE_TO_DESC(x.pt_abc_class, 'PT_ABC_CLASS', 'SYS', x.comp_no,'"+loginUser.getLangId()+"') ptAbcClassDesc,");
        query.append("       x.plf_type                             plfType,    ");
        query.append("       dbo.SFACODE_TO_DESC(x.plf_type, 'PLF_TYPE', 'SYS', x.comp_no,'"+loginUser.getLangId()+"') plfTypeDesc,");
        query.append("       x.mro_type                             mroType,    ");
        query.append("       dbo.SFACODE_TO_DESC(x.mro_type, 'MRO_TYPE', 'SYS', x.comp_no,'"+loginUser.getLangId()+"') mroTypeDesc,");
        query.append("       x.part_categ                           partCateg,    ");
        query.append("       dbo.SFACODE_TO_DESC(x.part_categ, 'PART_CATEG', 'SYS', x.comp_no,'"+loginUser.getLangId()+"') partCategDesc,");
        
        query.append("        x.cycle,                                                                                  ");
        query.append("        x.period_type periodType,                                                                 ");
        query.append("        dbo.SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE', 'SYS', x.comp_no,'"+loginUser.getLangId()+"') periodTypeDesc,          ");
        
        query.append("       x.pco_type                             pcoType,    ");
        //query.append("       dbo.SFAIDTODESC(x.pco_type, '', 'CDUSR', x.comp_no) pcdTypeDesc,");
        query.append("       x.seller                               seller,     ");
        query.append("       x.lead_time                            leadTime,   ");
        query.append("       x.is_use                                isUse,     ");
        query.append("       dbo.SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', x.comp_no,'"+loginUser.getLangId()+"') isUseDesc,     ");
        query.append("       x.remark                                remark,    ");
        query.append("       x.kind,                                            ");
        query.append("       x.vendor_code                      vendorPtCode,   ");
        query.append("       x.last_gr_date                      lastGrDate,    ");
        query.append("       x.last_iss_date                      lastIssDate,  ");
        //query.append("       x.vendor_code                      vendorPtCode,   ");
        query.append("       x.part_group partGroup,                            ");
        query.append("       x.is_serial_part isSerialPart,                     ");
        query.append("       dbo.SFACODE_TO_DESC(x.is_serial_part, 'IS_USE', 'SYS', x.comp_no,'"+loginUser.getLangId()+"') isSerialPartDesc,     ");
        query.append("       x.is_stock_control isStockControl,                 ");
        //query.append("       dbo.SFACODE_TO_DESC(x.is_stock_control,'IS_USE','SYS',x.comp_no,'"+loginUser.getLangId()+"') isStockControlDesc,           ");
        query.append("       dbo.SFACODE_TO_DESC(x.is_stock_control, 'IS_USE', 'SYS', x.comp_no,'"+loginUser.getLangId()+"') isStockControlDesc,     ");
        query.append("       dbo.SFACODE_TO_DESC(x.part_group, 'PART_GROUP', 'USR', x.comp_no,'"+loginUser.getLangId()+"') partGroupDesc,");
        query.append("       dbo.SFACODE_TO_DESC(x.var_class, 'VAR_CLASS', 'USR', x.comp_no,'"+loginUser.getLangId()+"') varClassDesc,");
        query.append("       x.is_asset_stock                            ISASSETSTOCKID   ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.is_asset_stock,'IS_USE','SYS',x.comp_no,'"+loginUser.getLangId()+"') ISASSETSTOCKDESC           ");
        query.append("      ,x.erp_part_no                          erpPartNo   ");
        query.append("      , x.currency                          																AS currencyId  		");
        query.append("      , dbo.SFACODE_TO_DESC(x.currency, 'CURRENCY', 'SYS', x.comp_no,'"+ loginUser.getLangId() +"') 		AS currencyDesc		");
        query.append("      , x.is_serial_part 																					AS isSerialPartId	");
        query.append("      , dbo.SFACODE_TO_DESC(x.is_serial_part, 'IS_USE', 'SYS', x.comp_no,'"+ loginUser.getLangId() +"') 	AS isSerialPartDesc	");
        query.append("FROM   TAPARTS x						                    ");
        query.append("WHERE  x.comp_no = '"+loginUser.getCompNo()+"'	");
        query.getAndQuery("x.part_categ", "SPPT");
        query.append("  AND  x.part_id = '"+maPtMstrCommonDTO.getPartId()+"'	");
    
        MaPtMstrDetailDTO maPtMstrDetailDTO = 
        		(MaPtMstrDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPtMstrDetailDTO()));
        
        return maPtMstrDetailDTO;
    }
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtMstrDetailDTO
     * @return
     */
    public int insertDetail(MaPtMstrDetailDTO maPtMstrDetailDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAPARTS(                                      							");
    	query.append("		comp_no			, part_id			, part_no			, description			");
    	query.append("	  , pt_size			, uom				, full_desc			, model		    		");
    	query.append("    , maker			, usage				, last_price		, plf_type				");
    	query.append("    , pco_type		, seller			, lead_time			, is_use   				");
    	query.append("    , mro_type		, upd_date			, upd_by			, kind				  	");
    	query.append("    , var_class 		, part_group		, is_inpart			, vendor_code		    ");
    	query.append("    , part_categ		, remark        	, pt_abc_class		, cycle					");
    	query.append("    , period_type		, is_serial_part	, is_stock_control	, is_asset_stock		");
    	query.append("    , erp_part_no		, currency                                             			");
    	query.append(") VALUES (							                        						");
    	query.append("		?			   	, ?		   			, ?					, isnull(upper(?), '')  ");
    	query.append("	  , isnull(upper(?), ''), ?		   		, ?		    		, ?              		");
    	query.append("	  , ?			   	, ?		   			, ?		    		, ?              		");
    	query.append("	  , ?	           	, ?         		, ?					, ?		      			");
    	query.append("	  , CONVERT(VARCHAR, GETDATE(), 112),? 	, ?  				, ?              		");
    	query.append("    , ?            	, ?          		, ?           		, ?              		");
    	query.append("    , ?            	, ?          		, ?           		, ?               		");
    	query.append("    , ?            	, ?  		 		, ?					, ?						");
    	query.append("    , ?            	, ?																");
    	query.append(")													        							");
    	
    	Object[] objects = new Object[] {
    			 maPtMstrDetailDTO.getCompNo()
    		   , maPtMstrDetailDTO.getPartId()
    		   , maPtMstrDetailDTO.getPartNo()
    		   , maPtMstrDetailDTO.getDescription()  
    		   , maPtMstrDetailDTO.getPtSize()
    		   , maPtMstrDetailDTO.getUom()
    		   , maPtMstrDetailDTO.getFullDesc()
    		   , maPtMstrDetailDTO.getModel()
    		   , maPtMstrDetailDTO.getMaker()
    		   , maPtMstrDetailDTO.getUsage()
    		   , maPtMstrDetailDTO.getLastPrice()
    		   , maPtMstrDetailDTO.getPlfType()
    		   , maPtMstrDetailDTO.getPcoType()
    		   , maPtMstrDetailDTO.getSeller()
    		   , maPtMstrDetailDTO.getLeadTime()
    		   , maPtMstrDetailDTO.getIsUse() 
    		   , maPtMstrDetailDTO.getMroType() 
    		   , loginUser.getUserId()
    		   , maPtMstrDetailDTO.getKind()
    		   , maPtMstrDetailDTO.getVarClass()
    		   , maPtMstrDetailDTO.getPartGroup()
    		   , "Y"
    		   , maPtMstrDetailDTO.getVendorPtCode()
    		   , maPtMstrDetailDTO.getPartCateg()
    		   , maPtMstrDetailDTO.getRemark()
    		   , maPtMstrDetailDTO.getPtAbcClass() 
    		   , maPtMstrDetailDTO.getCycle()
    		   , maPtMstrDetailDTO.getPeriodType()
    		   , maPtMstrDetailDTO.getIsSerialPart()
    		   , maPtMstrDetailDTO.getIsStockControl() 
    		   , maPtMstrDetailDTO.getIsAssetStockId()
    		   , maPtMstrDetailDTO.getErpPartNo()
    		   , maPtMstrDetailDTO.getCurrencyId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    
    public int createStock(MaPtMstrDetailDTO maPtMstrDetailDTO,User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("INSERT INTO TAPTSTOCK							");
    	query.append("(comp_no , wcode_id, part_id, part_grade,		");
    	query.append("stock_qty, bin_no, unit_price)				");
    	query.append("select x.comp_no,y.wcode_id, x.part_id, z.cdsysd_no,0,'',x.last_price");
    	query.append("from taparts x, tawarehouse y, (select * from tacdsysd where list_type='PART_GRADE') z");
    	query.append("where x.comp_no  = y.comp_no		");
    	query.append("and x.comp_no =	?				");
    	query.append("and x.part_id =	?				");
    	query.append("and z.is_use=		?				");
    	query.append("and y.wh_categ=	?				");
    	query.append("and y.is_use=		?				");
    	
    	Object[] objects = new Object[] {
    			loginUser.getCompNo(),
    			maPtMstrDetailDTO.getPartId(),
    			"Y",
    			"PART",
    			"Y"
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtMstrDetailDTO
     * @return
     */
    public int updateDetail(MaPtMstrDetailDTO maPtMstrDetailDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAPARTS SET	                						");
    	query.append("	     part_no    		= ?,		    					");
    	query.append("	     description    	= isnull(upper(?),'')     			");
    	query.append("     , pt_size        	= isnull(upper(?),'')     			");
        query.append("     , uom            	= ?            						");
        query.append("     , full_desc      	= ?            						");
        query.append("     , model         	 	= ?            						");
        query.append("     , maker          	= ?            						");
        query.append("     , usage          	= ?            						");
        query.append("     , last_price     	= ?            						");
        query.append("     , plf_type       	= ?            						");
        query.append("     , pco_type       	= ?            						");
        query.append("     , seller         	= ?            						");
        query.append("     , lead_time      	= ?            						");
        query.append("     , mro_type       	= ?            						");
        query.append("     , upd_date       	= CONVERT(VARCHAR, GETDATE(), 112)	");
        query.append("     , upd_by         	= ?            						");
        query.append("     , kind           	= ?            						");
        query.append("     , var_class      	= ?            						");
        query.append("     , part_group     	= ?            						");
        query.append("     , part_categ     	= ?            						");
        query.append("     , vendor_code    	= ?            						");
        query.append("     , remark         	= ?            						");
        query.append("     , pt_abc_class   	= ?            						");
        query.append("     , cycle          	= ?            						");
        query.append("     , period_type    	= ?           						");
    	query.append("     , is_serial_part 	= ?            						");
        query.append("     , is_stock_control 	= ?          						");
        query.append("     , is_asset_stock 	= ?          						");
    	query.append("	   , is_use		    	= ?            						");
    	query.append("     , erp_part_no    	= ?             					");
    	query.append("     , currency    		= ?             					");
    	query.append("WHERE  comp_no        	= ?	            					");
    	query.append("  AND  part_id        	= ?	            					");
    	
    	Object[] objects = new Object[] {
    			maPtMstrDetailDTO.getPartNo(),
                maPtMstrDetailDTO.getDescription()
               , maPtMstrDetailDTO.getPtSize()
               , maPtMstrDetailDTO.getUom()
               , maPtMstrDetailDTO.getFullDesc()
               , maPtMstrDetailDTO.getModel()
               , maPtMstrDetailDTO.getMaker()
               , maPtMstrDetailDTO.getUsage()
               , CommonUtil.getRowMoneyToNum(maPtMstrDetailDTO.getLastPrice())
               , maPtMstrDetailDTO.getPlfType()
               , maPtMstrDetailDTO.getPcoType()
               , maPtMstrDetailDTO.getSeller()
               , maPtMstrDetailDTO.getLeadTime()
               , maPtMstrDetailDTO.getMroType()
               , loginUser.getUserId()
               , maPtMstrDetailDTO.getKind()
               , maPtMstrDetailDTO.getVarClass()
               , maPtMstrDetailDTO.getPartGroup()
               , maPtMstrDetailDTO.getPartCateg()
               , maPtMstrDetailDTO.getVendorPtCode()
               , maPtMstrDetailDTO.getRemark()
               , maPtMstrDetailDTO.getPtAbcClass()
               , maPtMstrDetailDTO.getCycle()
               , maPtMstrDetailDTO.getPeriodType()
               , maPtMstrDetailDTO.getIsSerialPart()
               , maPtMstrDetailDTO.getIsStockControl()
               , maPtMstrDetailDTO.getIsAssetStockId()
               , maPtMstrDetailDTO.getIsUse()
               , maPtMstrDetailDTO.getErpPartNo()
               , maPtMstrDetailDTO.getCurrencyId()
               , maPtMstrDetailDTO.getCompNo()
               , maPtMstrDetailDTO.getPartId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtMstrDetailDTO
     * @return
     */
    public int updateEqPartCycle(MaPtMstrDetailDTO maPtMstrDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.append("update taeqpart set            ");
    	query.append("    cycle = ?                  ");
    	query.append("    ,period_type = ?           ");
    	query.append("where comp_no = ?              ");
    	query.append("    and part_id = ?            ");
    	query.append("    and cycle is null          ");
    	query.append("    and isnull(period_type,'') = ''    ");


    	
    	Object[] objects = new Object[] {
    			maPtMstrDetailDTO.getCycle(),
    			maPtMstrDetailDTO.getPeriodType(),
    			maPtMstrDetailDTO.getCompNo(),
                maPtMstrDetailDTO.getPartId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtMstrDetailDTO
     * @return
     */
    public String validPartNo(MaPtMstrDetailDTO maPtMstrDetailDTO, User loginUser)
    {
        
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(*)                        ");
        query.append("FROM   TAPARTS x                        ");
        query.append("WHERE  x.comp_no = '" + maPtMstrDetailDTO.getCompNo() + "'");
        query.append("  AND  x.part_no = '" + maPtMstrDetailDTO.getPartNo() + "'");
        query.append("  AND  x.part_id != " + maPtMstrDetailDTO.getPartId() + " ");
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    
    }
    

    public void SP_IF_UPD_TXPARTS(User loginUser) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("EXEC dbo.SP_IF_UPD_TXPARTS '"+loginUser.getCompNo()+"','"+loginUser.getUserNo()+"';         ");
        
        this.getJdbcTemplate().execute(query.toString());
    }
}