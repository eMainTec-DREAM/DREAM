package dream.work.pm.std.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.pm.std.dao.MaStdPartListDAO;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPartListDTO;

/**
 * 표준항목 리스트 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maStdPartListDAOTarget"
 * @spring.txbn id="maStdPartListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaStdPartListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaStdPartListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPointCommonDTO
     * @param maStdPartListDTO
     * @param loginUser
     * @return List
     */
    public List findStdPointList(MaStdPointCommonDTO maStdPointCommonDTO, MaStdPartListDTO maStdPartListDTO, User loginUser)
    { 
        QueryBuffer query = new QueryBuffer(); 
       
        query.append("SELECT																	");
        query.append("		''	 						seqNo									");
        query.append("		,'' 						isDelCheck								");
        query.append("		,(select y.part_no FROM TAPARTS y WHERE x.part_id=y.part_id AND x.comp_no=y.comp_no)partNo												");
        query.append("		,x.part_desc 				partDesc								");
        query.append("		,x.use_qty					useQty									");
        query.append("		,x.remark 					remark									");
        query.append("		,x.stwrk_part_id 			stwrkPartId								");
        query.append("		,x.stwrk_id 				stwrkId									");
        query.append("FROM   TASTWRKPART x														");
        query.append("WHERE  1=1                            									"); 
        query.append(this.getWhere(maStdPointCommonDTO,maStdPartListDTO,loginUser));
        query.append("ORDER by x.stwrk_part_id DESC												");
         
        return getJdbcTemplate().queryForList(query.toString());
    } 
    
    /**
     * Filter 조건
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPointCommonDTO
     * @param maStdPartListDTO
     * @param user
     * @return
     * @throws Exception
     */
    private String getWhere(MaStdPointCommonDTO maStdPointCommonDTO,MaStdPartListDTO maStdPartListDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.stwrk_id", maStdPointCommonDTO.getStWrkId()); 
     // CommonDTO의 equipNo가 있는경우는 상세에서 수정 되어서 List의 한 Row만을 재조회
        if (!"".equals(maStdPartListDTO.getStWrkPartId()))
        {
            query.getAndQuery("x.stwrk_part_id", maStdPartListDTO.getStWrkPartId());
            return query.toString();
        }
        return query.toString();
    }
    
    /**
     * 표준항목 삭제
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param id
     * @param loginUser
     * @return
     */
    public int deleteList(String id, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TASTWRKPOINT                             ");
        query.append("WHERE  comp_no         = ?                      ");
        query.append("  AND  stwrk_point_id  = ?                      ");

        Object[] objects = new Object[] {   
        		loginUser.getCompNo(),
                id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
}