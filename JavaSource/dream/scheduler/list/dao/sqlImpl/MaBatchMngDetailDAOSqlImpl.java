package dream.scheduler.list.dao.sqlImpl;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.scheduler.list.dao.MaBatchMngDetailDAO;
import dream.scheduler.list.dto.MaBatchMngCommonDTO;
import dream.scheduler.list.dto.MaBatchMngDetailDTO;

/**
 * 버튼 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaBatchMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maBatchMngDetailDAOTarget"
 * @spring.txbn id="maBatchMngDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaBatchMngDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaBatchMngDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaBatchMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBatchMngCommonDTO
     * @return
     */
    public MaBatchMngDetailDTO findDetail(MaBatchMngCommonDTO maBatchMngCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT x.batpgm_id AS batPgmId								");
        query.append("		,x.batpgm_no AS batPgmNo								");
        query.append("		,x.description AS batPgmDesc							");
        query.append("		,x.is_exec AS isExec									");
        query.append("		,x.batch_pgm AS batchPgm								");
        query.append("		,x.remark AS execRemark									");
        query.append("		,CONVERT(VARCHAR,CONVERT(DATETIME,						");
        query.append("			SUBSTRING(x.last_exe_time,1,8)+' '+					");
        query.append("			SUBSTRING(x.last_exe_time,9,2)+':'+					");
        query.append("			SUBSTRING(x.last_exe_time,11,2)+':'+				");
        query.append("			SUBSTRING(x.last_exe_time,13,2)),20) AS lastExeTime	");
        query.append("		,(SELECT user_name										");
        query.append("			FROM TAUSER											");
        query.append("			WHERE comp_no = x.comp_no							");
        query.append("			AND user_id=x.exe_by) AS exeBy						");
        query.append("FROM TABATPGM x												");
        query.append("WHERE 1=1														");
        query.getAndQuery("x.batpgm_id", maBatchMngCommonDTO.getBatPgmId());
        MaBatchMngDetailDTO maBatchMngDetailDTO = 
        		(MaBatchMngDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaBatchMngDetailDTO()));
        
        return maBatchMngDetailDTO;
    }
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaBatchMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBatchMngDetailDTO
     * @return
     */
    public int insertDetail(MaBatchMngDetailDTO maBatchMngDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TABATPGM								");
    	query.append("	(batpgm_id,		batpgm_no,		batch_pgm,		");
    	query.append("	 is_exec,		remark,         description,	");
    	query.append("	 comp_no										");
    	query.append("	)	VALUES										");
    	query.append("	(?,				?,				?,				");
    	query.append("	 ?,				?,              ?,				");
    	query.append("	 ?												");
    	query.append("	)												");
    	
    	Object[] objects = new Object[] {
    			maBatchMngDetailDTO.getBatPgmId(),
    			maBatchMngDetailDTO.getBatPgmNo(),
    			maBatchMngDetailDTO.getBatchPgm(),
    			maBatchMngDetailDTO.getIsExec(),
    			maBatchMngDetailDTO.getExecRemark(),
    			maBatchMngDetailDTO.getBatPgmDesc(),
    			maBatchMngDetailDTO.getCompNo()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaBatchMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBatchMngDetailDTO
     * @return
     */
    public int updateDetail(MaBatchMngDetailDTO maBatchMngDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TABATPGM SET	");
    	query.append("	batpgm_no	= ?,	");
    	query.append("	batch_pgm	= ?,	");
    	query.append("	is_exec		= ?,	");
    	query.append("	remark		= ?,	");
    	query.append("	description = ?		");
    	query.append("WHERE batpgm_id = ?	");
    	query.append("  AND comp_no	 = ?	");
    	
    	Object[] objects = new Object[] {
    			maBatchMngDetailDTO.getBatPgmNo(),
    			maBatchMngDetailDTO.getBatchPgm(),
    			maBatchMngDetailDTO.getIsExec(),
    			maBatchMngDetailDTO.getExecRemark(),
    			maBatchMngDetailDTO.getBatPgmDesc(),
    			maBatchMngDetailDTO.getBatPgmId(),
    			maBatchMngDetailDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}