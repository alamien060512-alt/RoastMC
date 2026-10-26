package com.roastmc.launcher.neoforge.feature;

import com.roastmc.launcher.neoforge.config.RoastMCConfigUtil;
import com.mohistmc.tools.IOUtil;
import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.List;
import lombok.Getter;

@Getter
public class RoastMCProxySelector extends ProxySelector {

    private final ProxySelector defaultSelector;

    public RoastMCProxySelector(ProxySelector defaultSelector) {
        this.defaultSelector = defaultSelector;
    }

    @Override
    public List<Proxy> select(URI uri) {
        if (RoastMCConfigUtil.NETWORKMANAGER_DEBUG()) {
            System.out.println(uri.toString());
        }

        String uriString = uri.toString();
        String defaultMsg = "[NetworkManager] Network protection and blocked by network rules!";
        boolean intercept = false;
        if (!RoastMCConfigUtil.NETWORKMANAGER_INTERCEPT().isEmpty()) {
            for (String config_uri : RoastMCConfigUtil.NETWORKMANAGER_INTERCEPT()) {
                if (uriString.contains(config_uri)) {
                    intercept = true;
                    break;
                }
            }
        }
        if (intercept) {
            try {
                IOUtil.throwException(new IOException(defaultMsg));
            } catch (Throwable ignored) {
            }
        } else {
            return this.defaultSelector.select(uri);
        }
        return null;
    }

    @Override
    public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
        this.defaultSelector.connectFailed(uri, sa, ioe);
    }

}
