package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the `libs` extension.
*/
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final ConverterLibraryAccessors laccForConverterLibraryAccessors = new ConverterLibraryAccessors(owner);
    private final CoreLibraryAccessors laccForCoreLibraryAccessors = new CoreLibraryAccessors(owner);
    private final EspressoLibraryAccessors laccForEspressoLibraryAccessors = new EspressoLibraryAccessors(owner);
    private final ExtLibraryAccessors laccForExtLibraryAccessors = new ExtLibraryAccessors(owner);
    private final HiltLibraryAccessors laccForHiltLibraryAccessors = new HiltLibraryAccessors(owner);
    private final KotlinxLibraryAccessors laccForKotlinxLibraryAccessors = new KotlinxLibraryAccessors(owner);
    private final LifecycleLibraryAccessors laccForLifecycleLibraryAccessors = new LifecycleLibraryAccessors(owner);
    private final LoggingLibraryAccessors laccForLoggingLibraryAccessors = new LoggingLibraryAccessors(owner);
    private final MpLibraryAccessors laccForMpLibraryAccessors = new MpLibraryAccessors(owner);
    private final NavigationLibraryAccessors laccForNavigationLibraryAccessors = new NavigationLibraryAccessors(owner);
    private final Retrofit2LibraryAccessors laccForRetrofit2LibraryAccessors = new Retrofit2LibraryAccessors(owner);
    private final RoomLibraryAccessors laccForRoomLibraryAccessors = new RoomLibraryAccessors(owner);
    private final SdpLibraryAccessors laccForSdpLibraryAccessors = new SdpLibraryAccessors(owner);
    private final SspLibraryAccessors laccForSspLibraryAccessors = new SspLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

        /**
         * Creates a dependency provider for appcompat (androidx.appcompat:appcompat)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getAppcompat() { return create("appcompat"); }

        /**
         * Creates a dependency provider for constraintlayout (androidx.constraintlayout:constraintlayout)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getConstraintlayout() { return create("constraintlayout"); }

        /**
         * Creates a dependency provider for junit (junit:junit)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJunit() { return create("junit"); }

        /**
         * Creates a dependency provider for material (com.google.android.material:material)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getMaterial() { return create("material"); }

        /**
         * Creates a dependency provider for mockk (io.mockk:mockk)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getMockk() { return create("mockk"); }

        /**
         * Creates a dependency provider for retrofit (com.squareup.retrofit2:retrofit)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getRetrofit() { return create("retrofit"); }

    /**
     * Returns the group of libraries at converter
     */
    public ConverterLibraryAccessors getConverter() { return laccForConverterLibraryAccessors; }

    /**
     * Returns the group of libraries at core
     */
    public CoreLibraryAccessors getCore() { return laccForCoreLibraryAccessors; }

    /**
     * Returns the group of libraries at espresso
     */
    public EspressoLibraryAccessors getEspresso() { return laccForEspressoLibraryAccessors; }

    /**
     * Returns the group of libraries at ext
     */
    public ExtLibraryAccessors getExt() { return laccForExtLibraryAccessors; }

    /**
     * Returns the group of libraries at hilt
     */
    public HiltLibraryAccessors getHilt() { return laccForHiltLibraryAccessors; }

    /**
     * Returns the group of libraries at kotlinx
     */
    public KotlinxLibraryAccessors getKotlinx() { return laccForKotlinxLibraryAccessors; }

    /**
     * Returns the group of libraries at lifecycle
     */
    public LifecycleLibraryAccessors getLifecycle() { return laccForLifecycleLibraryAccessors; }

    /**
     * Returns the group of libraries at logging
     */
    public LoggingLibraryAccessors getLogging() { return laccForLoggingLibraryAccessors; }

    /**
     * Returns the group of libraries at mp
     */
    public MpLibraryAccessors getMp() { return laccForMpLibraryAccessors; }

    /**
     * Returns the group of libraries at navigation
     */
    public NavigationLibraryAccessors getNavigation() { return laccForNavigationLibraryAccessors; }

    /**
     * Returns the group of libraries at retrofit2
     */
    public Retrofit2LibraryAccessors getRetrofit2() { return laccForRetrofit2LibraryAccessors; }

    /**
     * Returns the group of libraries at room
     */
    public RoomLibraryAccessors getRoom() { return laccForRoomLibraryAccessors; }

    /**
     * Returns the group of libraries at sdp
     */
    public SdpLibraryAccessors getSdp() { return laccForSdpLibraryAccessors; }

    /**
     * Returns the group of libraries at ssp
     */
    public SspLibraryAccessors getSsp() { return laccForSspLibraryAccessors; }

    /**
     * Returns the group of versions at versions
     */
    public VersionAccessors getVersions() { return vaccForVersionAccessors; }

    /**
     * Returns the group of bundles at bundles
     */
    public BundleAccessors getBundles() { return baccForBundleAccessors; }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() { return paccForPluginAccessors; }

    public static class ConverterLibraryAccessors extends SubDependencyFactory {

        public ConverterLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for gson (com.squareup.retrofit2:converter-gson)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getGson() { return create("converter.gson"); }

            /**
             * Creates a dependency provider for moshi (com.squareup.retrofit2:converter-moshi)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getMoshi() { return create("converter.moshi"); }

    }

    public static class CoreLibraryAccessors extends SubDependencyFactory {

        public CoreLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.core:core-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("core.ktx"); }

            /**
             * Creates a dependency provider for testing (androidx.arch.core:core-testing)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTesting() { return create("core.testing"); }

    }

    public static class EspressoLibraryAccessors extends SubDependencyFactory {

        public EspressoLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (androidx.test.espresso:espresso-core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() { return create("espresso.core"); }

    }

    public static class ExtLibraryAccessors extends SubDependencyFactory {

        public ExtLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for junit (androidx.test.ext:junit)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJunit() { return create("ext.junit"); }

    }

    public static class HiltLibraryAccessors extends SubDependencyFactory {
        private final HiltAndroidLibraryAccessors laccForHiltAndroidLibraryAccessors = new HiltAndroidLibraryAccessors(owner);

        public HiltLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at hilt.android
         */
        public HiltAndroidLibraryAccessors getAndroid() { return laccForHiltAndroidLibraryAccessors; }

    }

    public static class HiltAndroidLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public HiltAndroidLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for android (com.google.dagger:hilt-android)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("hilt.android"); }

            /**
             * Creates a dependency provider for compiler (com.google.dagger:hilt-android-compiler)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCompiler() { return create("hilt.android.compiler"); }

    }

    public static class KotlinxLibraryAccessors extends SubDependencyFactory {
        private final KotlinxCoroutinesLibraryAccessors laccForKotlinxCoroutinesLibraryAccessors = new KotlinxCoroutinesLibraryAccessors(owner);

        public KotlinxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at kotlinx.coroutines
         */
        public KotlinxCoroutinesLibraryAccessors getCoroutines() { return laccForKotlinxCoroutinesLibraryAccessors; }

    }

    public static class KotlinxCoroutinesLibraryAccessors extends SubDependencyFactory {

        public KotlinxCoroutinesLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for android (org.jetbrains.kotlinx:kotlinx-coroutines-android)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAndroid() { return create("kotlinx.coroutines.android"); }

            /**
             * Creates a dependency provider for core (org.jetbrains.kotlinx:kotlinx-coroutines-core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() { return create("kotlinx.coroutines.core"); }

            /**
             * Creates a dependency provider for test (org.jetbrains.kotlinx:kotlinx-coroutines-test)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTest() { return create("kotlinx.coroutines.test"); }

    }

    public static class LifecycleLibraryAccessors extends SubDependencyFactory {
        private final LifecycleCommonLibraryAccessors laccForLifecycleCommonLibraryAccessors = new LifecycleCommonLibraryAccessors(owner);
        private final LifecycleLivedataLibraryAccessors laccForLifecycleLivedataLibraryAccessors = new LifecycleLivedataLibraryAccessors(owner);
        private final LifecycleViewmodelLibraryAccessors laccForLifecycleViewmodelLibraryAccessors = new LifecycleViewmodelLibraryAccessors(owner);

        public LifecycleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for extensions (androidx.lifecycle:lifecycle-extensions)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getExtensions() { return create("lifecycle.extensions"); }

        /**
         * Returns the group of libraries at lifecycle.common
         */
        public LifecycleCommonLibraryAccessors getCommon() { return laccForLifecycleCommonLibraryAccessors; }

        /**
         * Returns the group of libraries at lifecycle.livedata
         */
        public LifecycleLivedataLibraryAccessors getLivedata() { return laccForLifecycleLivedataLibraryAccessors; }

        /**
         * Returns the group of libraries at lifecycle.viewmodel
         */
        public LifecycleViewmodelLibraryAccessors getViewmodel() { return laccForLifecycleViewmodelLibraryAccessors; }

    }

    public static class LifecycleCommonLibraryAccessors extends SubDependencyFactory {

        public LifecycleCommonLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for java8 (androidx.lifecycle:lifecycle-common-java8)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJava8() { return create("lifecycle.common.java8"); }

    }

    public static class LifecycleLivedataLibraryAccessors extends SubDependencyFactory {

        public LifecycleLivedataLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.lifecycle:lifecycle-livedata-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("lifecycle.livedata.ktx"); }

    }

    public static class LifecycleViewmodelLibraryAccessors extends SubDependencyFactory {

        public LifecycleViewmodelLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.lifecycle:lifecycle-viewmodel-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("lifecycle.viewmodel.ktx"); }

    }

    public static class LoggingLibraryAccessors extends SubDependencyFactory {

        public LoggingLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for interceptor (com.squareup.okhttp3:logging-interceptor)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getInterceptor() { return create("logging.interceptor"); }

    }

    public static class MpLibraryAccessors extends SubDependencyFactory {
        private final MpAndroidLibraryAccessors laccForMpAndroidLibraryAccessors = new MpAndroidLibraryAccessors(owner);

        public MpLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at mp.android
         */
        public MpAndroidLibraryAccessors getAndroid() { return laccForMpAndroidLibraryAccessors; }

    }

    public static class MpAndroidLibraryAccessors extends SubDependencyFactory {

        public MpAndroidLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for chart (com.github.PhilJay:MPAndroidChart)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getChart() { return create("mp.android.chart"); }

    }

    public static class NavigationLibraryAccessors extends SubDependencyFactory {
        private final NavigationFragmentLibraryAccessors laccForNavigationFragmentLibraryAccessors = new NavigationFragmentLibraryAccessors(owner);
        private final NavigationUiLibraryAccessors laccForNavigationUiLibraryAccessors = new NavigationUiLibraryAccessors(owner);

        public NavigationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at navigation.fragment
         */
        public NavigationFragmentLibraryAccessors getFragment() { return laccForNavigationFragmentLibraryAccessors; }

        /**
         * Returns the group of libraries at navigation.ui
         */
        public NavigationUiLibraryAccessors getUi() { return laccForNavigationUiLibraryAccessors; }

    }

    public static class NavigationFragmentLibraryAccessors extends SubDependencyFactory {

        public NavigationFragmentLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.navigation:navigation-fragment-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("navigation.fragment.ktx"); }

    }

    public static class NavigationUiLibraryAccessors extends SubDependencyFactory {

        public NavigationUiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.navigation:navigation-ui-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("navigation.ui.ktx"); }

    }

    public static class Retrofit2LibraryAccessors extends SubDependencyFactory {
        private final Retrofit2KotlinLibraryAccessors laccForRetrofit2KotlinLibraryAccessors = new Retrofit2KotlinLibraryAccessors(owner);

        public Retrofit2LibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at retrofit2.kotlin
         */
        public Retrofit2KotlinLibraryAccessors getKotlin() { return laccForRetrofit2KotlinLibraryAccessors; }

    }

    public static class Retrofit2KotlinLibraryAccessors extends SubDependencyFactory {
        private final Retrofit2KotlinCoroutinesLibraryAccessors laccForRetrofit2KotlinCoroutinesLibraryAccessors = new Retrofit2KotlinCoroutinesLibraryAccessors(owner);

        public Retrofit2KotlinLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at retrofit2.kotlin.coroutines
         */
        public Retrofit2KotlinCoroutinesLibraryAccessors getCoroutines() { return laccForRetrofit2KotlinCoroutinesLibraryAccessors; }

    }

    public static class Retrofit2KotlinCoroutinesLibraryAccessors extends SubDependencyFactory {

        public Retrofit2KotlinCoroutinesLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for adapter (com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAdapter() { return create("retrofit2.kotlin.coroutines.adapter"); }

    }

    public static class RoomLibraryAccessors extends SubDependencyFactory {

        public RoomLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for compiler (androidx.room:room-compiler)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCompiler() { return create("room.compiler"); }

            /**
             * Creates a dependency provider for ktx (androidx.room:room-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("room.ktx"); }

    }

    public static class SdpLibraryAccessors extends SubDependencyFactory {

        public SdpLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for android (com.intuit.sdp:sdp-android)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAndroid() { return create("sdp.android"); }

    }

    public static class SspLibraryAccessors extends SubDependencyFactory {

        public SspLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for android (com.intuit.ssp:ssp-android)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAndroid() { return create("ssp.android"); }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final AndroidxVersionAccessors vaccForAndroidxVersionAccessors = new AndroidxVersionAccessors(providers, config);
        private final CoreVersionAccessors vaccForCoreVersionAccessors = new CoreVersionAccessors(providers, config);
        private final EspressoVersionAccessors vaccForEspressoVersionAccessors = new EspressoVersionAccessors(providers, config);
        private final HiltVersionAccessors vaccForHiltVersionAccessors = new HiltVersionAccessors(providers, config);
        private final KotlinxVersionAccessors vaccForKotlinxVersionAccessors = new KotlinxVersionAccessors(providers, config);
        private final LifecycleVersionAccessors vaccForLifecycleVersionAccessors = new LifecycleVersionAccessors(providers, config);
        private final LoggingVersionAccessors vaccForLoggingVersionAccessors = new LoggingVersionAccessors(providers, config);
        private final MpVersionAccessors vaccForMpVersionAccessors = new MpVersionAccessors(providers, config);
        private final NavigationVersionAccessors vaccForNavigationVersionAccessors = new NavigationVersionAccessors(providers, config);
        private final Retrofit2VersionAccessors vaccForRetrofit2VersionAccessors = new Retrofit2VersionAccessors(providers, config);
        private final RoomVersionAccessors vaccForRoomVersionAccessors = new RoomVersionAccessors(providers, config);
        private final SdpVersionAccessors vaccForSdpVersionAccessors = new SdpVersionAccessors(providers, config);
        private final SspVersionAccessors vaccForSspVersionAccessors = new SspVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: agp (8.1.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAgp() { return getVersion("agp"); }

            /**
             * Returns the version associated to this alias: appcompat (1.6.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAppcompat() { return getVersion("appcompat"); }

            /**
             * Returns the version associated to this alias: constraintlayout (2.1.4)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getConstraintlayout() { return getVersion("constraintlayout"); }

            /**
             * Returns the version associated to this alias: junit (4.13.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJunit() { return getVersion("junit"); }

            /**
             * Returns the version associated to this alias: kotlin (1.8.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKotlin() { return getVersion("kotlin"); }

            /**
             * Returns the version associated to this alias: material (1.10.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getMaterial() { return getVersion("material"); }

            /**
             * Returns the version associated to this alias: mockk (1.13.7)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getMockk() { return getVersion("mockk"); }

            /**
             * Returns the version associated to this alias: retrofit (2.9.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getRetrofit() { return getVersion("retrofit"); }

        /**
         * Returns the group of versions at versions.androidx
         */
        public AndroidxVersionAccessors getAndroidx() { return vaccForAndroidxVersionAccessors; }

        /**
         * Returns the group of versions at versions.core
         */
        public CoreVersionAccessors getCore() { return vaccForCoreVersionAccessors; }

        /**
         * Returns the group of versions at versions.espresso
         */
        public EspressoVersionAccessors getEspresso() { return vaccForEspressoVersionAccessors; }

        /**
         * Returns the group of versions at versions.hilt
         */
        public HiltVersionAccessors getHilt() { return vaccForHiltVersionAccessors; }

        /**
         * Returns the group of versions at versions.kotlinx
         */
        public KotlinxVersionAccessors getKotlinx() { return vaccForKotlinxVersionAccessors; }

        /**
         * Returns the group of versions at versions.lifecycle
         */
        public LifecycleVersionAccessors getLifecycle() { return vaccForLifecycleVersionAccessors; }

        /**
         * Returns the group of versions at versions.logging
         */
        public LoggingVersionAccessors getLogging() { return vaccForLoggingVersionAccessors; }

        /**
         * Returns the group of versions at versions.mp
         */
        public MpVersionAccessors getMp() { return vaccForMpVersionAccessors; }

        /**
         * Returns the group of versions at versions.navigation
         */
        public NavigationVersionAccessors getNavigation() { return vaccForNavigationVersionAccessors; }

        /**
         * Returns the group of versions at versions.retrofit2
         */
        public Retrofit2VersionAccessors getRetrofit2() { return vaccForRetrofit2VersionAccessors; }

        /**
         * Returns the group of versions at versions.room
         */
        public RoomVersionAccessors getRoom() { return vaccForRoomVersionAccessors; }

        /**
         * Returns the group of versions at versions.sdp
         */
        public SdpVersionAccessors getSdp() { return vaccForSdpVersionAccessors; }

        /**
         * Returns the group of versions at versions.ssp
         */
        public SspVersionAccessors getSsp() { return vaccForSspVersionAccessors; }

    }

    public static class AndroidxVersionAccessors extends VersionFactory  {

        public AndroidxVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: androidx.junit (1.1.5)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJunit() { return getVersion("androidx.junit"); }

    }

    public static class CoreVersionAccessors extends VersionFactory  {

        public CoreVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: core.ktx (1.12.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKtx() { return getVersion("core.ktx"); }

            /**
             * Returns the version associated to this alias: core.testing (2.2.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getTesting() { return getVersion("core.testing"); }

    }

    public static class EspressoVersionAccessors extends VersionFactory  {

        public EspressoVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: espresso.core (3.5.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCore() { return getVersion("espresso.core"); }

    }

    public static class HiltVersionAccessors extends VersionFactory  {

        public HiltVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: hilt.android (2.46.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroid() { return getVersion("hilt.android"); }

    }

    public static class KotlinxVersionAccessors extends VersionFactory  {

        private final KotlinxCoroutinesVersionAccessors vaccForKotlinxCoroutinesVersionAccessors = new KotlinxCoroutinesVersionAccessors(providers, config);
        public KotlinxVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.kotlinx.coroutines
         */
        public KotlinxCoroutinesVersionAccessors getCoroutines() { return vaccForKotlinxCoroutinesVersionAccessors; }

    }

    public static class KotlinxCoroutinesVersionAccessors extends VersionFactory  {

        public KotlinxCoroutinesVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: kotlinx.coroutines.core (1.7.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCore() { return getVersion("kotlinx.coroutines.core"); }

    }

    public static class LifecycleVersionAccessors extends VersionFactory  {

        private final LifecycleViewmodelVersionAccessors vaccForLifecycleViewmodelVersionAccessors = new LifecycleViewmodelVersionAccessors(providers, config);
        public LifecycleVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: lifecycle.extensions (2.2.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getExtensions() { return getVersion("lifecycle.extensions"); }

        /**
         * Returns the group of versions at versions.lifecycle.viewmodel
         */
        public LifecycleViewmodelVersionAccessors getViewmodel() { return vaccForLifecycleViewmodelVersionAccessors; }

    }

    public static class LifecycleViewmodelVersionAccessors extends VersionFactory  {

        public LifecycleViewmodelVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: lifecycle.viewmodel.ktx (2.6.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKtx() { return getVersion("lifecycle.viewmodel.ktx"); }

    }

    public static class LoggingVersionAccessors extends VersionFactory  {

        public LoggingVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: logging.interceptor (4.10.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getInterceptor() { return getVersion("logging.interceptor"); }

    }

    public static class MpVersionAccessors extends VersionFactory  {

        private final MpAndroidVersionAccessors vaccForMpAndroidVersionAccessors = new MpAndroidVersionAccessors(providers, config);
        public MpVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.mp.android
         */
        public MpAndroidVersionAccessors getAndroid() { return vaccForMpAndroidVersionAccessors; }

    }

    public static class MpAndroidVersionAccessors extends VersionFactory  {

        public MpAndroidVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: mp.android.chart (v3.1.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getChart() { return getVersion("mp.android.chart"); }

    }

    public static class NavigationVersionAccessors extends VersionFactory  {

        private final NavigationFragmentVersionAccessors vaccForNavigationFragmentVersionAccessors = new NavigationFragmentVersionAccessors(providers, config);
        public NavigationVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.navigation.fragment
         */
        public NavigationFragmentVersionAccessors getFragment() { return vaccForNavigationFragmentVersionAccessors; }

    }

    public static class NavigationFragmentVersionAccessors extends VersionFactory  {

        public NavigationFragmentVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: navigation.fragment.ktx (2.7.5)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKtx() { return getVersion("navigation.fragment.ktx"); }

    }

    public static class Retrofit2VersionAccessors extends VersionFactory  {

        private final Retrofit2KotlinVersionAccessors vaccForRetrofit2KotlinVersionAccessors = new Retrofit2KotlinVersionAccessors(providers, config);
        public Retrofit2VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.retrofit2.kotlin
         */
        public Retrofit2KotlinVersionAccessors getKotlin() { return vaccForRetrofit2KotlinVersionAccessors; }

    }

    public static class Retrofit2KotlinVersionAccessors extends VersionFactory  {

        private final Retrofit2KotlinCoroutinesVersionAccessors vaccForRetrofit2KotlinCoroutinesVersionAccessors = new Retrofit2KotlinCoroutinesVersionAccessors(providers, config);
        public Retrofit2KotlinVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.retrofit2.kotlin.coroutines
         */
        public Retrofit2KotlinCoroutinesVersionAccessors getCoroutines() { return vaccForRetrofit2KotlinCoroutinesVersionAccessors; }

    }

    public static class Retrofit2KotlinCoroutinesVersionAccessors extends VersionFactory  {

        public Retrofit2KotlinCoroutinesVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: retrofit2.kotlin.coroutines.adapter (0.9.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAdapter() { return getVersion("retrofit2.kotlin.coroutines.adapter"); }

    }

    public static class RoomVersionAccessors extends VersionFactory  {

        public RoomVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: room.ktx (2.6.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKtx() { return getVersion("room.ktx"); }

    }

    public static class SdpVersionAccessors extends VersionFactory  {

        public SdpVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: sdp.android (1.1.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroid() { return getVersion("sdp.android"); }

    }

    public static class SspVersionAccessors extends VersionFactory  {

        public SspVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: ssp.android (1.1.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroid() { return getVersion("ssp.android"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for androidApplication to the plugin id 'com.android.application'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getAndroidApplication() { return createPlugin("androidApplication"); }

            /**
             * Creates a plugin provider for kotlinAndroid to the plugin id 'org.jetbrains.kotlin.android'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getKotlinAndroid() { return createPlugin("kotlinAndroid"); }

    }

}
