package dream.comm.revision.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.comm.revision.dao.CommRevDAO;
import dream.comm.revision.dto.CommRevCommonDTO;

/**
 * 상세 dao
 * 
 * @author jung7126
 * @version $Id: CommRevDAO.java,v 1.0 2015/12/02 08:25:47 jung7126 Exp $
 * @since 1.0
 * @spring.bean id="commRevDAOTarget"
 * @spring.txbn id="commRevDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class CommRevDAOSqlImpl extends BaseJdbcDaoSupportSql implements CommRevDAO
{
	/**
     * Detail 조희
     * @author jung7126
     * @version $Id: CommRevDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param woType
     * @param pmType
     * @return
     * @throws Exception
     */
    public CommRevCommonDTO findDetail(CommRevCommonDTO commRevCommonDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("	SELECT 	  											");
    	query.append("			x.object_id			AS oldObjectId  		");
    	query.append("			,x.revisionhist_id 	AS oldRevisionhistId	");
    	query.append("			,? 					AS description  		");
    	query.append("			,x.object_no		AS objectNo		  		");
    	query.append("			,x.doc_no 			AS docNo  				");
    	query.append("			,x.revision_object_type AS revisionObjType	");
    	query.append("			,x.rev_no 			AS oldRevNo  			");
    	query.append("			,? 					AS param				");
    	query.append("	FROM 	TAREVISIONHIST x 							");
    	query.append("	WHERE 	x.comp_no			= ?						");
    	query.append("	AND		x.revisionhist_id 	= ? 					");
    	
    	Object[] objects = new Object[] {
    			commRevCommonDTO.getDescription()
    			,commRevCommonDTO.getParam()
    			,user.getCompNo()
    			,commRevCommonDTO.getOldRevisionhistId()
    	};
    	
    	CommRevCommonDTO resultDTO = 
        		(CommRevCommonDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new CommRevCommonDTO()));
        
        return resultDTO;
    }
    
    /**
     * detail insert
     * @author jung7126
     * @version $Id: CommRevDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param commRevCommonDTO
     * @return
     */
    public int insertRevHist(CommRevCommonDTO commRevCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("INSERT INTO TAREVISIONHIST               				");
    	query.append("(                                        				");
    	query.append("  revisionhist_id,			comp_no,            	");
    	query.append("  object_id,  				object_no,       		");
    	query.append("	revision_object_type,      	doc_no,                 ");
        query.append("  revision_status,            revision_step_status,   ");
    	query.append("  revision_type,           	rev_no,        			");
    	query.append("  wrk_date,           		wrk_id,         		");
    	query.append("  rev_desc       				   						");
    	query.append(")VALUES                                  				");
    	query.append("(                                        				");
    	query.append("    ?,               ?,                  				");
    	query.append("    ?,               ?,                  				");
    	query.append("    ?,               ?,                    			");
    	query.append("    ?,               ?,                    			");
    	query.append("    ?,               ?,                  				");
    	query.append("    ?,               ?,                  				");
    	query.append("    ?                                   				");
    	query.append(")                                        				");

    	
    	Object[] objects = new Object[] {
    	        commRevCommonDTO.getRevisionhistId()
                ,commRevCommonDTO.getCompNo()
                ,commRevCommonDTO.getObjectId()
                ,commRevCommonDTO.getObjectNo()
                ,commRevCommonDTO.getRevisionObjType()
                ,commRevCommonDTO.getDocNo()
                ,commRevCommonDTO.getRevisionStatusId()
    	        ,"".equals(commRevCommonDTO.getRevisionStepStatusId())?"WRT":commRevCommonDTO.getRevisionStepStatusId()
                ,commRevCommonDTO.getRevisionType()
                ,commRevCommonDTO.getRevNo()
                ,CommonUtil.dateToData(commRevCommonDTO.getWrkDate())
                ,commRevCommonDTO.getWrkEmpId()
                ,commRevCommonDTO.getRevDesc()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    public int insertPm(CommRevCommonDTO commRevCommonDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("INSERT INTO TAPMLST                       ");
        query.append("(                                         ");
        query.append("  pm_id,              comp_no,            ");
        query.append("  pm_no,              description,        ");
        query.append("  revisionhist_id,    revision_status,    ");
        query.append("  is_last_version,    dept_id,            ");
        query.append("  pm_type,            is_active,          ");
        query.append("  wo_type,            work_number,        ");
        query.append("  plant,         		cre_date,			");
        query.append("  cre_by,             schedule_type 		");
        query.append(")VALUES                                   ");
        query.append("(                                         ");
        query.append("    ?,               ?,                   ");
        query.append("    ?,               ?,                   ");
        query.append("    ?,               ?,                   ");
        query.append("    ?,               ?,                   ");
        query.append("    ?,               ?,                   ");
        query.append("    ?,               ?,                   ");
        query.append("    ?,               ?,                   ");
        query.append("    ?,			   ?					");
        query.append(")                                         ");
        
        Object[] objects = new Object[] {
                commRevCommonDTO.getObjectId()
                ,loginUser.getCompNo()
                ,commRevCommonDTO.getObjectNo()
                ,commRevCommonDTO.getDescription()
                ,commRevCommonDTO.getRevisionhistId()
                ,commRevCommonDTO.getRevisionStatusId()
                ,"N"
                ,commRevCommonDTO.getWrkDeptId()
                ,commRevCommonDTO.getSelectedPmType()
                ,"N"
                ,commRevCommonDTO.getSelectedWoType()
                ,"1"
                ,loginUser.getPlant()
    			,DateUtil.getDateTime("yyyyMMddHHmmss")
    			,loginUser.getUserId()
    			,"T"
        };
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    public int insertAsset(CommRevCommonDTO commRevCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAEQUIPMENT               ");
        query.append("  (comp_no,       equip_id,           ");
        query.append("   item_no,       description,        ");
        query.append("   eq_status,     dept_id,            ");
        query.append("   main_mng_id,   eqctg_type,         ");
        query.append("   is_last_version,revisionhist_id,   ");
        query.append("   revision_status,eqctg_id,          ");
        query.append("   plant,         p_equip_id,         ");
        query.append("   eqloc_id,      cre_date,           ");
        query.append("   cre_by                             ");
        query.append("  )   VALUES                          ");
        query.append("  (?,             ?,                  ");
        query.append("   dbo.IS_EMPTY(?),             ?,    ");
        query.append("   ?,             dbo.IS_EMPTY(?),    ");
        query.append("   dbo.IS_EMPTY(?),             ?,    ");
        query.append("   ?,             dbo.IS_EMPTY(?),    ");
        query.append("   ?,             dbo.IS_EMPTY(?),    ");
        query.append("   ?,             dbo.IS_EMPTY(?),    ");
        query.append("   dbo.IS_EMPTY(?),?,                 ");
        query.append("   ?                                  ");
        query.append("  )                                   ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
                ,commRevCommonDTO.getObjectId()
                ,commRevCommonDTO.getObjectNo()
                ,commRevCommonDTO.getDescription()
                ,"R"
                ,commRevCommonDTO.getWrkDeptId()
                ,commRevCommonDTO.getWrkEmpId()
                ,commRevCommonDTO.getEqCtgTypeId()
                ,"N"
                ,commRevCommonDTO.getRevisionhistId()
                ,commRevCommonDTO.getRevisionStatusId()
                ,commRevCommonDTO.getEqCtgId()
                ,user.getPlant()
                ,commRevCommonDTO.getParentEquipId()
                ,commRevCommonDTO.getParentEqLocId()
    			,DateUtil.getDateTime("yyyyMMddHHmmss")
    			,user.getUserId()
        };
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    public int insertMold(CommRevCommonDTO commRevCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAEQMOLD                              ");
        query.append("  (comp_no,       equip_id                        ");
        query.append("  )   VALUES                                      ");
        query.append("  (?,             ?                               ");
        query.append("  )                                               ");
        
        Object[] objects = new Object[] {
                commRevCommonDTO.getCompNo()
                ,commRevCommonDTO.getObjectId()
        };
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    public int insertTool(CommRevCommonDTO commRevCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAEQTOOL              ");
        query.append("  (comp_no,       equip_id        ");
        query.append("  )   VALUES                      ");
        query.append("  (?,             ?               ");
        query.append("  )                               ");
        
        Object[] objects = new Object[] {
                commRevCommonDTO.getCompNo()
                ,commRevCommonDTO.getObjectId()
        };
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    public int insertBuilding(CommRevCommonDTO commRevCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAEQBUILDING                          ");
        query.append("  (comp_no,       equip_id                        ");
        query.append("  )   VALUES                                      ");
        query.append("  (?,             ?                               ");
        query.append("  )                                               ");
        
        Object[] objects = new Object[] {
                commRevCommonDTO.getCompNo()
                ,commRevCommonDTO.getObjectId()
        };
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    public int insertDevice(CommRevCommonDTO commRevCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAEQDEVICE                            ");
        query.append("  (comp_no,       equip_id                        ");
        query.append("  )   VALUES                                      ");
        query.append("  (?,             ?                               ");
        query.append("  )                                               ");
        
        Object[] objects = new Object[] {
                commRevCommonDTO.getCompNo()
                ,commRevCommonDTO.getObjectId()
        };
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    public int insertEqItDetail(CommRevCommonDTO commRevCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("INSERT INTO TAEQIT(		");
    	query.append("	 comp_no				");
    	query.append("	,equip_id				");
    	query.append(")VALUES(					");
    	query.append("	 ?						");
    	query.append("	,?						");
    	query.append(")							");
    	
    	Object[] objects = new Object[] {
    			commRevCommonDTO.getCompNo()
    			,commRevCommonDTO.getObjectId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    public int insertStwrk(CommRevCommonDTO commRevCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TASTWRKLST                ");
        query.append("    (comp_no,         stwrk_id,       ");
        query.append("     stwrk_no,        description,    ");
        query.append("     reg_date,        reg_by,         ");
        query.append("     is_last_version, revisionhist_id,");
        query.append("     revision_status  ,stwrk_type     ");
    	query.append("	  ,is_active		,stwrk_status	");
        query.append("    )    VALUES                       ");
        query.append("    (?,               ?,              ");
        query.append("     ?,               ?,              ");
        query.append("     ?,               ?,              ");
        query.append("     ?,               ?,              ");
        query.append("     ?               ,?               ");
    	query.append("	  ,?			   ,?				");
        query.append("    )                                 ");
        
        Object[] objects = new Object[] {
                commRevCommonDTO.getCompNo()
                ,commRevCommonDTO.getObjectId()
                ,commRevCommonDTO.getObjectNo()
                ,commRevCommonDTO.getDescription()
                ,commRevCommonDTO.getWrkDate()
                ,commRevCommonDTO.getEnterBy()
                ,"N"
                ,commRevCommonDTO.getRevisionhistId()
                ,commRevCommonDTO.getRevisionStatusId()
    	        ,"S"
    	        ,"Y"
    	        ,"W"
        };
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    
    public int updateRevHist(CommRevCommonDTO commRevCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAREVISIONHIST SET         ");
        query.append("  revision_status         = ?     ");
        query.append("  ,revision_step_status   = ?     ");
        query.append("WHERE comp_no             = ?     ");
        query.append("AND   revisionhist_id     = ?     ");
        
        Object[] objects = new Object[] {
                commRevCommonDTO.getRevisionStatusId()
                ,commRevCommonDTO.getRevisionStepStatusId()
                ,user.getCompNo()
                ,commRevCommonDTO.getRevisionhistId()
        };
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    public int updatePm(String objectId, String compNo, String isLastVersion, String revStat)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAPMLST SET            ");
        query.append("  is_last_version     = ?     ");
        query.append("  ,revision_status    = ?     ");
        query.append("WHERE comp_no = ?             ");
        query.append("AND   pm_id   = ?             ");
        
        Object[] objects = new Object[] {
        		isLastVersion
                ,revStat
                ,compNo
                ,objectId
        };
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    public int updateAsset(String objectId, String compNo, String isLastVersion, String revStat)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAEQUIPMENT SET        ");
        query.append("  is_last_version = ?         ");
        query.append("  ,revision_status = ?        ");
        query.append("WHERE 1=1                     ");
        query.append("AND comp_no   = ?             ");
        query.append("AND equip_id  = ?             ");
        
        Object[] objects = new Object[] {
        		isLastVersion
                ,revStat
                ,compNo
                ,objectId
        };
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    public int updateOldAsset(String objectId, String newObjectId, String compNo, String isLastVersion, String revStat)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAEQUIPMENT SET				");
    	query.append("	is_last_version 	= ?				");
    	query.append("	, revision_status 	= ?				");
    	query.append("	, equip_id			= ?				");
    	query.append("WHERE 1=1								");
    	query.append("AND comp_no 	= ?						");
    	query.append("AND equip_id 	= ?						");
    	
    	Object[] objects = new Object[] {
    			isLastVersion
    			, revStat
    			, newObjectId
    			, compNo
    			, objectId
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    public int updateNewAsset(String objectId, String oldObjectId, String compNo, String isLastVersion, String revStat)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAEQUIPMENT SET		");
    	query.append("	is_last_version		= ?		");
    	query.append("	, revision_status 	= ?		");
    	query.append("	, equip_id 			= ?		");
    	query.append("WHERE 1=1						");
    	query.append("AND comp_no 	= ?				");
    	query.append("AND equip_id 	= ?				");
    	
    	Object[] objects = new Object[] {
    			isLastVersion
    			, revStat
    			, oldObjectId
    			, compNo
    			, objectId
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * 이미지 object_id 변경
     * @author syyang
     * @version $Id$
     * @since   1.0
     * @param oldObjectId
     * @param newObjectId
     * @param objectType
     * @param user
     * @return
     */
    public String fileImgMove(String oldObjectId, String newObjectId, String objectType, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAIMAGE SET			");
    	query.append("		object_id		= ?		");
    	query.append("WHERE  comp_no 		= ?		");
    	query.append("  AND  object_type	= ?		");
    	query.append("  AND  object_id		= ?		");
    	
    	Object[] objects = new Object[] {
    			newObjectId
    			, user.getCompNo()
    			, objectType
    			, oldObjectId
    	};
    	
    	getJdbcTemplate().update(query.toString(), objects);
    	
    	return "0";
    }
    /**
     * 첨부문서 탭 object_id 변경
     * @author syyang
     * @version $Id$
     * @since   1.0
     * @param oldObjectId
     * @param newObjectId
     * @param objectType
     * @param user
     * @return
     */
    public String fileDocMove(String oldObjectId, String newObjectId, String objectType, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAOBJDOC SET			");
    	query.append("		object_id		= ?		");
    	query.append("WHERE  comp_no 		= ?		");
    	query.append("  AND  object_type	= ?		");
    	query.append("  AND  object_id		= ?		");
    	
    	Object[] objects = new Object[] {
    			newObjectId
    			, user.getCompNo()
    			, objectType
    			, oldObjectId
    	};
    	
    	getJdbcTemplate().update(query.toString(), objects);
    	
    	return "0";
    }
    public int updateEqIdRevHist(String oldObjectId, String newObjectId, String oldObjectNo, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAREVISIONHIST SET	        ");
    	query.append("   object_id				= ?		");
    	query.append("WHERE comp_no 			= ?     ");
    	query.append("AND	object_id 			= ?     ");
    	query.append("AND	object_no 			= ?     ");
    	
    	Object[] objects = new Object[] {
    			newObjectId
    			, compNo
    			, oldObjectId
    			, oldObjectNo
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * 설비부위 탭 equip_id 변경
     * @author syyang
     * @version $Id$
     * @since   1.0
     * @param oldEquipId
     * @param newEquipId
     * @param user
     * @return
     */
    public String updateEqIdAsmb(String oldEquipId, String newEquipId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAEQASMB SET		");
        query.append("		equip_id	=	?	");
    	query.append("WHERE comp_no   = ?		");
    	query.append("  AND equip_id  = ?		");
    	
    	Object[] objects = new Object[] {
    			newEquipId,
                user.getCompNo(),
                oldEquipId
        };
    	getJdbcTemplate().update(query.toString(), objects);
    	
    	return "0";
    }
    /**
     * 설비제원 탭 equip_id 변경
     * @author syyang
     * @version $Id$
     * @since   1.0
     * @param oldEquipId
     * @param newEquipId
     * @param user
     * @return
     */
    public String updateEqIdSpec(String oldEquipId, String newEquipId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAEQSPEC SET			");
    	query.append("		equip_id	= ?			");
    	query.append("WHERE comp_no   = ?			");
    	query.append("  AND equip_id  = ?			");
    	
    	Object[] objects = new Object[] {
    			newEquipId
    		  ,	user.getCompNo()
  			  , oldEquipId
	  	};
	  	getJdbcTemplate().update(query.toString(), objects);
	  	
	  	return "0";
    }
    /**
     * 설비부품 탭 equip_id 변경
     * @author syyang
     * @version $Id$
     * @since   1.0
     * @param oldEquipId
     * @param newEquipId
     * @param user
     * @return
     */
    public String updateEqIdPart(String oldEquipId, String newEquipId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAEQPART SET			");
    	query.append("		equip_id	= ?			");
    	query.append("WHERE comp_no   = ?			");
    	query.append("  AND equip_id  = ?			");
        
    	Object[] objects = new Object[] {
    			  newEquipId
      		  	, user.getCompNo()
    			, oldEquipId
    	};
    	
    	getJdbcTemplate().update(query.toString(), objects);
    	
    	return "0";
    }
    /**
     * 설비자산 탭 equip_id 변경
     * @author syyang
     * @version $Id$
     * @since   1.0
     * @param oldEquipId
     * @param newEquipId
     * @param user
     * @return
     */
    public String updateEqIdAsset(String oldEquipId, String newEquipId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAEQASSET SET			");
    	query.append("		equip_id	= ?			");
        query.append("WHERE  comp_no 		= ?		");
        query.append("  AND  equip_id		= ?		");
    	
    	Object[] objects = new Object[] {
    			  newEquipId
      		  	, user.getCompNo()
      		  	, oldEquipId
    	};
    	
    	getJdbcTemplate().update(query.toString(), objects);
    	
    	return "0";
	}
    /**
     * 예방작업설비 equip_id 변경
     * @author syyang
     * @version $Id$
     * @since   1.0
     * @param oldEquipId
     * @param newEquipId
     * @param user
     * @return
     */
    public String updateEqIdPmEquip(String oldEquipId, String newEquipId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAPMEQUIP SET			");
    	query.append("		equip_id	= ?			");
    	query.append("WHERE  comp_no 		= ?		");
    	query.append("  AND  equip_id		= ?		");
    	
    	Object[] objects = new Object[] {
    			newEquipId
    			, user.getCompNo()
    			, oldEquipId
    	};
    	
    	getJdbcTemplate().update(query.toString(), objects);
    	
    	return "0";
    }
    /**
     * 설비이력 equip_id 변경
     * @author syyang
     * @version $Id$
     * @since   1.0
     * @param oldEquipId
     * @param newEquipId
     * @param user
     * @return
     */
    public String updateEqIdEqHist(String oldEquipId, String newEquipId, User user)
	{
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAEQALTHIST SET		");
    	query.append("		equip_id	= ?			");
        query.append("WHERE  comp_no 		= ?		");
        query.append("  AND  equip_id		= ?		");

    	Object[] objects = new Object[] {
    			newEquipId
    			, user.getCompNo()
    			, oldEquipId
    	};
    	
    	this.getJdbcTemplate().update(query.toString(), objects);
    	
    	return "0";
	}
    public int updateApprObjectId(String oldObjectId, String newObjectId, String apprType, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAAPPRLIST SET	        ");
    	query.append("   object_id			= ?		");
    	query.append("WHERE comp_no 		= ?     ");
    	query.append(" AND	APPR_TYPE 		= ?     ");
    	query.append("  AND	object_id 		= ?     ");
    	
    	Object[] objects = new Object[] {
    			newObjectId
    			, compNo
    			, apprType
    			, oldObjectId
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    public int updateAuditTrailObjectId(String oldObjectId, String newObjectId, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TATRACELOG SET	        ");
    	query.append("   object_id			= ?		");
    	query.append("WHERE comp_no 		= ?     ");
    	query.append(" AND	object_id 		= ?     ");
    	query.append("  AND	file_name IN ('assetListGnMstrDetail','assetListITDetail'		");
    	query.append("  	,'maEqBuildingMstrDetail','maEqMoldMstrDetail','maEqMstrDetail'	");
    	query.append("  	,'maEqMstrMoldDetail','maEqPartMstrDetail','maEqToolMstrDetail'	");
    	query.append("  	,'maEqUtilityMstrDetail','maEqVehicleMstrDetail'	)			");
    	
    	Object[] objects = new Object[] {
    			newObjectId
    			, compNo
    			, oldObjectId
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
        
    public int updateStwrk(String objectId, String compNo, String isLastVersion, String revStat)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TASTWRKLST SET         ");
        query.append("  is_last_version = ?         ");
        query.append("  ,revision_status = ?        ");
        query.append("WHERE 1=1                     ");
        query.append("AND comp_no   = ?             ");
        query.append("AND stwrk_id  = ?             ");
        
        Object[] objects = new Object[] {
        		isLastVersion
                ,revStat
                ,compNo
                ,objectId
        };
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * Validation
     * 저장시 각종 Validation
     * @author hankyul
     * @version $Id: CommRevCommonDTO.java,v 1.0 2017/11/03 16:39:47 hankyul Exp $
     * @since   1.0
     * 
     * @param commRevCommonDTO
     * @param user
     * @return
     */
    public String validRevNo(CommRevCommonDTO commRevCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SELECT count(*)           ");
        query.append("FROM TAREVISIONHIST       ");
        query.append("WHERE 1=1                 ");
        query.getAndQuery("comp_no", user.getCompNo());
        query.getAndQuery("revision_object_type", commRevCommonDTO.getRevisionObjType());
        query.getAndQuery("object_no", commRevCommonDTO.getObjectNo());
        query.getAndQuery("rev_no", commRevCommonDTO.getRevNo());
        
    	return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    /**
     * 현재 가장 최신버전의 ID값 조회
     * @author hankyul
     * @version $Id: CommRevCommonDTO.java,v 1.0 2017/11/03 16:39:47 hankyul Exp $
     * @since   1.0
     * 
     * @param objectNo
     * @param compNo
     * @return
     */
    public List findOldPm(String objectNo, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT pm_id objId, pm_type pmType FROM TAPMLST 	");
        query.append("WHERE  comp_no = '"+compNo+"'		");
        query.append("  AND  pm_no = '"+objectNo+"'		");
        query.append("  AND  is_last_version = 'Y'		");
     
        return getJdbcTemplate().queryForList(query.toString());
    }
    public String findOldAsset(String objectNo, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT equip_id FROM TAEQUIPMENT 		");
        query.append("WHERE comp_no = '"+compNo+"'			");
        query.append("  AND item_no = '"+objectNo+"'		");
        query.append("  AND is_last_version = 'Y'			");
     
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    public String findOldStwrk(String objectNo, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT stwrk_id FROM TASTWRKLST		");
        query.append("WHERE comp_no = '"+compNo+"'			");
        query.append("  AND stwrk_no = '"+objectNo+"'		");
        query.append("  AND is_last_version = 'Y'			");
     
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    public String validPmNo(CommRevCommonDTO commRevCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SELECT count(*) 			");
    	query.append("FROM TAPMLST x			");
    	query.append("WHERE 1=1					");
    	query.getAndQuery("x.comp_no", user.getCompNo());
    	query.getAndQuery("x.pm_no", commRevCommonDTO.getObjectNo());
        query.append("AND EXISTS  (SELECT 1                         ");
        query.append("             FROM TAPMEQUIP A, TAEQUIPMENT b           ");
        query.append("             WHERE A.comp_no = b.comp_no               ");
        query.append("               AND A.equip_id = b.equip_id         ");
        query.append("               AND A.pm_id  = CASE WHEN (SELECT COUNT(1) FROM TAPMEQUIP c WHERE c.pm_id = x.pm_id) > 0 THEN  x.pm_id   ");
        query.append("                              ELSE A.pm_id    ");
        query.append("                              END     ");
        query.getStringEqualQuery("a.comp_no", user.getCompNo());
        query.getAndQuery("a.is_deleted", "N");
        query.getAndQuery("b.is_deleted", "N");
        query.append("             )  			");
    	
    	return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    public String validStwrkNo(CommRevCommonDTO commRevCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SELECT count(*) 			");
    	query.append("FROM TASTWRKLST			");
    	query.append("WHERE 1=1					");
    	query.getAndQuery("comp_no", user.getCompNo());
    	query.getAndQuery("stwrk_no", commRevCommonDTO.getObjectNo());
    	
    	return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }

	@Override
	public String maxRevNo(CommRevCommonDTO commRevCommonDTO, User user)
	{
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SELECT TOP(1)                         ");
		query.append("    x.rev_no AS RevNo	                ");
		query.append("FROM TAREVISIONHIST x                 ");
		query.append("WHERE x.comp_no = ?                   ");
		query.append(" AND  x.object_no = ?                 ");
		query.append("  AND  x.revision_object_type = ?     ");
		query.append("ORDER BY CONVERT(float,x.rev_no) DESC ");

		Object[] objects = new Object[] {
        		user.getCompNo()
        		,commRevCommonDTO.getObjectNo()
        		,commRevCommonDTO.getRevisionObjType()
        };
        
        List resultList = getJdbcTemplate().queryForList(query.toString(), objects);

        return QuerySqlBuffer.listToString(resultList);
	}
	
	public String findRevStatus(CommRevCommonDTO commRevCommonDTO, User user)
	{
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT 	 							");
		query.append("    revision_status	AS RevStatus	");
		query.append("FROM TAREVISIONHIST x        			");
		query.append("WHERE x.comp_no = ?	            	");
		query.append(" AND  x.object_no = ?		        	");
		query.append(" AND  x.object_id = ?		        	");
		query.append("  AND  x.revision_object_type = ?		");
		
		Object[] objects = new Object[] {
				user.getCompNo()
				,commRevCommonDTO.getObjectNo()
				,commRevCommonDTO.getObjectId()
				,commRevCommonDTO.getRevisionObjType()
		};

		List resultList = getJdbcTemplate().queryForList(query.toString(), objects);
		
		return QuerySqlBuffer.listToString(resultList);
	}

    @Override
    public String[] getPMTracelogId(String id, String compNo)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                        ");
        query.append("       tracelog_id            ");
        query.append("FROM   TATRACELOG             ");
        query.append("WHERE  object_id = ?          ");
        query.append("  AND  comp_no = ?            ");
        query.append("  AND  file_name IN (         ");
        query.append("                     'maPmRplMstrDetail','maPmRprMstrDetail','maPmRplMstrDetail','maPmClnMstrDetail','maPmOilMstrDetail','maPmGmMstrDetail','workPmListRInsDetail'    ");
        query.append("                     )        ");

        
        Object[] objects = new Object[] {
                id
                ,compNo
        };

        List resultList = getJdbcTemplate().queryForList(query.toString(), objects);
        
        return QueryBuffer.toStringSingleArray(resultList);
    }

    @Override
    public void copyAuditTrailObjectId(String newSeq, String id, String compNo,
            String newTracelogId, String tracelogId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO TATRACELOG        ");
        query.append("SELECT                        ");
        query.append("       update_time, ?, file_name, user_no, emp_no, dept_no, data_cud_type, comp_no, ?    ");
        query.append("FROM TATRACELOG               ");
        query.append("WHERE tracelog_id = ?         ");
        query.append("  AND comp_no     = ?         ");


        
        Object[] objects = new Object[] {
                newTracelogId,
                newSeq
                ,tracelogId
                ,compNo
        };

        getJdbcTemplate().update(query.toString(), objects);
    }

    @Override
    public void copyAuditTrailDtlObjectId(String newSeq, String id,
            String compNo, String newTraceLogId, String tracelogId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO TATRACELOGDTL         ");
        query.append("SELECT NEXT VALUE FOR sqatracelogdtl_id, ?, field_id, field_value    ");
        query.append("FROM TATRACELOGDTL                ");
        query.append("WHERE tracelog_id = ?             ");
        
        Object[] objects = new Object[] {
                newTraceLogId,
                tracelogId
        };

        getJdbcTemplate().update(query.toString(), objects);

    }

    @Override
    public void updateEqIdTool(String oldObjectId, String oldNewObjectId,
            User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAEQTOOL SET           ");
        query.append("      equip_id    = ?         ");
        query.append("WHERE  comp_no        = ?     ");
        query.append("  AND  equip_id       = ?     ");
        
        Object[] objects = new Object[] {
                oldNewObjectId
                , user.getCompNo()
                , oldObjectId
        };
        
        getJdbcTemplate().update(query.toString(), objects);
    }

}