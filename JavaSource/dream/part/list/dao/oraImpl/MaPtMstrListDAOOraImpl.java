package dream.part.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.part.list.dao.MaPtMstrListDAO;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrListDTO;

/**
 * 보전자재분류(마스터) - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maPtMstrListDAOTarget"
 * @spring.txbn id="maPtMstrListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtMstrListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtMstrListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtMstrCommonDTO
     * @return List
     */
    public List findList(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser)
    { 
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT ''                                     seqNo,      ");
        query.append("       ''                                     isDelCheck, ");
        query.append("       x.comp_no                              compNo,     ");
        query.append("       x.part_id                              partId,     ");
        query.append("       x.part_id                              id,         ");
        query.append("       x.part_no                              partNo,     ");
        query.append("       x.description                          partName,   ");
        query.append("       x.pt_size                              ptSize,     ");
        query.append("       x.description || (case when x.pt_size is null then '' else ',' || x.pt_size end )  || (case when x.vendor_code is null then '' else ',' || x.vendor_code end)         partNameSize, ");
        query.append("       (SELECT SFACODE_TO_DESC(x.part_group, 'PART_GROUP', 'USR', x.comp_no,'"+loginUser.getLangId()+"') FROM DUAL)  partGroupDesc,       ");
        query.append("       x.maker                                maker,      ");
        query.append("       (SELECT SFACODE_TO_DESC(x.pt_abc_class, 'PT_ABC_CLASS', 'SYS', x.comp_no,'"+loginUser.getLangId()+"') FROM DUAL)  ptAbcClassDesc,");
        query.append("       x.model                                model,      ");
        query.append("       (SELECT SFACODE_TO_DESC(x.plf_type, 'PLF_TYPE', 'SYS', x.comp_no,'"+loginUser.getLangId()+"') FROM DUAL)  plfTypeDesc,");
        query.append("       x.seller                               seller,     ");
        query.append("       x.lead_time                            leadTime,   ");
        query.append("       x.vendor_code                          oldNo,   ");
        query.append("       x.last_gr_date                         lastGrDate, ");
        query.append("       x.last_iss_date                         lastIssDate, ");
        query.append("       (SELECT SFACODE_TO_DESC(x.mro_type, 'MRO_TYPE', 'SYS', x.comp_no,'"+loginUser.getLangId()+"') FROM DUAL)  mroType,");
        query.append("       x.usage                                            ");
        query.append("      ,x.is_asset_stock                            ISASSETSTOCKID   ");
        query.append("      ,(SELECT SFACODE_TO_DESC(x.is_asset_stock,'IS_USE','SYS',x.comp_no,'"+loginUser.getLangId()+"') FROM DUAL) ISASSETSTOCKDESC           ");
        query.append("      ,x.erp_part_no                          erpPartNo   ");
        query.append("      ,x.last_price                           lastPrice   ");
        query.append("      ,x.remark                           	remark   ");
        query.append("FROM   TAPARTS x                                          ");
    	query.append("WHERE  1=1												");
        query.append(this.getWhere(maPtMstrCommonDTO,loginUser));
        query.getOrderByQuery("part_no", maPtMstrCommonDTO.getOrderBy(), maPtMstrCommonDTO.getDirection());
    	
    	
//        return getJdbcTemplate().queryForList(query.toString());
        return getJdbcTemplate().queryForList(query.toString(maPtMstrCommonDTO.getIsLoadMaxCount(), maPtMstrCommonDTO.getFirstRow()));

    } 
    
    /**
     * Filter 조건
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtMstrCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPtMstrCommonDTO maPtMstrCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        if (!"".equals(maPtMstrCommonDTO.getPartId()))
        {
            query.getAndQuery("x.part_id", maPtMstrCommonDTO.getPartId());
            return query.toString();
        }
        query.getStringEqualQuery("x.IS_DELETED", "N");
        query.getStringEqualQuery("x.part_categ", "SPPT");
        // 품명/규격
        query.getLikeQuery("x.full_desc", maPtMstrCommonDTO.getFilterPartNameSize());
        
        query.getLikeQuery("x.part_no", maPtMstrCommonDTO.getFilterPartNo());
        query.getLikeQuery("x.vendor_code", maPtMstrCommonDTO.getFilterOldPartNo());
        query.getLikeQuery("x.model", maPtMstrCommonDTO.getFilterModel());
        query.getLikeQuery("x.maker", maPtMstrCommonDTO.getFilterMaker());
        query.getLikeQuery("x.seller", maPtMstrCommonDTO.getFilterSeller());
        query.getLikeQuery("x.usage", maPtMstrCommonDTO.getFilterUsage());
        query.getLikeQuery("x.vendor_code", maPtMstrCommonDTO.getFilterVendorPtCode());
        query.getAndDateQuery("x.out_upd_date", maPtMstrCommonDTO.getFilterOutStartUpdDate(), maPtMstrCommonDTO.getFilterOutEndUpdDate());
        
        // 내/외자 
        query.getSysCdQuery("x.plf_type", maPtMstrCommonDTO.getFilterPlfType(), maPtMstrCommonDTO.getFilterPlfTypeDesc(), "PLF_TYPE", maPtMstrCommonDTO.getFilterCompNo(),user.getLangId());
        // 자재중요도
        query.getSysCdQuery("x.pt_abc_class", maPtMstrCommonDTO.getFilterPtAbcClass(), maPtMstrCommonDTO.getFilterPtAbcClassDesc(), "PT_ABC_CLASS", maPtMstrCommonDTO.getFilterCompNo(),user.getLangId());
        // 수리/소모 구분
        query.getSysCdQuery("x.mro_type", maPtMstrCommonDTO.getFilterMroType(), maPtMstrCommonDTO.getFilterMroTypeDesc(), "MRO_TYPE", maPtMstrCommonDTO.getFilterCompNo(),user.getLangId());
        // 자재그룹
        query.getUsrCdLevelQuery("x.part_group", maPtMstrCommonDTO.getFilterPartGroup(), maPtMstrCommonDTO.getFilterPartGroupDesc(), "PART_GROUP", maPtMstrCommonDTO.getFilterCompNo(),user.getLangId());
        
        // 사용여부 
        query.getSysCdQuery("x.is_use", maPtMstrCommonDTO.getFilterIsUse(), maPtMstrCommonDTO.getFilterIsUseDesc(), "IS_USE", maPtMstrCommonDTO.getFilterCompNo(),user.getLangId());
        
        // 내부품번여부 
        query.getSysCdQuery("x.is_inpart", maPtMstrCommonDTO.getFilterIsInPart(), maPtMstrCommonDTO.getFilterIsInPartDesc(), "IS_USE", maPtMstrCommonDTO.getFilterCompNo(),user.getLangId());
        
        query.getSysCdQuery("x.is_serial_part", maPtMstrCommonDTO.getIsSerialPart(), maPtMstrCommonDTO.getIsSerialPart(), "IS_USE", maPtMstrCommonDTO.getFilterCompNo(),user.getLangId());
        query.getSysCdQuery("x.is_stock_control", maPtMstrCommonDTO.getIsStockControl(), maPtMstrCommonDTO.getIsStockControl(), "IS_USE", maPtMstrCommonDTO.getFilterCompNo(),user.getLangId());
        
        
        // 자재사용부서 
        if(!"".equals(maPtMstrCommonDTO.getFilterDeptId())||!"".equals(maPtMstrCommonDTO.getFilterDeptDesc()))
        {
            query.append("AND x.part_id IN (SELECT part_id FROM TAPTUSEDDEPT a  ");
            query.append("                  WHERE  comp_no = x.comp_no          ");
            query.getDeptLevelQuery("dept_id", maPtMstrCommonDTO.getFilterDeptId(), maPtMstrCommonDTO.getFilterDeptDesc(), maPtMstrCommonDTO.getFilterCompNo());
            query.append("                  )  ");
        }
        
        if("DAY".equals(maPtMstrCommonDTO.getSapParts()))
        {
        	query.append("AND x.part_no IN (SELECT 					");
        	query.append("        			       TRIM(matnr)     	");
        	query.append("       		    FROM   TXERPPARTS     	");
        	query.append("					WHERE  REPLACE(SUBSTR(received_date,0, 10),'-','') =  to_char(sysdate, 'yyyyMMdd'))      ");

        }
        else if("WEEK".equals(maPtMstrCommonDTO.getSapParts()))
        {
        	query.append("AND x.part_no IN (SELECT 					");
        	query.append("        				   TRIM(matnr)     	");
        	query.append("                  FROM   TXERPPARTS     	");
        	query.append("				    WHERE REPLACE(SUBSTR(received_date,0, 10),'-','') > TO_CHAR(TRUNC(SYSDATE, 'IW'), 'yyyyMMdd')     	  ");
        	query.append("    				  AND REPLACE(SUBSTR(received_date,0, 10),'-','') <= TO_CHAR(TRUNC(SYSDATE, 'IW')+6, 'yyyyMMdd'))    ");

        }
        
        //설비
        if(!"".equals(maPtMstrCommonDTO.getFilterEquipId()))
        {
            query.append(" AND x.part_id IN(SELECT a.part_id FROM TAEQPART a       ");
            query.append("                            WHERE a.comp_no=x.comp_no       ");
            query.getAndQuery("a.equip_id", maPtMstrCommonDTO.getFilterEquipId());
            query.append("                            )     ");
        }
        else if(!"".equals(maPtMstrCommonDTO.getFilterEquipDesc()))
        {
            query.append(" AND x.part_id IN(SELECT DISTINCT a.part_id FROM TAEQPART a       ");
            query.append("                            WHERE a.comp_no=x.comp_no         ");
            query.append("                            AND a.equip_id IN(SELECT aa.equip_id FROM TAEQUIPMENT aa      ");
            query.append("                                                    WHERE aa.comp_no=a.comp_no        ");
            query.getLikeQuery("aa.description", maPtMstrCommonDTO.getFilterEquipDesc());
            query.append("                                                    )     ");
            query.append("                            )     ");
        }
        
        //비고
        query.getLikeQuery("x.remark", maPtMstrCommonDTO.getFilterRemark());
        
        //저장품여부
        query.getSysCdQuery("x.is_asset_stock", maPtMstrCommonDTO.getFilterIsAssetStockId(), maPtMstrCommonDTO.getFilterIsAssetStockDesc(),"IS_USE", user.getCompNo(), user.getLangId());
        
        //ERP 품번
        query.getLikeQuery("x.erp_part_no", maPtMstrCommonDTO.getFilterErpPartNo());

        return query.toString();
    }

    /**
     * 삭제
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param partId
     * @return
     */
    public int updateDeletePartsFlag(MaPtMstrListDTO maPtMstrListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAPARTS SET            ");
        query.append("         IS_DELETED = 'Y'     ");
        query.append("        ,DESCRIPTION = '[Delete] '||DESCRIPTION     ");
        query.append("        ,DELETE_BY  = ?       ");
        query.append("        ,DELETE_TIME = ?      ");
        query.append("WHERE  comp_no  = ?           ");
        query.append("  AND  part_id  = ?           ");      
        
        Object[] objects = new Object[] {  
        		loginUser.getEmpId()
        		,DateUtil.getDate()
                ,loginUser.getCompNo()
                ,maPtMstrListDTO.getPartId()
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public int deleteStock(MaPtMstrListDTO maPtMstrListDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("DELETE TAPTSTOCK                                          ");
    	query.append("WHERE  comp_no  = ?                                       ");
    	query.append("  AND  part_id  = ?                                       ");      
    	
    	Object[] objects = new Object[] {   
    			loginUser.getCompNo(),
    			maPtMstrListDTO.getPartId()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public int deleteSaftyStock(MaPtMstrListDTO maPtMstrListDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("DELETE TAPTSAFTYSTOCK                                     ");
    	query.append("WHERE  comp_no  = ?                                       ");
    	query.append("  AND  part_id  = ?                                       ");      
    	
    	Object[] objects = new Object[] {   
    			loginUser.getCompNo(),
    			maPtMstrListDTO.getPartId()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    

    public String findTotalCount(MaPtMstrCommonDTO maPtMstrCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TAPARTS x                                          ");
        query.append("WHERE  1=1                                                ");
        query.append(this.getWhere(maPtMstrCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
    public void SP_IF_UPD_TXPARTS(User loginUser) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                 ");
        query.append("SP_IF_UPD_TXPARTS('"+loginUser.getCompNo()+"','"+loginUser.getUserNo()+"');         ");
        query.append("end;                                                  ");
        
        this.getJdbcTemplate().execute(query.toString());
    }
    public String getPartsSeq()
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT SQAPART_ID.NEXTVAL		");
    	query.append("FROM DUAL						");
    	
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    public int insertCopyDetail(String newId, String oldId, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAPARTS(                                      	");
    	query.append("   comp_no,      part_id,    part_no,     description,		");
    	query.append("   pt_size,      uom,        full_desc,   model,		    	");
    	query.append("   maker,        usage,      last_price,  plf_type,			");
    	query.append("   pco_type,     seller,     lead_time,   is_use,mro_type,	");
    	query.append("   upd_date,	   upd_by,     kind,        var_class,  		");
    	query.append("   part_group,   is_inpart,  vendor_code, part_categ,     	");
    	query.append("   remark, pt_abc_class								    	");
    	query.append(") SELECT						                        		");
    	query.append("   x.comp_no,      ?,    		 ?,     		'['||(SELECT a.key_name FROM TALANG a WHERE a.key_type='LABEL' AND a.key_no='copied' AND a.lang = ?)||']'||x.description,	");
    	query.append("   x.pt_size,     x.uom,       '['||(SELECT a.key_name FROM TALANG a WHERE a.key_type='LABEL' AND a.key_no='copied' AND a.lang = ?)||']'||x.full_desc,   x.model,		");
    	query.append("   x.maker,       x.usage,     x.last_price,  x.plf_type,		");
    	query.append("   x.pco_type,    x.seller,    x.lead_time, x.is_use,mro_type,");
    	query.append("   x.upd_date,	x.upd_by,    x.kind,        x.var_class,  	");
    	query.append("   x.part_group,  x.is_inpart, x.vendor_code, x.part_categ,   ");
    	query.append("   x.remark,      x.pt_abc_class								");
    	query.append("  FROM TAPARTS x												");
    	query.append("	WHERE 1=1													");
    	query.append("	AND x.comp_no = ?											");
    	query.append("	AND x.part_id = ?											");

    	Object[] objects = new Object[] {
    			newId,
    			newId,
    			loginUser.getLangId(),
    			loginUser.getLangId(),
    			loginUser.getCompNo(),
    			oldId
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }

	@Override
	public String getData(User user, MaPtMstrCommonDTO maPtMstrCommonDTO) {
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT 										");
		query.append("    CASE WHEN MIN(x.exceltab_id) IS NOT NULL 	");
		query.append("             THEN MIN(x.exceltab_id)|| ',' || min(x.description) || ',' || min(x.table_name) 	");
		query.append("             ELSE '0' 						");
		query.append("             END           					");
		query.append("FROM TAEXCELTAB x								");
		query.append("WHERE x.exceltab_no = ?						");
		
        Object[] objects = new Object[] {
        		maPtMstrCommonDTO.getExceltabNo()
        };

		return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
	}
}