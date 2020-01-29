package intf.dream.android.offline.madoclib.dao;

import common.file.MwareFile;
import common.spring.BaseJdbcDaoSupportIntf;
import intf.dream.android.offline.madoclib.dto.AnIfDocLibCommonDTO;

/**
 * 첨부파일 - 목록 dao
 * @author  kim21017
 * @version $Id: AnIfDocLibListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 */
public interface AnIfDocLibListDAO extends BaseJdbcDaoSupportIntf
{
    public int insertFileInfo(MwareFile mwareFile, AnIfDocLibCommonDTO anIfDocLibCommonDTO);
}