package dream.doc.ctg.dao.oraImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.doc.ctg.dao.DocCtgDetailDAO;
import dream.doc.ctg.dto.DocCtgDetailDTO;

/**
 * 문서분류체계 - 상세 dao
 * 
 * @author ssong
 * @version $Id: DocCtgDetailDAO.java,v 1.0 2015/12/02 08:25:47 ssong Exp $
 * @since 1.0
 * @spring.bean id="docCtgDetailDAOTarget"
 * @spring.txbn id="docCtgDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class DocCtgDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements DocCtgDetailDAO
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param docCtgCommonDTO
     * @return
     */
    public DocCtgDetailDTO findDetail(String docCtgId, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT																														");
        query.append("        x.description,                                             											                ");
        query.append("        x.docctg_no docctgNo,                                                     											");
        query.append("        x.is_use isUse,                                                           											");
        query.append("        x.ord_no ordNo,                                                             											");
        query.append("        x.docctg_id docctgId,                                                       											");
        query.append("        x.p_docctg_id pdocctgId,                                                   											");
        query.append("        (SELECT a.description FROM TADOCCTG a WHERE a.docctg_id = x.p_docctg_id AND a.comp_no = x.comp_no) pdocctgDesc,      ");
        query.append("        x.remark,                                                                  											");
        query.append("        x.comp_no compNo,                                                                                                     ");
        query.append("        x.plant,                                                                                                              ");
        query.append("        (SELECT b.description FROM TAPLANT b WHERE b.plant = x.plant) plantDesc                                               ");
        query.append("       , x.full_desc													fullDesc	");
        query.append("FROM  TADOCCTG x                                                               	");
        query.append("WHERE 1 = 1        																");
        query.getAndQuery("docctg_id", docCtgId);

        DocCtgDetailDTO docCtgDetailDTO = 
        		(DocCtgDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new DocCtgDetailDTO()));
        
        return docCtgDetailDTO;
    }
    
    public int insertDetail(DocCtgDetailDTO docCtgDetailDTO) 
	{
		QueryBuffer query = new QueryBuffer();
        
		query.append("INSERT INTO TADOCCTG (                                    ");
		query.append("  comp_no,   docctg_id,   p_docctg_id,    plant,          ");
		query.append("  docctg_no, description, remark,  		ord_no,			");
		query.append("  is_use  												");
		query.append(")    VALUES (                                             ");
		query.append("  ?,         ?,          	NVL(?,0),    ?,              ");
		query.append("  ?,         ?,          	?,          	?,				");
		query.append("  ?                   									");
		query.append(")                                                         ");

        
        Object[] objects = new Object[] {
        		docCtgDetailDTO.getCompNo(),
        		docCtgDetailDTO.getDocctgId(),
        		docCtgDetailDTO.getPdocctgId(),
        		docCtgDetailDTO.getPlant(),
        		docCtgDetailDTO.getDocctgNo(),
        		docCtgDetailDTO.getDescription(),
        		docCtgDetailDTO.getRemark(),
        		docCtgDetailDTO.getOrdNo(),
        		docCtgDetailDTO.getIsUse()
        };
        return this.getJdbcTemplate().update(query.toString(), objects);
	}
    
    /**
     * detail 
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param docCtgDetailDTO
     * @return
     */
    public int[] updateDetail(final List <DocCtgDetailDTO> list, final User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue = 0;
    	
    	query.append("UPDATE TADOCCTG SET           	");
    	query.append("       p_docctg_id  = ?,         	");
    	query.append("       plant        = ?,         	");
    	query.append("       description  = ?,         	");
    	query.append("       docctg_no    = ?,         	");
    	query.append("       remark       = ?,         	");
    	query.append("       ord_no       = ?,         	");
    	query.append("       is_use       = ?,         	");
    	query.append("       full_desc    = ?          	");
    	query.append("WHERE  docctg_id    = ?           ");
    	query.append("  AND comp_no		  = ?			");

    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {
            
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
            	DocCtgDetailDTO docCtgDetailDTO = list.get(i);
                
                Object[] objects = new Object[] {
	    			docCtgDetailDTO.getPdocctgId(),
	    			docCtgDetailDTO.getPlant(),
	    			docCtgDetailDTO.getDescription(),
	    			docCtgDetailDTO.getDocctgNo(),
	    			docCtgDetailDTO.getRemark(),
	    			docCtgDetailDTO.getOrdNo(),
	    			docCtgDetailDTO.getIsUse(),
	    			docCtgDetailDTO.getFullDesc(),
	    			docCtgDetailDTO.getDocctgId(),
                    user.getCompNo()
		    	};
	
		        for(int j=0;j<objects.length;j++){
		            ps.setObject(j+1, objects[j]);
		        }
		    }
		    
		    @Override
		    public int getBatchSize()
		    {
		        return list.size();
		    }
		});
	}
}