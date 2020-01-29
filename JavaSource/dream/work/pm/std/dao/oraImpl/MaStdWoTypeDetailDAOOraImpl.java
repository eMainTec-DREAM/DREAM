package dream.work.pm.std.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.pm.std.dao.MaStdWoTypeDetailDAO;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdWoTypeDetailDTO;
import dream.work.pm.std.dto.MaStdWoTypeListDTO;

/**
 * 표준항목 리스트 - 상세 dao
 * 
 * @author kim21017
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="maStdWoTypeDetailDAOTarget"
 * @spring.txbn id="maStdWoTypeDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaStdWoTypeDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaStdWoTypeDetailDAO
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
    public MaStdWoTypeDetailDTO findDetail(MaStdPointCommonDTO maStdPointCommonDTO, MaStdWoTypeListDTO maStdWoTypeListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        String compNo=  loginUser.getCompNo();
        
        query.append("SELECT												");
        query.append("		x.stwrkwork_id 			stWrkWorkId				");
        query.append("		, x.stwrk_id 			stWrkId					");
        query.append("		, x.wo_type 			woType					");
        query.append("		, (SELECT SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+loginUser.getLangId()+"') FROM DUAL) woTypeDesc	");
        query.append("		, x.pm_type 			pmType					");
        query.append("		, (SELECT SFACODE_TO_DESC(x.pm_type, x.wo_type||'_TYPE','SYS','','"+loginUser.getLangId()+"') FROM DUAL) pmTypeDesc	");
        query.append("		, x.remark 				remark  				");
        query.append("		, x.description 		description 			");
        query.append("FROM   TASTWRKWORK x									");
        query.append("WHERE  1=1											");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.stwrk_id", maStdPointCommonDTO.getStWrkId());
        query.getAndQuery("x.stwrkwork_id", maStdWoTypeListDTO.getStWrkWorkId());
    
        MaStdWoTypeDetailDTO maStdWoTypeDetailDTO = 
        		(MaStdWoTypeDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaStdWoTypeDetailDTO()));
        
        return maStdWoTypeDetailDTO;
    }
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdWoTypeDetailDTO
     * @return
     */
    public int insertDetail(MaStdWoTypeDetailDTO maStdWoTypeDetailDTO, MaStdPointCommonDTO maStdPointCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TASTWRKWORK                           ");
    	query.append("    (comp_no           ,stwrkwork_id              ");
    	query.append("    ,stwrk_id          ,wo_type                 	");
    	query.append("    ,remark            ,pm_type      				");
    	query.append("    ,description									");
    	query.append("    )    VALUES                                   ");
    	query.append("    (?                 ,?                         ");
    	query.append("     ,?                ,?                         ");
    	query.append("     ,?                ,?                         ");
    	query.append("     ,?											");
    	query.append("    )                                             ");
    	
    	Object[] objects = new Object[] {
    			loginUser.getCompNo()
    			,maStdWoTypeDetailDTO.getStWrkWorkId()
    			,maStdPointCommonDTO.getStWrkId()
    			,maStdWoTypeDetailDTO.getWoType()
    			,maStdWoTypeDetailDTO.getRemark()
    			,maStdWoTypeDetailDTO.getPmType()
    			,maStdWoTypeDetailDTO.getDescription()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdWoTypeDetailDTO
     * @return
     */
    public int updateDetail(MaStdWoTypeDetailDTO maStdWoTypeDetailDTO,MaStdPointCommonDTO maStdPointCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	Object[] objects;

    	query.append("UPDATE TASTWRKWORK  SET  ");
    	query.append("    wo_type         = ?  ");
    	query.append("    ,pm_type        = ?  ");
    	query.append("    ,description    = ?  ");
    	query.append("    ,remark         = ?  ");
    	query.append("WHERE stwrkwork_id  = ?  ");
    	query.append("  AND comp_no       = ?  ");
    	
    	objects = new Object[] {
    			maStdWoTypeDetailDTO.getWoType()
    			,maStdWoTypeDetailDTO.getPmType()
    			,maStdWoTypeDetailDTO.getDescription()
    			,maStdWoTypeDetailDTO.getRemark()
    			,maStdWoTypeDetailDTO.getStWrkWorkId()
    	        ,loginUser.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}