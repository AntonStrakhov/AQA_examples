package qa.guru.owner;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import qa.guru.owner.config.MobileConfig;

import static org.assertj.core.api.Assertions.assertThat;

public class MobileTest {

    @Test
    public void testMobile() {
        MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());

        assertThat(config.getDeviceName()).isEqualTo("iPhone 13 Pro Max Super Man");
        assertThat(config.getPlatformName()).isEqualTo("IOS");
        assertThat(config.getPlatformVersion()).isEqualTo("13");
    }

    @Test
    public void testMobileWithSystemOverride() {
        System.setProperty("platform.version", "12");

        MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());

        assertThat(config.getPlatformVersion()).isEqualTo("12");
    }

    @Test
    public void testMobileWithAndroid() {
        System.setProperty("device", "google-pixel");

        MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());

        assertThat(config.getDeviceName()).isEqualTo("Google Pixel");
        assertThat(config.getPlatformName()).isEqualTo("Android");
        assertThat(config.getPlatformVersion()).isEqualTo("24");
    }

    @Test
    public void testMobileWithIPhone() {
        System.setProperty("device", "iphone-13");

        MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());

        assertThat(config.getDeviceName()).isEqualTo("iPhone 13 Pro Max Super Man");
        assertThat(config.getPlatformName()).isEqualTo("IOS");
        assertThat(config.getPlatformVersion()).isEqualTo("13");
    }
}
