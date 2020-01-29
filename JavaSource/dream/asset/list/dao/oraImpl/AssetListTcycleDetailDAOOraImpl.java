package dream.asset.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.asset.list.dao.AssetListTcycleDetailDAO;
import dream.asset.list.dto.AssetListTcycleDetailDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * 교정주기 상세 dao
 * @author  kim21017
 * @version $Id: AssetListTcycleDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="assetListTcycleDetailDAOTarget"
 * @spring.txbn id="assetListTcycleDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetListTcycleDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements AssetListTcycleDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: AssetListTcycleDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param equipId
     * @param eqPmCycleId
     * @param compNo
     * @return
     */
    public AssetListTcycleDetailDTO findDetail(String equipId, String eqPmCycleId, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	String compNo = user.getCompNo();
        
		query.append("SELECT											");
		query.append("       x.eqpmcycle_id					eqPmCycleId,");
		query.append("       y.pm_id						pmId,		");
        query.append("       y.cycle						cycle,		");
        query.append("       y.period_type					periodType,	");
        query.append("       SFACODE_TO_DESC(y.period_type, 'PERIOD_TYPE', 'SYS', '"+compNo+"','"+user.getLangId()+"') periodTypeDesc,	");
        query.append("       z.init_wrk_date				initWrkDate,");
        query.append("       y.wo_res_bef					woResBef,	");
        query.append("       y.wkctr_id						wkCtrId,	");
        query.append("		 (SELECT description						");
        query.append("		  FROM TAWKCTR								");
        query.append("		  WHERE comp_no = x.comp_no					");
        query.append("		  AND wkctr_id = y.wkctr_id)	wkCtrDesc,	");
        query.append("       y.is_active					isActive,	");
        query.append("       z.pmequip_id					pmEquipId,	");
        query.append("       y.wrkcallist_id				wrkcalListId,");
        query.append("       (SELECT a.description						");
        query.append("          FROM TAWRKCALLIST a						");
        query.append("          WHERE 1=1								");
        query.append("          AND a.comp_no = y.comp_no				");
        query.append("          AND a.wrkcallist_id = y.wrkcallist_id	");
        query.append("          )						wrkcalListDesc,	");
        query.append("       y.remark					remark,			");
        query.append("       z.last_sch_date			calDate,		");
        query.append("       z.next_sch_date			nextCalDate		");
//        query.append("	 	 (SELECT min(sched_date) FROM tapmsched WHERE pm_id=x.pm_id			");
//        query.append("			AND pmsched_status<>'C') calDate,								");
//        query.append("		(SELECT min(sched_date) FROM tapmsched WHERE pm_id=x.pm_id			");
//        query.append("			AND sched_date >												");
//        query.append("				(SELECT min(sched_date) FROM tapmsched WHERE pm_id=x.pm_id	");
//        query.append("				AND pmsched_status<>'C')) nextCalDate						");
        query.append("       , (SELECT a.is_last_version FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.equip_id)       isLastVersion    ");
        query.append("       ,y.plant       ");
        query.append("       ,(select a.description from taplant a where a.plant = y.plant) plantDesc   ");
        query.append("FROM   TAEQPMCYCLE x, TAPMLST y, TAPMEQUIP z		");
        query.append("WHERE	 x.pm_id 			= y.pm_id				");
        query.append("	AND	 x.pm_id 			= z.pm_id				");
        query.append("	AND	 x.eqpmcycle_id 	= ?                  	");
        query.append("  AND  x.comp_no			= ?             		");
        query.append("	AND	 y.is_deleted 		= 'N'					");
        query.append("	AND	 z.is_deleted 		= 'N'					");

        Object[] objects = new Object[] {
        		eqPmCycleId
        		,compNo
    	};
    
        AssetListTcycleDetailDTO assetListTcycleDetailDTO = 
        		(AssetListTcycleDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new AssetListTcycleDetailDTO()));
        
        return assetListTcycleDetailDTO;
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: AssetListTcycleDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param assetListTcycleDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     */
    public int insertDetail(AssetListTcycleDetailDTO assetListTcycleDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAEQPMCYCLE			");
    	query.append("	(comp_no,		eqpmcycle_id,	");
    	query.append("	 equip_id,		pm_id			");
    	query.append("	)	VALUES						");
    	query.append("	(?,				?,				");
    	query.append("	 ?,				?				");
    	query.append("	)								");
    	
    	Object[] objects = new Object[] {
    			maEqMstrCommonDTO.getCompNo(),
    			assetListTcycleDetailDTO.getEqPmCycleId(),
    			maEqMstrCommonDTO.getEquipId(),
    			assetListTcycleDetailDTO.getPmId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    public String getLabelDesc(User user, String key_no){
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT key_name FROM TALANG	");
    	query.append("WHERE 1=1						");
    	query.append("AND key_type='LABEL' 			");
    	query.append(" AND key_no = '"+key_no+"'	");
    	
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
}