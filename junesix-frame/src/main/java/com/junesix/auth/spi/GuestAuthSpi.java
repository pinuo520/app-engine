package com.junesix.auth.spi;


import com.junesix.auth.model.AuthExcepFactor;
import com.junesix.auth.model.AuthException;
import com.junesix.auth.model.AuthRequest;
import com.junesix.common.context.ClientVersion;
import org.springframework.stereotype.Component;

/**
 * 验证Header基本信息，暂时只验证版本号不为空
 *
 * @author sofn 10/03/15.
 */
@Component("guestSpi")
public class GuestAuthSpi extends AbstractAuthSpi {

    public static final String SPI_NAME = "GUESS_AUTH";

    @Override
    protected boolean checkCanAuth(AuthRequest request) {
        //此spi需手动指定
        return false;
    }

    @Override
    public long auth(AuthRequest request) throws AuthException {
        ClientVersion version = ClientVersion.valueOf(request.getHeader(ClientVersion.VERSION_HEADER));
        if (version.sdkVersion.equals(ClientVersion.Version.NULL)
                || version.clientVersion.equals(ClientVersion.Version.NULL)
                || version.udid.equals(ClientVersion.DEFAULT_UNKNOW)) {
            throw new AuthException(AuthExcepFactor.E_ILLEGAL_GUEST);
        }
        return 0;
    }

    @Override
    public String getName() {
        return SPI_NAME;
    }
}
