package util;
import java.io.InputStream;
import java.net.URL;

import lombok.NonNull;

public class ResourcesLoader {
    
    private ResourcesLoader() {}

	public static URL loadURL(@NonNull String path) {
		URL resourceUrl = ResourcesLoader.class.getResource(path);
		if (resourceUrl == null) {
			throw new ResourceNotFoundException("Resource not found: " + path);
		}
		return resourceUrl;
	}

	public static String load(String path) {
		return loadURL(path).getPath();
	}

	public static InputStream loadStream(@NonNull String name) {
		InputStream stream = ResourcesLoader.class.getResourceAsStream(name);
		if (stream == null) {
			throw new ResourceNotFoundException("Resource not found: " + name);
		}
		return stream;
	}
}
