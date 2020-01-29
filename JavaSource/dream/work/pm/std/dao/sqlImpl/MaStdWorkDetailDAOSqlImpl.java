package dream.work.pm.std.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.work.pm.std.dao.MaStdWorkDetailDAO;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdWorkDetailDTO;
import dream.work.pm.std.dto.MaStdWorkListDTO;

/**
 * 표준항목 리스트 - 상세 dao
 * 
 * @author kim21017
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="maStdWorkDetailDAOTarget"
 * @spring.txbn id="maStdWorkDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaStdWorkDetailDAOSqlImpl extends BaseJdbcDaoSupportOra implements MaStdWorkDetailDAO
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
    public MaStdWorkDetailDTO findDetail(MaStdPointCommonDTO maStdPointCommonDTO, MaStdWorkListDTO maStdWorkListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String compNo=  loginUser.getCompNo();
        
        query.append("SELECT																		");
        query.append("		x.stwrkworkprc_id stWrkWorkPrcId,										");
        query.append("		x.stwrkwork_id stWrkWorkId,												");
        query.append("		x.stwrk_id stWrkId,														");
        query.append("		x.step_num stepNum,														");
        query.append("		x.description woDesc,													");
        query.append("		x.remark remark,														");
        query.append("		dbo.SFACODE_TO_DESC((SELECT y.wo_type FROM TASTWRKWORK y WHERE x.stwrkwork_id=y.stwrkwork_id AND x.comp_no=y.comp_no),'WO_TYPE','SYS','','"+loginUser.getLangId()+"') woTypeDesc		");
        query.append("FROM   TASTWRKWORKPRC x														");
        query.append("WHERE  1=1																	");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.stwrk_id", maStdPointCommonDTO.getStWrkId());
        query.getAndQuery("x.stwrkworkprc_id", maStdWorkListDTO.getStWrkWorkPrcId());
    
        MaStdWorkDetailDTO maStdWorkDetailDTO = 
        		(MaStdWorkDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaStdWorkDetailDTO()));
        
        return maStdWorkDetailDTO;
    }
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdWorkDetailDTO
     * @return
     */
    public int insertDetail(MaStdWorkDetailDTO maStdWorkDetailDTO, MaStdPointCommonDTO maStdPointCommonDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TASTWRKWORKPRC                            ");
    	query.append("    (comp_no,           stwrkworkprc_id,              ");
    	query.append("     stwrkwork_id,      stwrk_id,                     ");
    	query.append("     step_num,          			                    ");
    	query.append("     description,       remark                       ");
    	query.append("    )    VALUES                                      ");
    	query.append("    (?,                ?,                            ");
    	query.append("     ?,                ?,                            ");
    	query.append("     ?,                                              ");
    	query.append("     ?,                ?                             ");
    	query.append("    )                                                ");
    	
    	Object[] objects = new Object[] {
    			loginUser.getCompNo()
    			,maStdWorkDetailDTO.getStWrkWorkPrcId()
    			,maStdWorkDetailDTO.getStWrkWorkId()
    			,maStdPointCommonDTO.getStWrkId()
    			,maStdWorkDetailDTO.getStepNum()
    			,maStdWorkDetailDTO.getWoDesc()
    			,maStdWorkDetailDTO.getRemark()

    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdWorkDetailDTO
     * @return
     */
    public int updateDetail(MaStdWorkDetailDTO maStdWorkDetailDTO,MaStdPointCommonDTO maStdPointCommonDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	Object[] objects;

    	query.append("UPDATE TASTWRKWORKPRC  SET  ");
    	query.append("    step_num        = ?,  ");
    	query.append("    stwrkwork_id       = ?,  ");
    	query.append("    description        = ?,  ");
    	query.append("    remark            = ?  ");
    	query.append("WHERE stwrkworkprc_id= ?   ");
    	query.append("  AND comp_no       = ?   ");
    	
    	objects = new Object[] {
    			maStdWorkDetailDTO.getStepNum()
    			,maStdWorkDetailDTO.getStWrkWorkId()
    			,maStdWorkDetailDTO.getWoDesc()
    			,maStdWorkDetailDTO.getRemark()
    			,maStdWorkDetailDTO.getStWrkWorkPrcId()
    			,loginUser.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}