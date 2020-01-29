package dream.work.pm.std.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.pm.std.dao.MaStdPartDetailDAO;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPartDetailDTO;
import dream.work.pm.std.dto.MaStdPartListDTO;

/**
 * 표준항목 리스트 - 상세 dao
 * 
 * @author kim21017
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="maStdPartDetailDAOTarget"
 * @spring.txbn id="maStdPartDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaStdPartDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaStdPartDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0 
     * 
     * @param maStdPointCommonDTO
     * @param loginUser
     * @return
     */
    public MaStdPartDetailDTO findDetail(MaStdPointCommonDTO maStdPointCommonDTO, MaStdPartListDTO maStdPartListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        String compNo=  loginUser.getCompNo();
        
        query.append("SELECT																		");
        query.append("		x.stwrk_part_id 		stWrkPartId										");
        query.append("		,x.stwrk_id 			stWrkId											");
        query.append("		,x.part_id 				partId											");
        query.append("		,(SELECT y.part_no FROM TAPARTS y where x.part_id=y.part_id AND x.comp_no=y.comp_no)	partNo					");
        query.append("		,x.part_desc 			partDesc										");
        query.append("		,x.use_qty				useQty											");
        query.append("		,x.remark 				remark											");
        query.append("FROM   TASTWRKPART x															");
        query.append("WHERE  1=1																	");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.stwrk_id", maStdPointCommonDTO.getStWrkId());
        query.getAndQuery("x.stwrk_part_id", maStdPartListDTO.getStWrkPartId());
    
        MaStdPartDetailDTO maStdPartDetailDTO = 
        		(MaStdPartDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaStdPartDetailDTO()));
        
        return maStdPartDetailDTO;
    }
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPartDetailDTO
     * @return
     */
    public int insertDetail(MaStdPartDetailDTO maStdPartDetailDTO, MaStdPointCommonDTO maStdPointCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TASTWRKPART                             ");
    	query.append("    (comp_no,           stwrk_part_id,              ");
    	query.append("     stwrk_id,          part_id,                    ");
    	query.append("     part_desc,         remark                      ");
    	query.append("	 , use_qty										  ");
    	query.append("    )    VALUES                                      ");
    	query.append("    (?,                ?,                            ");
    	query.append("     ?,                ?,                            ");
    	query.append("     ?,                ?                             ");
    	query.append("	 , ? 											   ");
    	query.append("    )                                                ");
    	
    	Object[] objects = new Object[] {
    			loginUser.getCompNo(),
    			maStdPartDetailDTO.getStWrkPartId()
    			,maStdPointCommonDTO.getStWrkId()
    			,maStdPartDetailDTO.getPartId()
    			,maStdPartDetailDTO.getPartDesc()
    			,maStdPartDetailDTO.getRemark()
    			,maStdPartDetailDTO.getUseQty()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPartDetailDTO
     * @return
     */
    public int updateDetail(MaStdPartDetailDTO maStdPartDetailDTO,MaStdPointCommonDTO maStdPointCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	Object[] objects;

    	query.append("UPDATE TASTWRKPART  SET   ");
    	query.append("    part_id         = ?,  ");
    	query.append("    part_desc       = ?,  ");
    	query.append("    use_qty         = ?,  ");
    	query.append("    remark          = ?   ");
    	query.append("WHERE stwrk_part_id = ?   ");
    	query.append("  AND comp_no       = ?   ");
    	
    	objects = new Object[] {
    			maStdPartDetailDTO.getPartId()
    			,maStdPartDetailDTO.getPartDesc()
    			,maStdPartDetailDTO.getUseQty()
    			,maStdPartDetailDTO.getRemark()
    			,maStdPartDetailDTO.getStWrkPartId()
    	        ,loginUser.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}