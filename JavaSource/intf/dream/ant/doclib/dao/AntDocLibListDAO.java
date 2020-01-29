package intf.dream.ant.doclib.dao;

import common.file.MwareFile;
import common.spring.BaseJdbcDaoSupportIntf;
import intf.dream.ant.doclib.dto.AntDocLibCommonDTO;

/**
 * 첨부파일 - 목록 dao
 * @author  kim21017
 * @version $Id: AntDocLibListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 */
public interface AntDocLibListDAO extends BaseJdbcDaoSupportIntf
{
    public int insertFileInfo(MwareFile mwareFile, AntDocLibCommonDTO antDocLibCommonDTO);
}