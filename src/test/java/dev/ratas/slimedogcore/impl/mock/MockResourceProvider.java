package dev.ratas.slimedogcore.impl.mock;

import java.io.IOException;
import java.io.InputStream;

import dev.ratas.slimedogcore.api.wrappers.SDCResourceProvider;

public class MockResourceProvider implements SDCResourceProvider {

    @Override
    public InputStream getResource(String filename) {
        return new EmptyInputStream();
    }

    @Override
    public void saveResource(String resourcePath, boolean replace) {
        System.err.println("Mock Resource provider should not save resources");
    }

    public static class EmptyInputStream extends InputStream {

        @Override
        public int read() throws IOException {
            return -1;
        }

    }

}
