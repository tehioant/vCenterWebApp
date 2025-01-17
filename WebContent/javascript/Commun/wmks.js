(function() {
    function a(a) {
        var b = a.length,
            c = new Array(Math.ceil(b / 8)),
            d, e;
        for (d = 0, e = 0; d < b; d += 8, e++) c[e] = String.fromCharCode(a[d], a[d + 1], a[d + 2], a[d + 3], a[d + 4], a[d + 5], a[d + 6], a[d + 7]);
        return c.join("").substr(0, b)
    }

    function b(a, b) {
        var c = a.length,
            d = b ? new Uint8Array(c) : new Array(c),
            e;
        for (e = 0; e + 7 < c; e += 8) d[e] = a.charCodeAt(e), d[e + 1] = a.charCodeAt(e + 1), d[e + 2] = a.charCodeAt(e + 2), d[e + 3] = a.charCodeAt(e + 3), d[e + 4] = a.charCodeAt(e + 4), d[e + 5] = a.charCodeAt(e + 5), d[e + 6] = a.charCodeAt(e + 6), d[e + 7] = a.charCodeAt(e + 7);
        for (; e < c; e++) d[e] = a.charCodeAt(e);
        return d
    }

    function d(a) {
        "use strict";
        var b = a,
            d = [],
            e = function() {
                return d.length > 0 ? d.shift() : new Image
            },
            f = function(a) {
                delete a[0], a[0] = null, a = null
            },
            g = function(a) {
                a.onload = a.onerror = null;
                var e = [71, 73, 70, 56, 57, 97, 1, 0, 1, 0, 0, 255, 0, 44, 0, 0, 0, 0, 1, 0, 1, 0, 0, 2, 0, 59];
                e.push32($.now()), a.src = "data:image/gif;base64," + c.encodeFromArray(e), d.length <= b ? d.push(a) : f([a])
            };
        this.getImage = function() {
            return e()
        }, this.releaseImage = function(a) {
            if (!a) return;
            g(a)
        }
    }

    function e() {
        this._mediaSource = null, this._sourceBuffer = null, this._tempQueue = [], this._mediaPlayer = null, this._isError = !1, this._isErrorDoneCalled = !1, this._sendRequest = 0, this._doneRequest = 0, this._decodeDoneCb = null, this._decodeErrorCb = null, e.instanceNumber++, this._name = "MP4Decoder" + e.instanceNumber
    }

    function f(d) {
        function g(a) {
            WMKS.LOGGER.trace("uint8utf8: replacing functions"), a._originalFunctions = a._originalFunctions || {};
            for (var b in f) f.hasOwnProperty(b) && (a._originalFunctions[b] || (a._originalFunctions[b] = a[b]), a[b] = f[b])
        }

        function h(a) {
            WMKS.LOGGER.trace("restoreFunctions");
            if (!a._originalFunctions) return;
            for (var b in a._originalFunctions) a._originalFunctions.hasOwnProperty(b) && (a[b] = a._originalFunctions[b])
        }
        "use strict", WMKS.LOGGER.debug("adding uint8utf8 support");
        var e = d;
        e.hasOwnProperty("_legacyReceiveQueue") || (e._legacyReceiveQueue = "", e._legacyReceiveQueueIndex = ""), e.useLegacy = !1;
        var f = {};
        f._receiveQueueBytesUnread = function() {
            return this._legacyReceiveQueue.length - this._legacyReceiveQueueIndex
        }, f._receiveQueueConsumeBytes = function(a) {
            this._legacyReceiveQueueIndex += a
        }, f._receiveQueueReset = function() {
            this._legacyReceiveQueue = "", this._legacyReceiveQueueIndex = 0
        }, f._readString = function(a) {
            var b = this._legacyReceiveQueue.slice(this._legacyReceiveQueueIndex, this._legacyReceiveQueueIndex + a);
            return this._legacyReceiveQueueIndex += a, b
        }, f._readStringUTF8 = function(a) {
            var b, c, d, e, f = [],
                g = this._legacyReceiveQueueIndex;
            while (g < this._legacyReceiveQueueIndex + a) b = this._legacyReceiveQueue.charCodeAt(g), b < 128 ? (f.push(b), g++) : b < 224 ? (c = this._legacyReceiveQueue.charCodeAt(g + 1) & 63, f.push((b & 31) << 6 | c), g += 2) : b < 240 ? (c = this._legacyReceiveQueue.charCodeAt(g + 1) & 63, d = this._legacyReceiveQueue.charCodeAt(g + 2) & 63, f.push((b & 15) << 12 | c << 6 | d), g += 3) : (c = this._legacyReceiveQueue.charCodeAt(g + 1) & 63, d = this._legacyReceiveQueue.charCodeAt(g + 2) & 63, e = this._legacyReceiveQueue.charCodeAt(g + 3) & 63, f.push((b & 7) << 18 | c << 12 | d << 6 | e), g += 4);
            return this._legacyReceiveQueueIndex += a, String.fromCharCode.apply(String, f)
        }, f._readByte = function() {
            var a = this._legacyReceiveQueue.charCodeAt(this._legacyReceiveQueueIndex);
            return this._legacyReceiveQueueIndex += 1, a
        }, f._readBytes = function(a) {
            var b, c;
            b = new Array(a);
            for (c = 0; c < a; c++) b[c] = this._legacyReceiveQueue.charCodeAt(c + this._legacyReceiveQueueIndex);
            return this._legacyReceiveQueueIndex += a, b
        }, f._sendString = function(a) {
            if (!this._websocket) return;
            this._websocket.send(a)
        }, f._sendBytes = function(b) {
            this._sendString(a(b))
        }, f._sendClientEncodingsMsg = function() {
            var a, b = [this.encTightPNG, this.encDesktopSize, this.encVMWDefineCursor, this.encVMWCursorState, this.encVMWCursorPosition, this.encVMWTypematicInfo, this.encVMWLEDState, this.encVMWServerPush2, this.encVMWServerCaps, this.encTightJpegQuality10, this.encVMWFrameStamp, this.encUpdateCache];
            this.options.mediaPlayer && b.unshift(this.encH264MP4), this._canvas[1] && (b = [this.encOffscreenCopyRect].concat(b)), b = [this.encCopyRect].concat(b);
            var c = [];
            c.push8(this.msgClientEncodings), c.push8(0), c.push16(b.length);
            for (a = 0; a < b.length; a += 1) c.push32(b[a]);
            this._sendBytes(c)
        }, f._readTightData = function(a) {
            var d = a.subEncoding === this.subEncPNG ? "image/png" : "image/jpeg",
                e = this._readString(this.nextBytes),
                f = window.URL || window.webkitURL,
                g = this;
            this._useImageBitmaps ? (e = b(e, !0), createImageBitmap(new Blob([e], {
                type: d
            })).then(function(b) {
                a.image = b, g.onDecodeComplete()
            })) : (a.image = this._imageManager.getImage(), a.image.width = a.width, a.image.height = a.height, a.image.destX = a.x, a.image.destY = a.y, f ? (e = b(e, !0), a.image.onload = this.onDecodeObjectURLComplete, a.image.src = f.createObjectURL(new Blob([e], {
                type: d
            }))) : (e = c.encodeFromString(e), a.image.onload = this.onDecodeComplete, a.image.src = "data:" + d + ";base64," + e)), this._nextRect()
        }, f._peekFirstMessage = function() {
            this.usedVNCHandshake = this._receiveQueueBytesUnread() == 12, this.usedVNCHandshake ? this._setReadCB(12, this._handleProtocolVersionMsg) : this._setReadCB(24, this._handleServerInitializedMsg)
        }, e.wsOpen = function(a) {
            e._state = e.VNC_ACTIVE_STATE;
            if (this.protocol !== "uint8utf8" && this.protocol !== "binary" && this.protocol !== "vmware-vvc") return e.fail("no agreement on protocol");
            this.protocol === "vmware-vvc" && (e._setupVVC(), WMKS.LOGGER.log("WebSocket is using VMware Virtual Channels"), this.protocol = "binary"), this.protocol === "binary" && (this.binaryType = "arraybuffer", WMKS.LOGGER.log("WebSocket HAS binary support")), e.useLegacy = this.protocol === "uint8utf8", e.useLegacy ? g(e) : h(e), e.options.onConnecting(e.vvc, e.vvcSession), WMKS.LOGGER.log("WebSocket created protocol: " + this.protocol)
        };
        var i = e.wsMessage;
        e.wsMessage = function(b) {
            if (!e.useLegacy) return i.apply(this, arguments);
            if (e._legacyReceiveQueueIndex > e._legacyReceiveQueue.length) return e.fail("overflow receiveQueue");
            e._legacyReceiveQueueIndex === e._legacyReceiveQueue.length && (e._legacyReceiveQueue = "", e._legacyReceiveQueueIndex = 0);
            if (typeof b.data != "string") {
                var c = new Uint8Array(b.data);
                e._legacyReceiveQueue = e._legacyReceiveQueue.concat(a(c))
            } else e._legacyReceiveQueue = e._legacyReceiveQueue.concat(b.data);
            e._processMessages()
        }, e.protocolList.indexOf("uint8utf8") === -1 && e.protocolList.push("uint8utf8"), f._receiveQueueReset.call(e)
    }
    var c = {
        decodeToArray: function(a, c) {
            return b(window.atob(a), c)
        },
        decodeToString: function(a) {
            return window.atob(a)
        },
        encodeFromArray: function(b) {
            return window.btoa(a(b))
        },
        encodeFromString: function(a) {
            return window.btoa(a)
        }
    };
    WMKS = {}, WMKS.LOGGER = new function() {
            "use strict",
            this.LEVEL = {
                TRACE: 0,
                DEBUG: 1,
                INFO: 2,
                WARN: 3,
                ERROR: 4
            };
            var a = this.LEVEL.INFO,
                b = [" [Trace] ", " [Debug] ", " [Info ] ", " [Warn ] ", " [Error] "];this.trace = function(a) {
                this.log(a, this.LEVEL.TRACE)
            },
            this.debug = function(a) {
                this.log(a, this.LEVEL.DEBUG)
            },
            this.info = function(a) {
                this.log(a, this.LEVEL.INFO)
            },
            this.warn = function(a) {
                this.log(a, this.LEVEL.WARN)
            },
            this.error = function(a) {
                this.log(a, this.LEVEL.ERROR)
            },
            this.log = typeof console == "undefined" || typeof console.log == "undefined" ? $.noop : function(c, d) {
                d = d === undefined ? this.LEVEL.INFO : d, d >= a && c && console.log((WMKS.BROWSER.isIE() ? (new Date).toUTCString() : (new Date).toISOString()) + b[d] + c)
            },
            this.setLogLevel = function(c) {
                typeof c == "number" && c >= 0 && c < b.length ? a = c : this.log("Invalid input logLevel: " + c)
            }
        }, WMKS.BROWSER = new function() {
            var a = navigator.userAgent.toLowerCase(),
                b = navigator.appVersion.toString(),
                c = function() {
                    return !0
                },
                d = function() {
                    return !1
                };
            this.isIE = a.indexOf("msie") !== -1 || a.indexOf("trident") !== -1 || a.indexOf("edge") !== -1 ? c : d, this.isOpera = a.indexOf("opera/") !== -1 || a.indexOf("opr/") !== -1 ? c : d, this.isWebkit = this.isChrome = this.isSafari = this.isBB = d, !this.isIE() && !this.isOpera() && a.indexOf("applewebkit") !== -1 && (this.isWebkit = c, a.indexOf("chrome") !== -1 ? this.isChrome = c : a.indexOf("bb") !== -1 ? this.isBB = c : a.indexOf("safari") !== -1 && (this.isSafari = c)), this.isGecko = !this.isWebkit() && !this.isIE() && a.indexOf("gecko") !== -1 ? c : d, this.isFirefox = a.indexOf("firefox") !== -1 || a.indexOf("iceweasel") !== -1 ? c : d, this.isLowBandwidth = a.indexOf("mobile") !== -1 ? c : d, this.isIOS = a.indexOf("iphone") !== -1 || a.indexOf("ipod") !== -1 || a.indexOf("ipad") !== -1 ? c : d, this.isAndroid = a.indexOf("android") !== -1 ? c : d, this.isIEMobile = a.indexOf("IEMobile") !== -1 ? c : d, this.hasTouchInput = "ontouchstart" in window || navigator.maxTouchPoints || navigator.msMaxTouchPoints ? c : d, this.isTouchDevice = this.isIOS() || this.isAndroid() || this.isBB() ? c : d, this.isChromeOS = a.indexOf("cros") !== -1 ? c : d, this.isWindows = a.indexOf("windows") !== -1 ? c : d, this.isLinux = a.indexOf("linux") !== -1 ? c : d, this.isMacOS = a.indexOf("macos") !== -1 || a.indexOf("macintosh") > -1 ? c : d;
            var e = function(b, c) {
                var d = a.match(b);
                return d && d.length > c && d[c] || ""
            };
            this.version = {
                full: ""
            }, this.isSafari() ? this.version.full = e(/Version[ \/]([0-9\.]+)/i, 1) : this.isChrome() ? this.version.full = e(/Chrome\/([0-9\.]+)/i, 1) : this.isFirefox() ? this.version.full = e(/(?:Firefox|Iceweasel)[ \/]([0-9\.]+)/i, 1) : this.isOpera() ? this.version.full = e(/Version[ \/]([0-9\.]+)/i, 1) || e(/(?:opera|opr)[\s\/]([0-9\.]+)/i, 1) : this.isIE() && (this.version.full = e(/(?:\b(MS)?IE\s+|\bTrident\/7\.0;.*\s+rv:|\bEdge\/)([0-9\.]+)/i, 2));
            var f = this.version.full.split(".");
            this.version.major = parseInt(f.length > 0 ? f[0] : 0, 10), this.version.minor = parseInt(f.length > 1 ? f[1] : 0, 10), this.version.float = parseFloat(this.version.full), this.isCanvasSupported = function() {
                try {
                    var a = document.createElement("canvas"),
                        b = !!a.getContext;
                    return a = null, b
                } catch (c) {
                    return !1
                }
            }
        }, WMKS.CONST = {
            CLICK: {
                left: 1,
                middle: 2,
                right: 4
            },
            FORCE_RAW_KEY_CODE: {
                8: !0,
                9: !0,
                13: !0
            }
        }, WMKS.UTIL = {
            createCanvas: function(a) {
                var b = {};
                return a && (b.position = "absolute"), $("<canvas/>").css(b)
            },
            createVideo: function(a) {
                var b = {};
                return a && (b.position = "absolute"), $("<video/>").css(b)
            },
            getLineLength: function(a, b) {
                return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2))
            },
            isHighResolutionSupported: function() {
                return window.devicePixelRatio && window.devicePixelRatio > 1
            },
            isFullscreenNow: function() {
                return document.fullscreenElement || document.mozFullScreenElement || document.msFullscreenElement || document.webkitFullscreenElement ? !0 : !1
            },
            isFullscreenEnabled: function() {
                return !WMKS.BROWSER.isSafari() && (document.fullscreenEnabled || document.mozFullScreenEnabled || document.msFullscreenEnabled || document.webkitFullscreenEnabled) ? !0 : !1
            },
            toggleFullScreen: function(a, b) {
                var c = WMKS.UTIL.isFullscreenNow(),
                    d = b || document.documentElement;
                if (!WMKS.UTIL.isFullscreenEnabled()) {
                    WMKS.LOGGER.warn("This browser does not support fullScreen mode.");
                    return
                }
                if (c === a) return;
                c ? document.exitFullscreen ? document.exitFullscreen() : document.mozCancelFullScreen ? document.mozCancelFullScreen() : document.webkitCancelFullScreen ? document.webkitCancelFullScreen() : document.msExitFullscreen && document.msExitFullscreen() : d.requestFullscreen ? d.requestFullscreen() : d.mozRequestFullScreen ? d.mozRequestFullScreen() : d.webkitRequestFullscreen ? d.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT) : d.msRequestFullscreen && d.msRequestFullscreen()
            }
        }, WMKS.BitBuf = function(a, b) {
            return "use strict", this._buf = a, this._size = b, this._readCount = 0, this._overflow = !1, this._thisByte = 0, this._thisByteBits = 0, this
        }, WMKS.BitBuf.prototype.readBits0 = function(a, b) {
            "use strict";
            var c;
            return this._bits < b ? (this._overflow = !0, -1) : (c = ~(255 >> b), a <<= b, a |= (this._thisByte & c) >> 8 - b, this._thisByte <<= b, this._thisByte &= 255, this._thisByteBits -= b, a)
        }, WMKS.BitBuf.prototype.readBits = function(a) {
            "use strict";
            var b = a,
                c = 0;
            if (this._overflow) return 0;
            while (a > this._thisByteBits) {
                a -= this._thisByteBits, c = this.readBits0(c, this._thisByteBits);
                if (this._readCount < this._size) this._thisByte = this._buf[this._readCount++], this._thisByteBits = 8;
                else {
                    this._thisByte = 0, this._thisByteBits = 0;
                    if (a > 0) return this._overflow = !0, 0
                }
            }
            return c = this.readBits0(c, a), c
        }, WMKS.BitBuf.prototype.readEliasGamma = function() {
            "use strict";
            var a = 0,
                b, c, d = this._readCount,
                e = this._thisByteBits;
            while (!this._overflow && (c = this.readBits(1)) == 0) a++;
            return b = 1 << a, a && (b |= this.readBits(a)), b
        }, WMKS.WebSocket = function(a, b) {
            return new window.WebSocket(a, b)
        }, Array.prototype.push8 = function(a) {
            this.push(a & 255)
        }, Array.prototype.push16 = function(a) {
            this.push(a >> 8 & 255, a & 255)
        }, Array.prototype.push32 = function(a) {
            this.push(a >> 24 & 255, a >> 16 & 255, a >> 8 & 255, a & 255)
        }, Array.prototype.push16le = function(a) {
            this.push(a & 255, a >> 8 & 255)
        }, Array.prototype.push32le = function(a) {
            this.push(a & 255, a >> 8 & 255, a >> 16 & 255, a >> 24 & 255)
        }, e.instanceNumber = 0, e.byteStreamFormat = 'video/mp4; codecs="avc1.640030"', e.prototype.toString = function() {
            return this._name
        }, e.prototype.init = function(a, b, c, d, e) {
            var f = this,
                g = b || window.URL || window.webkitURL,
                h = c || window.MediaSource || window.WebKitMediaSource;
            this.reset(), this._decodeDoneCb = d, this._decodeErrorCb = e, this._mediaPlayer = a, this._mediaSource = new h, this._mediaPlayer.src = g.createObjectURL(this._mediaSource), this._mediaSource.addEventListener("sourceopen", function(a) {
                return f._onMediaSourceOpen(a)
            }, !1), this._mediaSource.addEventListener("webkitsourceopen", function(a) {
                return f._onMediaSourceOpen(a)
            }, !1)
        }, e.prototype._onMediaSourceOpen = function(a) {
            var b = this;
            WMKS.LOGGER.log(this + " media source status is changed to open.");
            if (this._mediaSource.readyState !== "open") {
                WMKS.LOGGER.log(this + " media source is not open yet.");
                return
            }
            this._sourceBuffer = this._mediaSource.addSourceBuffer(e.byteStreamFormat), this._sourceBuffer.addEventListener("updateend", function() {
                if (b._tempQueue.length === 0) return;
                b._tempQueue[0].method === "add" && b._decodeDoneCb && (b._doneRequest++, WMKS.LOGGER.debug(b + " request track: send " + b._sendRequest + " done " + b._doneRequest), b._decodeDoneCb()), b._tempQueue.shift(), b._flushPayloads()
            }), this._sourceBuffer.addEventListener("error", function(a) {
                WMKS.LOGGER.error(b + " error code is " + a)
            }), this._flushPayloads();
            return
        }, e.prototype._flushPayloads = function() {
            var a = 0,
                b = 0,
                c = 0;
            if (!this._sourceBuffer) {
                WMKS.LOGGER.log(this + "source buffer is not ready yet.");
                return
            }
            if (this._tempQueue.length === 0) return;
            if (this._mediaSource.readyState === "open" && !this._sourceBuffer.updating) try {
                if (this._tempQueue[0].method === "add") this._sourceBuffer.appendBuffer(this._tempQueue[0].payload);
                else if (this._tempQueue[0].method === "remove") {
                    a = this._sourceBuffer.buffered.start(0), b = this._sourceBuffer.buffered.end(0), c = this._mediaPlayer.currentTime - .5, WMKS.LOGGER.log(this + " status:  start " + a + " end " + b + " with current time " + this._mediaPlayer.currentTime);
                    if (c > a) WMKS.LOGGER.log(this + " start to remove from " + a + " to " + c), this._sourceBuffer.remove(a, c);
                    else throw WMKS.LOGGER.log(this + " it is too close to clear buffer."), this._tempQueue.shift(), "close buffer"
                }
            } catch (d) {
                if (d.name === "QuotaExceededError") {
                    var e = this;
                    WMKS.LOGGER.log(this + " browser is full."), setTimeout(function() {
                        e._tempQueue.unshift({
                            method: "remove"
                        }), e._flushPayloads()
                    }, 0)
                } else WMKS.LOGGER.error(this + " encounters a unrecoverable error. " + d), this._isError = !0, this._decodeDoneCb && this._decodeDoneCb(), this._decodeErrorCb && !this._isErrorDoneCalled && (this._isErrorDoneCalled = !0, this._decodeErrorCb())
            }
        }, e.prototype.reset = function() {
            WMKS.LOGGER.log(this + " is reset."), this._mediaSource && (this._sourceBuffer && (this._mediaSource.removeSourceBuffer(this._sourceBuffer), this._sourceBuffer = null), this._mediaSource.readyState === "open" && this._mediaSource.endOfStream(), this._mediaSource = null), this._mediaPlayer && (this._mediaPlayer.src = "", this._mediaPlayer = null), this._sendRequest = 0, this._doneRequest = 0, this._decodeDoneCb = null, this._decodeErrorCb = null, this._isError = !1, this._isErrorDoneCalled = !1, this._tempQueue = []
        }, e.prototype.appendData = function(a) {
            if (this._isError) {
                WMKS.LOGGER.log(this + " is in error state."), this._decodeDoneCb && this._decodeDoneCb();
                return
            }
            this._sendRequest++, this._tempQueue.push({
                method: "add",
                payload: a
            }), this._flushPayloads(), this._mediaPlayer && this._mediaPlayer.paused && this._mediaPlayer.play()
        },
        function() {
            function d(a) {
                var b = 0,
                    c = 0,
                    d = $.event.dispatch || $.event.handle;
                "detail" in a && (c = a.detail * -1), "wheelDelta" in a && (c = a.wheelDelta), "wheelDeltaY" in a && (c = a.wheelDeltaY), "wheelDeltaX" in a && (b = a.wheelDeltaX * -1), "axis" in a && a.axis === a.HORIZONTAL_AXIS && (b = c * -1, c = 0), "deltaY" in a && (c = a.deltaY * -1), "deltaX" in a && (b = a.deltaX);
                if (c === 0 && b === 0) return;
                return a = $.event.fix(a), a.type = "mousewheel", delete a.wheelDelta, a.wheelDeltaX = b, a.wheelDeltaY = c, d.call(this, a)
            }
            var a = "onwheel" in document || document.documentMode >= 9 ? ["wheel"] : ["mousewheel", "DomMouseScroll", "MozMousePixelScroll"],
                b = ["wheel", "mousewheel", "DOMMouseScroll", "MozMousePixelScroll"];
            if ($.event.fixHooks)
                for (var c = b.length; c;) $.event.fixHooks[b[--c]] = $.event.mouseHooks;
            $.event.special.mousewheel = {
                setup: function() {
                    if (this.addEventListener) {
                        var b;
                        for (b = 0; b < a.length; b++) this.addEventListener(a[b], d, !1)
                    } else this.onmousewheel = d
                },
                tearDown: function() {
                    if (this.removeEventListener) {
                        var b;
                        for (b = 0; b < a.length; b++) this.removeEventListener(a[b], d, !1)
                    } else this.onmousewheel = d
                }
            }
        }(), WMKS.VNCDecoder = function(a) {
            var b;
            return this.options = $.extend({}, this.options, a), $.extend(this, {
                useVMWRequestResolution: !1,
                useVMWRequestMultiMon: !1,
                useVMWKeyEvent: !1,
                allowVMWKeyEvent2UnicodeAndRaw: !1,
                useVMWAck: !1,
                useVMWAudioAck: !1,
                useVMWSessionClose: !1,
                serverSupportsMKSVChanClipboard: !1,
                vvc: null,
                vvcSession: null,
                _websocket: null,
                _encrypted: !1,
                _receivedFirstUpdate: !1,
                _serverInitialized: !1,
                _canvas: [],
                _currentCursorURI: "default",
                _cursorVisible: !0,
                _imageCache: [],
                _copyRectBlit: null,
                _copyRectOffscreenBlit: null,
                _state: this.DISCONNECTED,
                _FBWidth: 0,
                _FBHeight: 0,
                _FBName: "",
                _FBBytesPerPixel: 0,
                _FBDepth: 3,
                _mouseButtonMask: 0,
                _mouseX: 0,
                _mouseY: 0,
                onDecodeComplete: {},
                rects: 0,
                rectsRead: 0,
                rectsDecoded: 0,
                requestedWidth: 0,
                requestedHeight: 0,
                decodeToCacheEntry: -1,
                updateCache: [],
                updateCacheEntries: 0,
                resolutionTimeout: {},
                resolutionTimer: null,
                resolutionRequestActive: !1,
                updateReqId: 0,
                typematicState: 1,
                typematicPeriod: 33333,
                typematicDelay: 5e5,
                _keyboardLEDs: 0,
                _frameTimestampLo: 0,
                _frameTimestampHi: 0,
                rect: [],
                _msgTimer: null,
                _mouseTimer: null,
                _mouseActive: !1,
                msgTimeout: {},
                mouseTimeout: {},
                _retryConnectionTimer: null,
                _url: "",
                _receiveQueue: [],
                _receiveQueueIndex: 0,
                _receiveQueueLength: 0
            }), this.setRenderCanvas(this.options.canvas), this.options.backCanvas && (this._canvas = this._canvas.concat([this.options.backCanvas]), this._canvas[1].ctx = this.options.backCanvas.getContext("2d")), this.options.blitTempCanvas && (this._canvas = this._canvas.concat([this.options.blitTempCanvas]), this._canvas[2].ctx = this.options.blitTempCanvas.getContext("2d")), this.options.mediaPlayer && (this._mp4Decoder = new e), typeof createImageBitmap != "undefined" ? (this._useImageBitmaps = !0, this._imageManager = null) : this._imageManager = new d(256), this._releaseImage = function(a) {
                this._imageManager && this._imageManager.releaseImage(a)
            }, this
        }, $.extend(WMKS.VNCDecoder.prototype, {
            options: {
                canvas: null,
                backCanvas: null,
                blitTempCanvas: null,
                VCDProxyHandshakeVmxPath: null,
                useUnicodeKeyboardInput: !1,
                enableVorbisAudioClips: !1,
                enableOpusAudioClips: !1,
                enableAacAudioClips: !1,
                enableVVC: !0,
                enableUint8Utf8: !1,
                enableVMWSessionClose: !1,
                retryConnectionInterval: -1,
                sendRelativeMouseEvent: !1,
                onConnecting: function() {},
                onConnected: function() {},
                onBeforeDisconnected: function() {},
                onDisconnected: function() {},
                onAuthenticationFailed: function() {},
                onError: function(a) {},
                onProtocolError: function() {},
                onNewDesktopSize: function(a, b) {},
                onKeyboardLEDsChanged: function(a) {},
                onCursorStateChanged: function(a) {},
                onHeartbeat: function(a) {},
                onUpdateCopyPasteUI: function(a, b) {},
                onCopy: function(a) {},
                onSetReconnectToken: function(a) {},
                onAudio: function(a) {},
                onAudioMixer: function(a) {},
                onEncodingChanged: function(a) {},
                cacheSizeKB: 102400,
                cacheSizeEntries: 1024
            },
            DISCONNECTED: 0,
            VNC_ACTIVE_STATE: 1,
            FBU_DECODING_STATE: 2,
            FBU_RESTING_STATE: 3,
            msgFramebufferUpdate: 0,
            msgSetColorMapEntries: 1,
            msgRingBell: 2,
            msgServerCutText: 3,
            msgVMWSrvMessage: 127,
            msgVMWSrvMessage_ServerCaps: 0,
            msgVMWSrvMessage_Audio: 3,
            msgVMWSrvMessage_Heartbeat: 4,
            msgVMWSrvMessage_SetReconnectToken: 6,
            msgVMWSrvMessage_SessionClose: 7,
            msgVMWSrvMessage_AudioMixer: 8,
            msgClientEncodings: 2,
            msgFBUpdateRequest: 3,
            msgKeyEvent: 4,
            msgPointerEvent: 5,
            msgVMWClientMessage: 127,
            msgVMWKeyEvent: 0,
            msgVMWPointerEvent2: 2,
            msgVMWKeyEvent2: 6,
            msgVMWAudioAck: 7,
            msgVMWSessionClose: 12,
            encRaw: 0,
            encCopyRect: 1,
            encTightPNG: -260,
            encDesktopSize: -223,
            encH264RectEnc: 1464686100,
            encTightDiffComp: 1464686102,
            encH264MP4: 1464686104,
            encVMWDefineCursor: 1464686180,
            encVMWCursorState: 1464686181,
            encVMWCursorPosition: 1464686182,
            encVMWTypematicInfo: 1464686183,
            encVMWLEDState: 1464686184,
            encVMWServerPush2: 1464686203,
            encVMWServerCaps: 1464686202,
            encVMWFrameStamp: 1464686204,
            encOffscreenCopyRect: 1464686206,
            encUpdateCache: 1464686207,
            encTopologyChangeEnc: 1464686208,
            encH264MultimonEnc: 1464686209,
            encTightJpegQuality10: -23,
            diffCompCopyFromPrev: 1,
            diffCompAppend: 2,
            diffCompAppendRemaining: 3,
            updateCacheOpInit: 0,
            updateCacheOpBegin: 1,
            updateCacheOpEnd: 2,
            updateCacheOpReplay: 3,
            updateCacheCapDisableOffscreenSurface: 2,
            updateCacheCapReplay: 4,
            serverCapKeyEvent: 2,
            serverCapClientCaps: 8,
            serverCapUpdateAck: 32,
            serverCapRequestResolution: 128,
            serverCapKeyEvent2Unicode: 256,
            serverCapKeyEvent2JSKeyCode: 512,
            serverCapAudioAck: 1024,
            serverCapMultiMon: 4096,
            serverCapUpdateCacheInfo: 8192,
            serverCapDisablingCopyUI: 16384,
            serverCapDisablingPasteUI: 32768,
            serverCapSessionClose: 131072,
            serverCapHasMKSVChanClipboard: 262144,
            clientCapHeartbeat: 256,
            clientCapVorbisAudioClips: 512,
            clientCapOpusAudioClips: 1024,
            clientCapAacAudioClips: 2048,
            clientCapAudioAck: 4096,
            clientCapSetReconnectToken: 16384,
            clientCapSessionClose: 32768,
            clientCapUseMKSVChanClipboard: 65536,
            clientCapUseAudioMixer: 131072,
            audioflagRequestAck: 1,
            subEncFill: 128,
            subEncJPEG: 144,
            subEncPNG: 160,
            subEncDiffJpeg: 176,
            subEncMask: 240,
            mouseTimeResolution: 16,
            resolutionDelay: 300
        }), WMKS.VNCDecoder.prototype.fail = function(a) {
            return WMKS.LOGGER.log(a), this.disconnect(), null
        }, WMKS.VNCDecoder.prototype._assumeServerIsVMware = function() {
            if (!this.usedVNCHandshake) return;
            this.useVMWKeyEvent = !0
        }, WMKS.VNCDecoder.prototype._receiveQueueBytesUnread = function() {
            return "use strict", this._receiveQueueLength - this._receiveQueueIndex
        }, WMKS.VNCDecoder.prototype._receiveQueueConsumeBytes = function(a) {
            this._receiveQueueIndex += a;
            while (this._receiveQueueIndex > 0 && this._receiveQueue[0].data.byteLength <= this._receiveQueueIndex) this._receiveQueueLength -= this._receiveQueue[0].data.byteLength, this._receiveQueueIndex -= this._receiveQueue[0].data.byteLength, this._receiveQueue.shift()
        }, WMKS.VNCDecoder.prototype._receiveQueueReset = function() {
            this._receiveQueue = [], this._receiveQueueLength = 0, this._receiveQueueIndex = 0
        }, WMKS.VNCDecoder.prototype._readBytes = function(a) {
            "use strict";
            if (this._receiveQueueIndex + a <= this._receiveQueue[0].data.byteLength) {
                var b = new Uint8Array(this._receiveQueue[0].data, this._receiveQueueIndex, a);
                return this._receiveQueueConsumeBytes(a), b
            }
            var b = new Uint8Array(a),
                c = 0;
            while (a > 0) {
                var d = Math.min(a, this._receiveQueue[0].data.byteLength - this._receiveQueueIndex),
                    e = new Uint8Array(this._receiveQueue[0].data, this._receiveQueueIndex, d);
                b.set(e, c), c += d, a -= d, this._receiveQueueConsumeBytes(d)
            }
            return b
        }, WMKS.VNCDecoder.prototype._readByte = function() {
            "use strict";
            var a = this._readBytes(1);
            return a[0]
        }, WMKS.VNCDecoder.prototype._skipBytes = function(a) {
            "use strict",
            this._receiveQueueConsumeBytes(a)
        }, WMKS.VNCDecoder.prototype._readString = function(b) {
            "use strict";
            var c = this._readBytes(b);
            return a(c)
        }, WMKS.VNCDecoder.prototype._readStringUTF8 = function(a) {
            "use strict";
            var b, c, d, e, f = [],
                g = 0,
                h = this._readBytes(a);
            while (g < a) b = h[g], b < 128 ? (f.push(b), g++) : b < 224 ? (c = h[g + 1] & 63, f.push((b & 31) << 6 | c), g += 2) : b < 240 ? (c = h[g + 1] & 63, d = h[g + 2] & 63, f.push((b & 15) << 12 | c << 6 | d), g += 3) : (c = h[g + 1] & 63, d = h[g + 2] & 63, e = h[g + 3] & 63, f.push((b & 7) << 18 | c << 12 | d << 6 | e), g += 4);
            return String.fromCharCode.apply(String, f)
        }, WMKS.VNCDecoder.prototype._readInt16 = function() {
            "use strict";
            var a = this._readBytes(2);
            return (a[0] << 8) + a[1]
        }, WMKS.VNCDecoder.prototype._readInt32 = function() {
            "use strict";
            var a = this._readBytes(4);
            return (a[0] << 24) + (a[1] << 16) + (a[2] << 8) + a[3]
        }, WMKS.VNCDecoder.prototype._sendString = function(a) {
            "use strict",
            this._sendBytes(b(a))
        }, WMKS.VNCDecoder.prototype._sendBytes = function(a) {
            "use strict";
            if (!this._websocket) return;
            var b = new ArrayBuffer(a.length),
                c = new Uint8Array(b),
                d;
            for (d = 0; d < a.length; d++) c[d] = a[d];
            this._websocket.send(b)
        }, WMKS.VNCDecoder.prototype._setReadCB = function(a, b, c) {
            this.nextBytes = a, this.nextFn = b, this.nextArg = c
        }, WMKS.VNCDecoder.prototype._sendMouseEvent = function() {
            if (this.options.sendRelativeMouseEvent) {
                var a = [];
                a.push8(this.msgVMWClientMessage), a.push8(this.msgVMWPointerEvent2), a.push16(19), a.push8(0), a.push32(this._mouseX), a.push32(this._mouseY), a.push32(this._mouseButtonMask), a.push8(0), a.push8(0), this._sendBytes(a), this._mouseActive = !1
            } else {
                var a = [];
                a.push8(this.msgPointerEvent), a.push8(this._mouseButtonMask), a.push16(this._mouseX), a.push16(this._mouseY), this._sendBytes(a), this._mouseActive = !1
            }
        }, WMKS.VNCDecoder.prototype._sendResolutionRequest = function() {
            var a = [];
            a.push8(this.msgVMWClientMessage), a.push8(5), a.push16(8), a.push16(this.requestedWidth), a.push16(this.requestedHeight), this._sendBytes(a)
        }, WMKS.VNCDecoder.prototype._sendTopologyRequest = function(a) {
            var b = [],
                c = 0;
            b.push8(this.msgVMWClientMessage), b.push8(10), b.push16(6 + 20 * a.length), b.push16(a.length);
            for (c = 0; c < a.length; c++) b.push32(a[c].left), b.push32(a[c].top), b.push32(a[c].requestedWidth), b.push32(a[c].requestedHeight), b.push32(0);
            this._sendBytes(b)
        }, WMKS.VNCDecoder.prototype._sendClientEncodingsMsg = function() {
            var a, b = [this.encTightDiffComp, this.encTightPNG, this.encDesktopSize, this.encVMWDefineCursor, this.encVMWCursorState, this.encVMWCursorPosition, this.encVMWTypematicInfo, this.encVMWLEDState, this.encVMWServerPush2, this.encVMWServerCaps, this.encTightJpegQuality10, this.encVMWFrameStamp, this.encUpdateCache];
            this.options.mediaPlayer && b.unshift(this.encH264MP4), this.options.enableRawH264 && b.unshift(this.encH264RectEnc), this.options.enableTopologyChange && b.unshift(this.encToppologyChangeEnc), this.options.enableH264Multimon && b.unshift(this.encH264MultimonEnc), this._canvas[1] && (b = [this.encOffscreenCopyRect].concat(b)), b = [this.encCopyRect].concat(b);
            var c = [];
            c.push8(this.msgClientEncodings), c.push8(0), c.push16(b.length);
            for (a = 0; a < b.length; a += 1) c.push32(b[a]);
            this._sendBytes(c)
        }, WMKS.VNCDecoder.prototype._sendFBUpdateRequestMsg = function(a) {
            var b = [];
            b.push8(this.msgFBUpdateRequest), b.push8(a), b.push16(0), b.push16(0), b.push16(this._FBWidth), b.push16(this._FBHeight), this._sendBytes(b)
        }, WMKS.VNCDecoder.prototype._sendAck = function(a) {
            var b = this.updateReqId || 1,
                c;
            if (this.useVMWAck) {
                var d = (a + 2) * 10,
                    e = [];
                e.push8(this.msgVMWClientMessage), e.push8(4), e.push16(8), e.push8(b), e.push8(0), e.push16(d), this._sendBytes(e)
            } else this._sendFBUpdateRequestMsg(b)
        }, WMKS.VNCDecoder.prototype._sendAudioAck = function(a, b) {
            var c = [];
            c.push8(this.msgVMWClientMessage), c.push8(this.msgVMWAudioAck), c.push16(12), c.push32(a), c.push32(b), this._sendBytes(c)
        }, WMKS.VNCDecoder.prototype._changeCursor = function(a, b, d, e, f, g) {
            var h = [],
                i = f * g * 4,
                j = Math.ceil(f * g / 8),
                k = i + 40 + j * 2,
                l, m;
            h.push16le(0), h.push16le(2), h.push16le(1), h.push8(f), h.push8(g), h.push8(0), h.push8(0), h.push16le(d), h.push16le(e), h.push32le(k), h.push32le(h.length + 4), h.push32le(40), h.push32le(f), h.push32le(g * 2), h.push16le(1), h.push16le(32), h.push32le(0), h.push32le(i + 2 * j), h.push32le(0), h.push32le(0), h.push32le(0), h.push32le(0);
            for (m = g - 1; m >= 0; m -= 1)
                for (l = 0; l < f; l += 1) {
                    var n = m * Math.ceil(f / 8) + Math.floor(l / 8),
                        o = 0;
                    b.length > 0 && (o = b[n] << l % 8 & 128 ? 255 : 0), n = (f * m + l) * 4, h.push8(a[n]), h.push8(a[n + 1]), h.push8(a[n + 2]), b.length > 0 ? h.push8(o) : h.push8(a[n + 3])
                }
            for (m = 0; m < g; m += 1)
                for (l = 0; l < Math.ceil(f / 8); l += 1) h.push8(0);
            for (m = 0; m < g; m += 1)
                for (l = 0; l < Math.ceil(f / 8); l += 1) h.push8(0);
            var p = "data:image/x-icon;base64," + c.encodeFromArray(h);
            this._currentCursorURI = "url(" + p + ") " + d + " " + e + ", default", this._updateCanvasCursor()
        }, WMKS.VNCDecoder.prototype._readOffscreenCopyRect = function(a) {
            a.srcBuffer = this._readByte(), a.dstBuffer = this._readByte(), a.srcX = this._readInt16(), a.srcY = this._readInt16(), this._nextRect()
        }, WMKS.VNCDecoder.prototype._readUpdateCacheData = function(a) {
            "use strict",
            a.data = this._readBytes(a.dataLength),
            this._nextRect()
        }, WMKS.VNCDecoder.prototype._readUpdateCacheInitData = function(a) {
            "use strict",
            this._skipBytes(4),
            this._skipBytes(4),
            a.updateCacheEntries = this._readInt16(),
            this._skipBytes(4),
            this._nextRect()
        }, WMKS.VNCDecoder.prototype._readUpdateCacheRect = function(a) {
            "use strict",
            a.opcode = this._readByte(),
            a.slot = this._readInt16(),
            a.dataLength = this._readInt16(),
            a.opcode != this.updateCacheOpInit ? this._setReadCB(a.dataLength, this._readUpdateCacheData, a) : this._setReadCB(a.dataLength, this._readUpdateCacheInitData, a)
        }, WMKS.VNCDecoder.prototype._readVMWDefineCursorData = function(a) {
            var b, c, d = [],
                e = [],
                f = [],
                g, h, i, j;
            if (a.cursorType === 0) {
                a.masklength > 0 && (d = this._readBytes(a.masklength)), a.pixelslength > 0 && (e = this._readBytes(a.pixelslength));
                for (b = 0; b < a.height; b++)
                    for (c = 0; c < a.width; c++) {
                        h = c + b * a.width, i = b * Math.ceil(a.width / 8) + Math.floor(c / 8), g = 1 << 7 - c % 8;
                        if (d[h * 4] === 255 && d[h * 4 + 1] === 255 && d[h * 4 + 2] === 255 && d[h * 4 + 3] === 255)
                            for (var k = 0; k < 4; k++) e[h * 4 + k] !== 0 && (e[h * 4 + k] = 0, f[i] |= g);
                        else f[i] |= g
                    }
            } else a.cursorType === 1 && a.pixelslength > 0 && (e = this._readBytes(a.pixelslength), a.pixelslength == 4 && e[3] == 0 && (e[3] = 1));
            this._changeCursor(e, f, a.x, a.y, a.width, a.height), this._nextRect()
        }, WMKS.VNCDecoder.prototype._readVMWDefineCursor = function(a) {
            a.cursorType = this._readByte(), this._skipBytes(1), a.pixelslength = 4 * a.width * a.height, a.cursorType === 0 ? a.masklength = a.pixelslength : a.masklength = 0, this._setReadCB(a.pixelslength + a.masklength, this._readVMWDefineCursorData, a)
        }, WMKS.VNCDecoder.prototype._updateCanvasCursor = function() {
            var a, b;
            this._cursorVisible ? WMKS.BROWSER.isIE() ? a = "default" : a = this._currentCursorURI : WMKS.BROWSER.isFirefox() && WMKS.BROWSER.isMacOS() ? a = "none, !important" : a = "none", b = this._mediaPlayer || this._canvas[0], b.style.cursor !== a && (b.style.cursor = a)
        }, WMKS.VNCDecoder.prototype._readVMWCursorState = function(a) {
            var b = this._readInt16();
            this._cursorVisible = !!(b & 1), this._updateCanvasCursor(), this.options.onCursorStateChanged(this._cursorVisible), this._nextRect()
        }, WMKS.VNCDecoder.prototype._readVMWCursorPosition = function(a) {
            WMKS.VNCDecoder.cursorPosition = a, this._nextRect()
        }, WMKS.VNCDecoder.prototype._readTypematicInfo = function(a) {
            this.typematicState = this._readInt16(), this.typematicPeriod = this._readInt32(), this.typematicDelay = this._readInt32(), this._nextRect()
        }, WMKS.VNCDecoder.prototype._readLEDState = function(a) {
            this._keyboardLEDs = this._readInt32(), this.options.onKeyboardLEDsChanged(this._keyboardLEDs), this._nextRect()
        }, WMKS.VNCDecoder.prototype._readFrameStamp = function(a) {
            this._frameTimestampLo = this._readInt32(), this._frameTimestampHi = this._readInt32(), this._nextRect()
        }, WMKS.VNCDecoder.prototype._fillRectWithColor = function(a, b, c, d, e, f) {
            var g;
            g = "rgb(" + f[0] + "," + f[1] + "," + f[2] + ")", a.fillStyle = g, a.fillRect(b, c, d, e)
        }, WMKS.VNCDecoder.prototype._blitImageString = function(a, b, c, d, e, f) {
            var g, h, i;
            g = a.createImageData(d, e), i = g.data;
            for (h = 0; h < d * e * 4; h += 4) i[h] = f.charCodeAt(h + 2), i[h + 1] = f.charCodeAt(h + 1), i[h + 2] = f.charCodeAt(h + 0), i[h + 3] = 255;
            a.putImageData(g, b, c)
        }, WMKS.VNCDecoder.prototype._copyRectGetPut = function(a, b, c, d, e, f, g, h) {
            var i;
            i = this._canvas[a].ctx.getImageData(b, c, d, e), this._canvas[f].ctx.putImageData(i, g, h), delete i
        }, WMKS.VNCDecoder.prototype._copyRectDrawImage = function(a, b, c, d, e, f, g, h) {
            this._canvas[f].ctx.drawImage(this._canvas[a], b, c, d, e, g, h, d, e)
        }, WMKS.VNCDecoder.prototype._copyRectDrawImageTemp = function(a, b, c, d, e, f, g, h) {
            this._copyRectDrawImage(a, b, c, d, e, 2, b, c), this._copyRectDrawImage(2, b, c, d, e, f, g, h)
        }, WMKS.VNCDecoder.prototype._lighten = function(a, b, c, d, e) {
            "use strict",
            this._canvas[0].ctx.globalCompositeOperation = "lighten",
            this._canvas[0].ctx.fillStyle = e,
            this._canvas[0].ctx.fillRect(a, b, c, d),
            this._canvas[0].ctx.globalCompositeOperation = "source-over"
        }, WMKS.VNCDecoder.prototype._decodeDiffComp = function(a, b) {
            "use strict";
            var c = 0,
                d = 0,
                e = new Uint8Array(1024),
                f;
            while (d < a.length && c <= e.length) switch (a[d++]) {
                case this.diffCompCopyFromPrev:
                    var g = a[d++];
                    e.set(b.subarray(c, c + g), c), c += g;
                    break;
                case this.diffCompAppend:
                    var g = a[d++];
                    e.set(a.subarray(d, d + g), c), d += g, c += g;
                    break;
                case this.diffCompAppendRemaining:
                    return f = new Uint8Array(c + a.length - d), f.set(e.subarray(0, c), 0), d < a.length && f.set(a.subarray(d), c), f
            }
            return e.subarray(0, c)
        }, WMKS.VNCDecoder.prototype._readTightData = function(a) {
            var b = this._readBytes(this.nextBytes),
                d = window.URL || window.webkitURL,
                e = this,
                f;
            a.subEncoding === this.subEncDiffJpeg && (b = this._decodeDiffComp(b, this._lastJpegData)), a.subEncoding !== this.subEncPNG ? (this._lastJpegData = b, f = "image/jpeg") : f = "image/png", this._useImageBitmaps ? createImageBitmap(new Blob([b], {
                type: f
            })).then(function(b) {
                a.image = b, e.onDecodeComplete()
            }) : (a.image = this._imageManager.getImage(), a.image.width = a.width, a.image.height = a.height, d && !(WMKS.BROWSER.isChrome() && WMKS.BROWSER.version.major >= 50) ? (a.image.onload = this.onDecodeObjectURLComplete, a.image.src = d.createObjectURL(new Blob([b], {
                type: f
            }))) : (b = c.encodeFromArray(b), a.image.onload = this.onDecodeComplete, a.image.src = "data:" + f + ";base64," + b)), this._nextRect()
        }, WMKS.VNCDecoder.prototype._readTightPNG = function(a) {
            a.subEncoding = this._readByte(), a.subEncoding &= this.subEncMask, this._mediaPlayer && this.options.onEncodingChanged("TightPNG");
            if (a.subEncoding === this.subEncFill) a.color = [], a.color[0] = this._readByte(), a.color[1] = this._readByte(), a.color[2] = this._readByte(), a.color[3] = 255, this.rectsDecoded++, this._nextRect();
            else {
                var b = 1,
                    c = this._readByte();
                c & 128 && (b = 2, c &= -129, c += this._readByte() << 7, c & 16384 &&
                    (b = 3, c &= -16385, c += this._readByte() << 14)), this._setReadCB(c, this._readTightData, a)
            }
        }, WMKS.VNCDecoder.prototype._readH264MP4Rect = function(a) {
            var b = this._readInt16(),
                c = this._readInt16(),
                d = this._readInt32();
            b === 1 && (WMKS.LOGGER.log("MP4 encoding is selected and stream is reset."), this.options.onEncodingChanged("MP4"), this._mp4Decoder.init(this._mediaPlayer, undefined, undefined, this.onDecodeComplete, this.onDecodeMP4Error)), this._setReadCB(d, this._readH264MP4Data, a)
        }, WMKS.VNCDecoder.prototype._readH264MP4Data = function(a) {
            this._mp4Decoder.appendData(this._readBytes(this.nextBytes)), this._nextRect()
        }, WMKS.VNCDecoder.prototype._readH264Rect = function(a) {
            var b = this._readInt16(),
                c = this._readInt16(),
                d = this._readInt32();
            b === 1 && (WMKS.LOGGER.log("Raw H264 encoding is selected and stream is reset."), this.options.onEncodingChanged("RawH264")), this._setReadCB(d, this._readH264Data, a)
        }, WMKS.VNCDecoder.prototype._readH264Data = function(a) {
            this._readBytes(this.nextBytes), this._nextRect()
        }, WMKS.VNCDecoder.prototype._readCopyRect = function(a) {
            a.srcX = this._readInt16(), a.srcY = this._readInt16(), this._nextRect()
        }, WMKS.VNCDecoder.prototype._readRaw = function(a) {
            a.imageString = this._readString(this.nextBytes), this._nextRect()
        }, WMKS.VNCDecoder.prototype._readDesktopSize = function(a) {
            this._FBWidth = a.width, this._FBHeight = a.height, this.options.onNewDesktopSize(this._FBWidth, this._FBHeight), this._nextRect()
        }, WMKS.VNCDecoder.prototype._readRect = function() {
            var a = this.rectsRead;
            this.rect[a] = {}, this.rect[a].x = this._readInt16(), this.rect[a].y = this._readInt16(), this.rect[a].width = this._readInt16(), this.rect[a].height = this._readInt16(), this.rect[a].encoding = this._readInt32(), this.rect[a].encoding !== this.encTightPNG && this.rect[a].encoding !== this.encH264MP4 && this.rectsDecoded++;
            switch (this.rect[a].encoding) {
                case this.encRaw:
                    this._setReadCB(this.rect[a].width * this.rect[a].height * this._FBBytesPerPixel, this._readRaw, this.rect[a]);
                    break;
                case this.encCopyRect:
                    this._setReadCB(4, this._readCopyRect, this.rect[a]);
                    break;
                case this.encOffscreenCopyRect:
                    this._setReadCB(6, this._readOffscreenCopyRect, this.rect[a]);
                    break;
                case this.encUpdateCache:
                    this._setReadCB(5, this._readUpdateCacheRect, this.rect[a]);
                    break;
                case this.encH264RectEnc:
                    this._setReadCB(8, this._readH264Rect, this.rect[a]);
                    break;
                case this.encTightPNG:
                    this._setReadCB(4, this._readTightPNG, this.rect[a]);
                    break;
                case this.encH264MP4:
                    this._setReadCB(8, this._readH264MP4Rect);
                    break;
                case this.encDesktopSize:
                    this._readDesktopSize(this.rect[a]);
                    break;
                case this.encVMWDefineCursor:
                    this._setReadCB(2, this._readVMWDefineCursor, this.rect[a]);
                    break;
                case this.encVMWCursorState:
                    this._assumeServerIsVMware(), this._setReadCB(2, this._readVMWCursorState, this.rect[a]);
                    break;
                case this.encVMWCursorPosition:
                    this._readVMWCursorPosition(this.rect[a]);
                    break;
                case this.encVMWTypematicInfo:
                    this._setReadCB(10, this._readTypematicInfo, this.rect[a]);
                    break;
                case this.encVMWLEDState:
                    this._setReadCB(4, this._readLEDState, this.rect[a]);
                    break;
                case this.encVMWFrameStamp:
                    this._setReadCB(8, this._readFrameStamp, this.rect[a]);
                    break;
                default:
                    return this.fail("Disconnected: unsupported encoding " + this.rect[a].encoding)
            }
        }, WMKS.VNCDecoder.prototype._evictUpdateCacheEntry = function(a) {
            "use strict",
            this.updateCache[a].image !== null && this._releaseImage(this.updateCache[a].image),
            this.updateCache[a] = {},
            this.updateCache[a].image = null
        }, WMKS.VNCDecoder.prototype._executeUpdateCacheInit = function(a) {
            "use strict";
            var b;
            for (b = 0; b < this.updateCacheEntries; b++) this._evictUpdateCacheEntry(b);
            this.updateCache = [], this.updateCacheEntries = a.updateCacheEntries;
            if (this.updateCacheEntries > this.options.cacheSizeEntries) return this.fail("Disconnected: requested cache too large");
            for (b = 0; b < this.updateCacheEntries; b++) this.updateCache[b] = {}, this.updateCache[b].image = null
        }, WMKS.VNCDecoder.prototype._updateCacheInsideBeginEnd = function() {
            return this.decodeToCacheEntry !== -1
        }, WMKS.VNCDecoder.prototype._updateCacheInitialized = function() {
            return this.updateCacheSizeEntries !== 0
        }, WMKS.VNCDecoder.prototype._executeUpdateCacheBegin = function(a) {
            "use strict";
            var b, c, d, e, f;
            if (!this._updateCacheInitialized() || this._updateCacheInsideBeginEnd() || a.slot >= this.updateCacheEntries) return this.fail("Disconnected: requested cache slot too large");
            b = new WMKS.BitBuf(a.data, a.dataLength), c = !b.readBits(1), d = 0, f = 0;
            do {
                d = b.readEliasGamma(), c = !c;
                if (c)
                    for (e = 0; e < d && e < this.updateCacheEntries; e++) this._evictUpdateCacheEntry(e + f);
                f += d
            } while (f < this.updateCacheEntries && !b.overflow);
            this.decodeToCacheEntry = a.slot, this._evictUpdateCacheEntry(a.slot), this.updateCache[this.decodeToCacheEntry].imageWidth = a.width, this.updateCache[this.decodeToCacheEntry].imageHeight = a.height
        }, WMKS.VNCDecoder.prototype._executeUpdateCacheEnd = function(a) {
            "use strict";
            var b = this.updateCache[this.decodeToCacheEntry],
                c, d, e = 0,
                f = 0,
                g = Math.ceil(this._FBWidth / 16),
                h = Math.ceil(this._FBHeight / 16),
                i = 0,
                j = 0,
                k = b.imageWidth / 16,
                l = b.imageHeight / 16,
                m, n;
            if (!this._updateCacheInitialized() || !this._updateCacheInsideBeginEnd() || a.slot != this.decodeToCacheEntry || a.slot >= this.updateCacheEntries) return this.fail("Disconnected: requested cache slot invalid");
            b.mask = a.data, b.maskLength = a.dataLength, n = new WMKS.BitBuf(b.mask, b.maskLength), c = !n.readBits(1), d = 0;
            do d == 0 && (d = n.readEliasGamma(), c = !c), m = Math.min(k - i, g - e), m = Math.min(m, d), c && (this._canvas[0].ctx.drawImage(b.image, i * 16, j * 16, m * 16, 16, e * 16, f * 16, m * 16, 16), i += m, i == k && (i = 0, j++)), e += m, e == g && (e = 0, f++), d -= m; while (f < h && !n._overflow);
            this.decodeToCacheEntry = -1
        }, WMKS.VNCDecoder.prototype._executeUpdateCacheReplay = function(a) {
            "use strict";
            if (a.slot >= this.updateCacheEntries) return this.fail("Disconnected: requested cache slot invalid");
            var b = 0,
                c = 0,
                d = Math.ceil(this._FBWidth / 16),
                e = Math.ceil(this._FBHeight / 16),
                f, g = this.updateCache[a.slot],
                h = 0,
                i = 0,
                j = g.imageWidth / 16,
                k = g.imageHeight / 16,
                l = new WMKS.BitBuf(a.data, a.dataLength),
                m = new WMKS.BitBuf(g.mask, g.maskLength),
                n = !m.readBits(1),
                o = 0,
                p = !l.readBits(1),
                q = 0;
            if (!this._updateCacheInitialized() || this._updateCacheInsideBeginEnd() || a.slot >= this.updateCacheEntries) return this.fail("");
            do o == 0 && (o = m.readEliasGamma(), n = !n), q == 0 && (q = l.readEliasGamma(), p = !p), f = d - b, f = Math.min(f, o), n && (f = Math.min(f, j - h), f = Math.min(f, q), p && this._canvas[0].ctx.drawImage(g.image, h * 16, i * 16, f * 16, 16, b * 16, c * 16, f * 16, 16), h += f, h == j && (h = 0, i++), q -= f), b += f, b == d && (b = 0, c++), o -= f; while (c < e && !l._overflow && !m._overflow)
        }, WMKS.VNCDecoder.prototype._handleVCDProxyVmxPathMessage = function() {
            var a = this._readString(17);
            if (a !== "connect info vmx\n") return this.fail("Invalid connection vmx request: " + a);
            this._sendString(this.options.VCDProxyHandshakeVmxPath), this._setReadCB(12, this._peekFirstMessage)
        }, WMKS.VNCDecoder.prototype._executeUpdateCache = function(a) {
            "use strict";
            switch (a.opcode) {
                case this.updateCacheOpInit:
                    this._executeUpdateCacheInit(a);
                    break;
                case this.updateCacheOpBegin:
                    this._executeUpdateCacheBegin(a);
                    break;
                case this.updateCacheOpEnd:
                    this._executeUpdateCacheEnd(a);
                    break;
                case this.updateCacheOpReplay:
                    this._executeUpdateCacheReplay(a);
                    break;
                default:
                    return this.fail("Disconnected: requested cache opcode invalid")
            }
        }, WMKS.VNCDecoder.prototype._executeRectSingle = function(a) {
            var b = this._canvas[0].ctx;
            switch (a.encoding) {
                case this.encRaw:
                    this._blitImageString(b, a.x, a.y, a.width, a.height, a.imageString), a.imageString = "";
                    break;
                case this.encCopyRect:
                    this._copyRectBlit(0, a.srcX, a.srcY, a.width, a.height, 0, a.x, a.y);
                    break;
                case this.encOffscreenCopyRect:
                    this._copyRectOffscreenBlit(a.srcBuffer, a.srcX, a.srcY, a.width, a.height, a.dstBuffer, a.x, a.y);
                    break;
                case this.encTightPNG:
                    a.subEncoding === this.subEncFill ? this._fillRectWithColor(b, a.x, a.y, a.width, a.height, a.color) : this.decodeToCacheEntry === -1 ? (b.drawImage(a.image, a.x, a.y), this._releaseImage(a.image), a.image = null) : (this.updateCache[this.decodeToCacheEntry].image = a.image, a.image = null);
                    break;
                case this.encDesktopSize:
                case this.encVMWDefineCursor:
                case this.encVMWCursorState:
                case this.encVMWCursorPosition:
                case this.encH264MP4:
                    break;
                case this.encUpdateCache:
                    this._executeUpdateCache(a);
                    break;
                default:
            }
        }, WMKS.VNCDecoder.prototype._executeRects = function() {
            var a;
            if (this._state !== this.FBU_DECODING_STATE) return this.fail("wrong state: " + this._state);
            if (this.rectsDecoded !== this.rects || this.rectsRead !== this.rects) return this.fail("messed up state");
            for (a = 0; a < this.rects; a++) this._executeRectSingle(this.rect[a]), delete this.rect[a];
            var b = (new Date).getTime();
            this._sendAck(b - this.decodeStart), this.rects = 0, this.rectsRead = 0, this.rectsDecoded = 0, this.updateReqId = 0, this._receivedFirstUpdate === !1 && (this.options.onConnected(), this._receivedFirstUpdate = !0);
            var c = this;
            this._state = this.FBU_RESTING_STATE, this._getNextServerMessage(), this._msgTimer = setTimeout(this.msgTimeout, 1)
        }, WMKS.VNCDecoder.prototype._nextRect = function() {
            this.rectsRead++, this.rectsRead < this.rects ? this._setReadCB(12, this._readRect) : (this._state = this.FBU_DECODING_STATE, this.rectsDecoded === this.rects && this._executeRects())
        }, WMKS.VNCDecoder.prototype._gobble = function(a) {
            this._skipBytes(this.nextBytes), a()
        }, WMKS.VNCDecoder.prototype._getNextServerMessage = function() {
            this._setReadCB(1, this._handleServerMsg)
        }, WMKS.VNCDecoder.prototype._framebufferUpdate = function() {
            this.updateReqId = this._readByte(), this.rects = this._readInt16(), this.rectsRead = 0, this.rectsDecoded = 0, this.decodeStart = (new Date).getTime(), this._setReadCB(12, this._readRect)
        }, WMKS.VNCDecoder.prototype._handleServerInitializedMsg = function() {
            var a = this;
            this._FBWidth = this._readInt16(), this._FBHeight = this._readInt16();
            var b = this._readByte(),
                c = this._readByte(),
                d = this._readByte(),
                e = this._readByte();
            WMKS.LOGGER.log("Screen: " + this._FBWidth + " x " + this._FBHeight + ", bits-per-pixel: " + b + ", depth: " + c + ", big-endian-flag: " + d + ", true-color-flag: " + e), this._skipBytes(6);
            var f = this._readByte(),
                g = this._readByte(),
                h = this._readByte();
            WMKS.LOGGER.debug("red shift: " + f + ", green shift: " + g + ", blue shift: " + h), this._skipBytes(3);
            var i = this._readInt32();
            this.options.onNewDesktopSize(this._FBWidth, this._FBHeight), this._copyRectBlit = this._copyRectDrawImageTemp, this._copyRectOffscreenBlit = this._copyRectDrawImage;
            if (e) this._FBBytesPerPixel = 4, this._FBDepth = 3;
            else return this.fail("no colormap support");
            var j = function() {
                a._FBName = a._readString(i), a._sendClientEncodingsMsg(), a._sendFBUpdateRequestMsg(0), WMKS.LOGGER.log("Connected " + (a._encrypted ? "(encrypted)" : "(unencrypted)") + " to: " + a._FBName), a._serverInitialized = !0, a._getNextServerMessage()
            };
            this._setReadCB(i, j)
        }, WMKS.VNCDecoder.prototype._peekFirstMessage = function() {
            this.usedVNCHandshake = this._receiveQueue[0].data.byteLength == 12, this.usedVNCHandshake ? this._setReadCB(12, this._handleProtocolVersionMsg) : this._setReadCB(24, this._handleServerInitializedMsg)
        }, WMKS.VNCDecoder.prototype._handleSecurityResultMsg = function() {
            var a = this,
                b, c = function() {
                    var c = a._readString(b);
                    return a.options.onAuthenticationFailed(), a.fail(c)
                },
                d = function() {
                    b = a._readInt32(), a._setReadCB(b, c)
                };
            switch (this._readInt32()) {
                case 0:
                    this._sendBytes([1]), this._setReadCB(24, this._handleServerInitializedMsg);
                    return;
                case 1:
                    this._setReadCB(4, d);
                    return;
                case 2:
                    return this.options.onAuthenticationFailed(), this.fail("Too many auth attempts");
                default:
                    return this.fail("Bogus security result")
            }
        }, WMKS.VNCDecoder.prototype._handleSecurityMsg = function() {
            var a = 0,
                b, c, d = this,
                e = function() {
                    var a = this._readString(c);
                    return d.options.onAuthenticationFailed(), d.fail(a)
                },
                f = function() {
                    c = d._readInt32(), d._setReadCB(c, e)
                },
                g = function() {
                    var c = d._readBytes(b);
                    WMKS.LOGGER.log("Server security types: " + c);
                    for (var e = 0; e < c.length; e += 1) c && c[e] < 3 && (a = c[e]);
                    if (a === 0) return d.fail("Unsupported security types: " + c);
                    d._sendBytes([a]), WMKS.LOGGER.log("Using authentication scheme: " + a);
                    if (a === 1) d._setReadCB(4, d._handleSecurityResultMsg);
                    else return d.fail("vnc authentication not implemented")
                };
            b = this._readByte(), b === 0 ? this._setReadCB(4, f) : this._setReadCB(b, g)
        }, WMKS.VNCDecoder.prototype._handleProtocolVersionMsg = function() {
            var a = this._readString(12);
            if (a !== "RFB 003.008\n") return this.fail("Invalid Version packet: " + a);
            this._sendString("RFB 003.008\n"), this._setReadCB(1, this._handleSecurityMsg)
        }, WMKS.VNCDecoder.prototype._sendClientCaps = function() {
            if (this._serverInitialized) {
                var a = [],
                    b = this.clientCapHeartbeat | this.clientCapAudioAck | this.clientCapSetReconnectToken;
                this.options.enableVorbisAudioClips ? b |= this.clientCapVorbisAudioClips : this.options.enableOpusAudioClips ? b |= this.clientCapOpusAudioClips : this.options.enableAacAudioClips && (b |= this.clientCapAacAudioClips), this.options.enableVMWSessionClose && (b |= this.clientCapSessionClose), this.options.enableVMWAudioMixer && (b |= this.clientCapUseAudioMixer), this.serverSupportsMKSVChanClipboard && this.vvcSession && (b |= this.clientCapUseMKSVChanClipboard), a.push8(this.msgVMWClientMessage), a.push8(3), a.push16(8), a.push32(b), this._sendBytes(a)
            }
        }, WMKS.VNCDecoder.prototype._sendSessionClose = function(a) {
            if (this._serverInitialized && this.useVMWSessionClose && this.options.enableVMWSessionClose) {
                WMKS.LOGGER.log("Send session close to server.");
                var b = [];
                b.push8(this.msgVMWClientMessage), b.push8(this.msgVMWSessionClose), b.push16(8), b.push32(a), this._sendBytes(b)
            }
        }, WMKS.VNCDecoder.prototype._sendUpdateCacheInfo = function() {
            "use strict";
            var a = [],
                b = this.updateCacheCapReplay | this.updateCacheCapDisableOffscreenSurface,
                c = this.options.cacheSizeEntries,
                d = this.options.cacheSizeKB;
            WMKS.LOGGER.trace("sendUpdateCacheInfo"), a.push8(this.msgVMWClientMessage), a.push8(11), a.push16(14), a.push32(b), a.push16(c), a.push32(d), this._sendBytes(a)
        }, WMKS.VNCDecoder.prototype._handleServerCapsMsg = function() {
            var a = this._readInt32();
            this.useVMWKeyEvent = !!(a & this.serverCapKeyEvent), this.allowVMWKeyEvent2UnicodeAndRaw = this.options.useUnicodeKeyboardInput && !!(a & this.serverCapKeyEvent2Unicode) && !!(a & this.serverCapKeyEvent2JSKeyCode), this.useVMWAck = !!(a & this.serverCapUpdateAck), this.useVMWRequestResolution = !!(a & this.serverCapRequestResolution), this.useVMWRequestMultiMon = !!(a & this.serverCapMultiMon), this.useVMWAudioAck = !!(a & this.serverCapAudioAck), this.useVMWSessionClose = !!(a & this.serverCapSessionClose), this.serverSupportsMKSVChanClipboard = !!(a & this.serverCapHasMKSVChanClipboard), this.useVMWRequestResolution && this.requestedWidth > 0 && this.requestedHeight > 0 && this.onRequestResolution(this.requestedWidth, this.requestedHeight), a & this.serverCapClientCaps && this._sendClientCaps(), a & this.serverCapUpdateCacheInfo && this._sendUpdateCacheInfo();
            if (a & this.serverCapDisablingCopyUI || a & this.serverCapDisablingPasteUI) {
                var b = 0,
                    c = 0;
                a & this.serverCapDisablingCopyUI && (b = 1), a & this.serverCapDisablingPasteUI && (c = 1), this.options.onUpdateCopyPasteUI(b, c)
            }
            this._getNextServerMessage()
        }, WMKS.VNCDecoder.prototype._handleServerHeartbeatMsg = function() {
            var a = this._readInt16();
            this.options.onHeartbeat(a), this._getNextServerMessage()
        }, WMKS.VNCDecoder.prototype._handleSessionCloseMsg = function() {
            var a = this._readInt32();
            this.options.onBeforeDisconnected(a), this._getNextServerMessage()
        }, WMKS.VNCDecoder.prototype._handleAudioMixer = function() {
            var a = this._readInt32(),
                b = this._readInt32(),
                c = this._readInt32(),
                d = this._readInt32();
            a < 2 && (b === 0 || b === 1) ? this.options.onAudioMixer({
                channelId: a,
                msgType: b,
                data: c,
                flags: d
            }) : WMKS.LOGGER.warn("Ignoring audio mixer message for an unsupported  channelId = " + a + " msgType = " + b + " data = " + c + " flags = " + d), this._getNextServerMessage()
        }, WMKS.VNCDecoder.prototype._handleServerSetReconnectTokenMsg = function(a) {
            var b = this._readString(a);
            this.options.onSetReconnectToken(b), this._getNextServerMessage()
        }, WMKS.VNCDecoder.prototype._handleServerAudioMsg = function() {
            var a = this._readInt32(),
                b = this._readInt32(),
                c = this._readInt32(),
                d = this._readInt32(),
                e = this._readInt32(),
                f = this._readInt32(),
                g = this._readInt32(),
                h = this._readInt32(),
                i = {
                    sampleRate: b,
                    numChannels: c,
                    containerSize: e,
                    sampleSize: d,
                    length: a,
                    audioTimestampLo: f,
                    audioTimestampHi: g,
                    frameTimestampLo: this._frameTimestampLo,
                    frameTimestampHi: this._frameTimestampHi,
                    flags: h,
                    data: null
                };
            this._setReadCB(a, this._handleServerAudioMsgData, i)
        }, WMKS.VNCDecoder.prototype._handleServerAudioMsgData = function(a) {
            a.length > 0 ? (a.data = this._readBytes(a.length), this.useVMWAudioAck && (a.flags == 0 || a.flags & this.audioflagRequestAck) && this._sendAudioAck(a.audioTimestampLo, a.audioTimestampHi), this.options.onAudio(a)) : WMKS.LOGGER.info("Audio data length is 0."), this._getNextServerMessage()
        }, WMKS.VNCDecoder.prototype._handleServerCutText = function(a) {
            var b = this._readStringUTF8(a);
            this.options.onCopy(b), this._getNextServerMessage()
        }, WMKS.VNCDecoder.prototype._handleServerMsg = function() {
            var a, b, c, d, e, f = this,
                g = this._readByte();
            switch (g) {
                case this.msgFramebufferUpdate:
                    this._setReadCB(3, this._framebufferUpdate);
                    break;
                case this.msgSetColorMapEntries:
                    var h = function() {
                        f._skipBytes(3);
                        var a = f._readInt16();
                        f._setReadCB(6 * a, f._gobble, f._getNextServerMessage)
                    };
                    this._setReadCB(5, h);
                    break;
                case this.msgRingBell:
                    this._getNextServerMessage();
                    break;
                case this.msgServerCutText:
                    var i = function() {
                        f._readBytes(3), a = f._readInt32(), a > 0 ? f._setReadCB(a, f._handleServerCutText, a) : f._getNextServerMessage()
                    };
                    this._setReadCB(8, i);
                    break;
                case this.msgVMWSrvMessage:
                    var j = function() {
                        var a = f._readByte(),
                            b = f._readInt16();
                        if (a === this.msgVMWSrvMessage_ServerCaps) {
                            if (b !== 8) return f.options.onProtocolError(), f.fail("invalid length message for id: " + a + ", len: " + b);
                            f._setReadCB(b - 4, f._handleServerCapsMsg)
                        } else if (a === this.msgVMWSrvMessage_Heartbeat) {
                            if (b !== 6) return f.options.onProtocolError(), f.fail("invalid length message for id: " + a + ", len: " + b);
                            f._setReadCB(b - 4, f._handleServerHeartbeatMsg)
                        } else if (a === this.msgVMWSrvMessage_SetReconnectToken) f._setReadCB(b - 4, f._handleServerSetReconnectTokenMsg, b - 4);
                        else if (a === this.msgVMWSrvMessage_Audio) {
                            if (b !== 36) return f.options.onProtocolError(), f.fail("invalid length message for id: " + a + ", len: " + b);
                            f._setReadCB(b - 4, f._handleServerAudioMsg)
                        } else if (a === this.msgVMWSrvMessage_SessionClose) {
                            if (b !== 8) return f.options.onProtocolError(), f.fail("invalid length message for id: " + a + ", len: " + b);
                            f._setReadCB(b - 4, f._handleSessionCloseMsg)
                        } else if (a === this.msgVMWSrvMessage_AudioMixer) {
                            if (b !== 20) return f.options.onProtocolError(), f.fail("invalid length message for id: " + a + ", len: " + b);
                            f._setReadCB(b - 4, f._handleAudioMixer)
                        } else {
                            var c = b - 4;
                            c === 0 ? f._getNextServerMessage() : f._setReadCB(c, f._gobble, f._getNextServerMessage)
                        }
                    };
                    this._setReadCB(3, j);
                    break;
                default:
                    return this.options.onProtocolError(), this.fail("Disconnected: illegal server message type " + g)
            }
        }, WMKS.VNCDecoder.prototype._processMessages = function() {
            while (this._state === this.VNC_ACTIVE_STATE && this._receiveQueueBytesUnread() >= this.nextBytes) {
                var a = this.nextBytes,
                    b = this._receiveQueueBytesUnread();
                this.nextFn(this.nextArg);
                var c = this._receiveQueueBytesUnread();
                if (a < b - c) return this.fail("decode overrun " + a + " vs " + (b - c))
            }
        }, WMKS.VNCDecoder.prototype.onMouseMove = function(a, b) {
            this._mouseX = a, this._mouseY = b, this._serverInitialized && (this._mouseActive = !0, this._mouseTimer === null && (this._sendMouseEvent(), this._mouseTimer = setTimeout(this.mouseTimeout, this.mouseTimeResolution)))
        }, WMKS.VNCDecoder.prototype.onMouseButton = function(a, b, c, d) {
            this._mouseX = a, this._mouseY = b, c ? this._mouseButtonMask |= d : this._mouseButtonMask &= ~d, this._serverInitialized && (this._mouseActive = !0, this._sendMouseEvent())
        }, WMKS.VNCDecoder.prototype.onKeyVScan = function(a, b) {
            if (this._serverInitialized) {
                var c = [];
                c.push8(this.msgVMWClientMessage), c.push8(this.msgVMWKeyEvent), c.push16(8), c.push16(a), c.push8(b), c.push8(0), this._sendBytes(c)
            }
        }, WMKS.VNCDecoder.prototype.onVMWKeyUnicode = function(a, b, c) {
            if (this._serverInitialized) {
                var d = [];
                d.push8(this.msgVMWClientMessage), d.push8(this.msgVMWKeyEvent2), d.push16(10), d.push32(a), d.push8(b), d.push8(c), this._sendBytes(d)
            }
        }, WMKS.VNCDecoder.prototype.onMouseWheel = function(a, b, c, d) {
            if (this._serverInitialized) {
                var e = [];
                e.push8(this.msgVMWClientMessage), e.push8(this.msgVMWPointerEvent2), e.push16(19), e.push8(1), e.push32(a), e.push32(b), e.push32(0), e.push8(d), e.push8(c), this._sendBytes(e)
            }
        }, WMKS.VNCDecoder.prototype.onRequestResolution = function(a, b) {
            this._serverInitialized && this.useVMWRequestResolution && (a !== this.requestedWidth || b !== this.requestedHeight) && (this.resolutionRequestActive = !0, clearTimeout(this.resolutionTimer), this.resolutionTimer = setTimeout(this.resolutionTimeout, this.resolutionDelay), this.requestedWidth = a, this.requestedHeight = b)
        }, WMKS.VNCDecoder.prototype.onRequestTopology = function(a) {
            this._serverInitialized && this.useVMWRequestMultiMon && (WMKS.LOGGER.log("Calling _sendTopologyRequest with arg:" + a), WMKS.LOGGER.log(a), console.log(a), this._sendTopologyRequest(a))
        }, WMKS.VNCDecoder.prototype.disconnect = function() {
            "use strict",
            this._state !== this.DISCONNECTED && (this._state = this.DISCONNECTED, this._mp4Decoder && (this._mp4Decoder.reset(), this._mp4Decoder = null), this._receiveQueueReset(), this.rects = 0, this._receivedFirstUpdate = !1, this._websocket && (this._sendSessionClose(23), this._websocket.onopen = null, this._websocket.onclose = null, this._websocket.onmessage = null, this._websocket.onerror = null, this._websocket.close(), delete this._websocket), this._serverInitialized = !1)
        }, WMKS.VNCDecoder.prototype.connect = function(a) {
            var b = this;
            this.setRenderCanvas(this.options.canvas), this._mediaPlayer = this.options.mediaPlayer, this.onDecodeComplete = function() {
                b.rectsDecoded++, b.rectsDecoded === b.rects && b.rectsRead === b.rects && (b._state = b.FBU_DECODING_STATE, b._executeRects())
            }, this.onDecodeObjectURLComplete = function() {
                URL.revokeObjectURL(this.src), b.onDecodeComplete()
            }, this.onDecodeMP4Error = function() {
                var a = b.options.mediaPlayer;
                WMKS.LOGGER.log("Resetting stream request is sent."), b.options.mediaPlayer = null, b._sendClientEncodingsMsg(), b.options.mediaPlayer = a, b._sendClientEncodingsMsg()
            }, this.msgTimeout = function() {
                b._state = b.VNC_ACTIVE_STATE, b._processMessages()
            }, this.mouseTimeout = function() {
                b._mouseTimer = null, b._mouseActive && (b._sendMouseEvent(), b._mouseTimer = setTimeout(b.mouseTimeout, b.mouseTimeResolution))
            }, this.resolutionTimeout = function() {
                b.resolutionRequestActive && (b._sendResolutionRequest(), b.resolutionRequestActive = !1)
            }, this.options.VCDProxyHandshakeVmxPath ? this._setReadCB(17, this._handleVCDProxyVmxPathMessage) : this._setReadCB(12, this._peekFirstMessage), this._url = a, this._receiveQueueReset(), this.wsOpen = function(a) {
                b._state = b.VNC_ACTIVE_STATE;
                if (this.protocol !== "binary" && this.protocol !== "vmware-vvc") return this.fail("no agreement on protocol");
                this.protocol === "vmware-vvc" && (b._setupVVC(), WMKS.LOGGER.log("WebSocket is using VMware Virtual Channels"), this.protocol = "binary"), this.protocol === "binary" && (this.binaryType = "arraybuffer", WMKS.LOGGER.log("WebSocket HAS binary support")), b.options.onConnecting(b.vvc, b.vvcSession), WMKS.LOGGER.log("WebSocket created protocol: " + this.protocol)
            }, this.wsClose = function(a) {
                b.options.onDisconnected(a.reason, a.code)
            }, this.wsMessage = function(a) {
                if (typeof a.data != "string") b._receiveQueue.push(a), b._receiveQueueLength += a.data.byteLength;
                else return b.fail("non-binary message");
                b._processMessages()
            }, this.wsError = function(a) {
                b.options.onError(a)
            }, this.protocolList = ["binary"], this.options.enableVVC && this.protocolList.push("vmware-vvc"), this._setupConnection = function() {
                b._websocket = WMKS.WebSocket(b._url, b.protocolList), b._websocket.onopen = b.wsOpen, b._websocket.onclose = b.wsClose, b._websocket.onmessage = b.wsMessage, b._websocket.onerror = b.wsError
            }, this._setupVVC = function() {
                b.vvc = new g, b.vvcSession = b.vvc.openSession(b._websocket), b.vvcSession.onerror = function(a) {
                    b.vvcSession.close()
                }, b.vvcSession.ontransportclose = function(a) {
                    b.wsClose(a)
                }, b.vvcSession.ontransporterror = function(a) {
                    b.wsError(a)
                };
                var a = b.vvc.createListener(b.vvcSession, "blast-*");
                a.onpeeropen = function(a, c) {
                    c.name === "blast-mks" ? (c.onclose = function(c) {
                        a.close(), b._websocket = null, b.disconnect()
                    }, c.onerror = function(c) {
                        a.close(), b._websocket = null, b.disconnect()
                    }, b._websocket = c, c.onmessage = b.wsMessage, a.acceptChannel(c)) : c.name === "blast-audio" && (c.onclose = function(b) {
                        a.close()
                    }, c.onerror = function(b) {
                        a.close()
                    }, c.onmessage = b.wsMessage, a.acceptChannel(c))
                }
            }, this._retryConnectionTimeout = function() {
                b._state === b.DISCONNECTED && (WMKS.LOGGER.log("Connection timeout. Retrying now."), b._websocket && (b._websocket.onclose = function() {}, b._websocket.close(), b._websocket = null), b._setupConnection()), b._retryConnectionTimer = null
            }, this.options.enableUint8Utf8 && f(this), this._setupConnection(), this.options.retryConnectionInterval > 0 && (WMKS.LOGGER.log("Check connection status after " + this.options.retryConnectionInterval + "ms."), this._retryConnectionTimer = setTimeout(this._retryConnectionTimeout, this.options.retryConnectionInterval))
        }, WMKS.VNCDecoder.prototype.setRenderCanvas = function(a) {
            this._canvas[0] = a, this._canvas[0].ctx = a.getContext("2d");
            if (!this._canvas[0].ctx.createImageData) throw "no canvas imagedata support"
        }, WMKS.CONST.KB = {
            ControlKeys: [8, 9, 13, 16, 17, 18, 19, 20, 27, 33, 34, 35, 36, 37, 38, 39, 40, 45, 46, 91, 92, 93, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 144, 145, 240],
            Modifiers: [16, 17, 18, 91, 92],
            WestEuroLanguage: ["de-DE", "it-IT", "es-ES", "pt-BR", "pt-PT", "fr-FR", "fr-CH", "de-CH"],
            Diacritics: [192],
            KEY_CODE: {
                Shift: 16,
                Ctrl: 17,
                Alt: 18,
                Meta: 91,
                Enter: 13,
                CapsLock: 20
            },
            SoftKBRawKeyCodes: [8, 9, 13],
            keyInputDefaultValue: " ",
            ANSIShiftSymbols: '~!@#$%^&*()_+{}|:"<>?',
            ANSINoShiftSymbols: "`-=[]\\;',./1234567890",
            WindowsKeys: {
                left: 91,
                right: 92
            },
            VScanMap: {}
        }, WMKS.BROWSER.isMacOS() && (WMKS.CONST.KB.Modifiers = [16, 17, 18, 91, 92, 224]), WMKS.CONST.KB.ANSISpecialSymbols = WMKS.CONST.KB.ANSIShiftSymbols + WMKS.CONST.KB.ANSINoShiftSymbols, WMKS.KeyboardManager = function(a) {
            "use strict";
            if (!a || !a.vncDecoder) return null;
            this._vncDecoder = a.vncDecoder, this.ignoredRawKeyCodes = a.ignoredRawKeyCodes, this.fixANSIEquivalentKeys = a.fixANSIEquivalentKeys, this.mapMetaToCtrlForKeys = a.mapMetaToCtrlForKeys, this.keyDownKeyTimer = null, this.keyDownIdentifier = null, this.pendingKey = null, this.addCtrlKey = !1, this.activeModifiers = [], this.keyToUnicodeMap = {}, this.keyToRawMap = {}, this.keyboardLayoutId = a.keyboardLayoutId, this.UnicodeToVScanMap = WMKS.CONST.KB.VScanMap[this.keyboardLayoutId], this._windowsKeyManager = {
                enabled: a.enableWindowsKey,
                windowsOn: !1,
                leftWindowsDown: !1,
                rightWindowsDown: !1,
                modifiedKeyMap: {
                    Pause: 19
                },
                modifiedKey: null,
                reset: function() {
                    this.windowsOn = !1, this.leftWindowsDown = !1, this.rightWindowsDown = !1, this.modifiedKey = null
                },
                keyboardHandler: function(a) {
                    a.keyCode === WMKS.CONST.KB.WindowsKeys.left || a.keyCode === 224 ? (this.leftWindowsDown = a.type === "keydown", this.leftWindowsDown || (this.windowsOn = !1)) : a.keyCode === WMKS.CONST.KB.WindowsKeys.right && (this.rightWindowsDown = a.type === "keydown", this.rightWindowsDown || (this.windowsOn = !1))
                },
                modifyKey: function(a) {
                    return a === 3 && (this.windowsOn ? (a = this.modifiedKeyMap.Pause, this.modifiedKey = 3) : this.modifiedKey === 3 && (a = this.modifiedKeyMap.Pause, this.modifiedKey = null)), a
                }
            }, this._extractKeyCodeFromEvent = function(a) {
                var b = 0,
                    c = !1;
                if (a.keyCode) b = a.keyCode, this.keyboardLayoutId == "pt-PT" && WMKS.BROWSER.isChrome() && (a.keyCode === 186 || a.keyCode === 191) && WMKS.BROWSER.isWindows() && (a.keyCode === 186 ? b = 180 : b = 126), this.keyboardLayoutId == "de-DE" && a.keyCode == 192 && WMKS.BROWSER.isFirefox() && (b = 187), (this.keyboardLayoutId === "fr-CH" || this.keyboardLayoutId === "de-CH") && WMKS.BROWSER.isWindows() && a.keyCode === 221 && (b = 94);
                else if (a.which) b = a.which;
                else if (a.keyIdentifier && a.keyIdentifier.substring(0, 2) === "U+") {
                    b = parseInt("0x" + a.keyIdentifier.slice(2), 16);
                    if (b) c = !0;
                    else return WMKS.LOGGER.log("assert: Unicode identifier=" + a.keyIdentifier + " int conversion failed, keyCode=" + b), null
                } else if (a.keyCode === 0 && WMKS.BROWSER.isFirefox() && a.key && this._vncDecoder.allowVMWKeyEvent2UnicodeAndRaw) b = 0;
                else {
                    this._vncDecoder.allowVMWKeyEvent2UnicodeAndRaw || (b = this.handleInternationalKeyboard(a, b)), this.keyboardLayoutId == "ja-JP_106/109" && !WMKS.BROWSER.isFirefox() && a.keyCode === 0 && (b = 165);
                    if (!b) return WMKS.LOGGER.trace("assert: could not read keycode from event, keyIdentifier=" + a.keyIdentifier), null
                }
                return !c && this._windowsKeyManager.enabled && (b = this._windowsKeyManager.modifyKey(b)), {
                    keyCode: b,
                    isUnicode: c
                }
            }, this.onKeyDown = function(a) {
                var b, c = 0,
                    d = !1,
                    e = this;
                b = this._extractKeyCodeFromEvent(a);
                if (!b) return WMKS.LOGGER.log("Extraction of keyCode from keyUp event failed."), !1;
                c = b.keyCode, d = b.isUnicode, this._syncModifiers(a);
                if (c === 0) return WMKS.LOGGER.log("onKeyDown: Do not send 0 to server."), !0;
                if ($.inArray(c, WMKS.CONST.KB.Modifiers) !== -1) return a.returnValue = !1, !1;
                if (WMKS.CONST.KB.ControlKeys.indexOf(c) !== -1) return a.returnValue = !1, this._handleControlKeys(c);
                if (d) return WMKS.LOGGER.log("Send unicode down from keyIdentifier: " + c), e.sendKey(c, !1, !0), a.returnValue = !1, !1;
                this.keyDownKeyTimer !== null && (WMKS.LOGGER.log("assert: nuking an existing keyDownKeyTimer"), clearTimeout(this.keyDownKeyTimer));
                var f = 0;
                return WMKS.BROWSER.isFirefox() && WMKS.BROWSER.isMacOS() && a.altKey === !0 && a.ctrlKey === !0 && !this.allowVMWKeyEvent2UnicodeAndRaw ? f = 1 : f = 0, this.keyDownKeyTimer = setTimeout(function() {
                    e.sendKey(c, !1, !1), e.keyDownKeyTimer = null, e.pendingKey = null
                }, f), this.pendingKey = c, a.originalEvent && a.originalEvent.keyIdentifier && (this.keyDownIdentifier = a.originalEvent.keyIdentifier), a.returnValue = this.activeModifiers.length !== 1 || this.activeModifiers[0] !== WMKS.CONST.KB.KEY_CODE.Alt && this.activeModifiers[0] !== WMKS.CONST.KB.KEY_CODE.Ctrl && this.activeModifiers[0] !== WMKS.CONST.KB.WindowsKeys.left && this.activeModifiers[0] !== WMKS.CONST.KB.WindowsKeys.right, a.returnValue
            }, this._handleControlKeys = function(a) {
                if (this.isAddCapsLockEvent(a)) {
                    this.sendKey(a, !1, !1), this.sendKey(a, !0, !1);
                    return
                }
                return this.sendKey(a, !1, !1), !1
            }, this.isAddCapsLockEvent = function(a) {
                return a === WMKS.CONST.KB.KEY_CODE.CapsLock && WMKS.BROWSER.isMacOS() || a === 240 && WMKS.BROWSER.isWindows()
            }, this._handleCapsLockKey = function(a) {
                if (this.isAddCapsLockEvent(a) && !this._vncDecoder.allowVMWKeyEvent2UnicodeAndRaw) return this.sendKey(a, !1, !1), this.sendKey(a, !0, !1), !1
            }, this._syncModifiers = function(a) {
                var b, c, d, e, f = [a.shiftKey, a.ctrlKey, a.altKey, a.metaKey, !1, !1];
                a.altGraphKey === !0 && (f[1] = f[2] = !0), f = this.resetEventValue(a, f), a.metaKey === !0 && this.mapMetaToCtrlForKeys.indexOf(a.keyCode) !== -1 && (f[1] = !0, f[3] = !1);
                if (this._windowsKeyManager.enabled) {
                    this._windowsKeyManager.keyboardHandler(a);
                    if (a.ctrlKey === !0)
                        if (this._windowsKeyManager.leftWindowsDown || this.activeModifiers.indexOf(WMKS.CONST.KB.WindowsKeys.left) !== -1) f[1] = !1, f[3] = !0, this._windowsKeyManager.windowsOn = !0;
                        else if (this._windowsKeyManager.rightWindowsDown || this.activeModifiers.indexOf(WMKS.CONST.KB.WindowsKeys.right) !== -1) f[1] = !1, f[4] = !0, this._windowsKeyManager.windowsOn = !0
                }
                for (d = 0; d < WMKS.CONST.KB.Modifiers.length; d++) b = WMKS.CONST.KB.Modifiers[d], c = f[d], e = this.activeModifiers.indexOf(b), c && e === -1 ? (this.activeModifiers.push(b), this.sendKey(b, !1, !1)) : !c && e !== -1 && (this.activeModifiers.splice(e, 1), this.sendKey(b, !0, !1))
            }, this.resetEventValue = function(a, b) {
                var c = a.charCode || a.which,
                    d = WMKS.keyboardUtils._WindowsFirefoxAltGr[this.keyboardLayoutId];
                return !!d && d.indexOf(c) !== -1 && WMKS.BROWSER.isWindows() && WMKS.BROWSER.isFirefox() && (b[1] = b[2] = !0), b
            }, this.cancelModifiers = function(a) {
                var b;
                if (WMKS.BROWSER.isTouchDevice() && !a) return;
                for (b = 0; b < this.activeModifiers.length; b++) this.sendKey(this.activeModifiers[b], !0, !1);
                this.activeModifiers.length = 0, this._windowsKeyManager.enabled && this._windowsKeyManager.reset()
            }, this.updateModifiers = function(a, b) {
                this.sendKey(a, b, !1), b ? this.activeModifiers.splice(this.activeModifiers.indexOf(a), 1) : this.activeModifiers.push(a)
            }, this.onKeyPress = function(a) {
                var b, c = !1,
                    d = !1,
                    e = !1,
                    f = !1,
                    g = !1,
                    h = "",
                    i = WMKS.CONST.KB.WestEuroLanguage.indexOf(this.keyboardLayoutId) !== -1;
                if (WMKS.BROWSER.isMacOS() && this.activeModifiers.length === 1 && this.activeModifiers[0] === WMKS.CONST.KB.KEY_CODE.Alt) return WMKS.LOGGER.log("Preferring raw keycode with Alt held (Mac)"), !1;
                if (a.charCode && a.charCode >= 32) b = a.charCode, c = !1;
                else if (a.keyCode) b = a.keyCode, b = this.remapUncontrolKey(a, b, c).keyCode, c = this.remapUncontrolKey(a, b, c).isRaw;
                else return WMKS.LOGGER.log("assert: could not read keypress event"), !1;
                this.keyDownKeyTimer !== null && (clearTimeout(this.keyDownKeyTimer), this.keyDownKeyTimer = null);
                if (c && WMKS.CONST.KB.ControlKeys.indexOf(b) !== -1) return !1;
                if (this.handleUnusefulKeys(a)) return !1;
                a = this.fixDeFrChSpecialKeys(a, this.pendingKey, b), this._syncModifiers(a), this.pendingKey !== null && (c ? this.keyToRawMap[this.pendingKey] = b : this.keyToUnicodeMap[this.pendingKey] = b);
                if (this.fixANSIEquivalentKeys && a.originalEvent) {
                    if (a.originalEvent.key) h = a.originalEvent.key;
                    else if (!WMKS.BROWSER.isWindows() || !WMKS.BROWSER.isChrome()) a.originalEvent.keyIdentifier === "" && this.keyDownIdentifier ? h = String.fromCharCode(parseInt(this.keyDownIdentifier.replace("U+", ""), 16)) : a.originalEvent.keyIdentifier && (h = String.fromCharCode(parseInt(a.originalEvent.keyIdentifier.replace("U+", ""), 16)));
                    h && (f = h.charCodeAt(0) !== b, d = WMKS.CONST.KB.ANSIShiftSymbols.indexOf(h) !== -1 && this.activeModifiers.indexOf(WMKS.CONST.KB.KEY_CODE.Shift) === -1, e = WMKS.CONST.KB.ANSINoShiftSymbols.indexOf(h) !== -1 && this.activeModifiers.indexOf(WMKS.CONST.KB.KEY_CODE.Shift) !== -1, g = WMKS.CONST.KB.ANSISpecialSymbols.indexOf(h) !== -1)
                }
                return this.keyDownIdentifier = null, this.fixANSIEquivalentKeys && h && g && (f || d || e) ? (e && this.sendKey(WMKS.CONST.KB.KEY_CODE.Shift, !0, !1), this.handleSoftKb(h.charCodeAt(0), !0), e && this.sendKey(WMKS.CONST.KB.KEY_CODE.Shift, !1, !1)) : (this._vncDecoder.allowVMWKeyEvent2UnicodeAndRaw || (b = this.handleInternationalKeyboard(a, b, this.pendingKey)), this.pendingKey !== null && (c ? this.keyToRawMap[this.pendingKey] = b : this.keyToUnicodeMap[this.pendingKey] = b), this.sendAndfixWrongKeys(a, b, c, this.pendingKey)), (this.pendingKey === 50 && b === 126 || this.pendingKey === 55 && b === 96) && !c && (WMKS.LOGGER.debug("Sending extra up for Unicode " + b + " so one isn't missed."), this.sendKey(b, !0, !c)), this.pendingKey = null, !1
            }, this.fixDeFrChSpecialKeys = function(a, b, c) {
                return (this.keyboardLayoutId === "de-CH" || this.keyboardLayoutId === "fr-CH") && WMKS.BROWSER.isWindows() && WMKS.BROWSER.isFirefox() && (a.charCode === 176 && b === 176 ? (a.keyCode = 1176, a.charCode = 1176) : a.charCode === 167 && b === 167 && (a.keyCode = 1167, a.charCode = 1167)), a
            }, this.sendAndfixWrongKeys = function(a, b, c, d) {
                var e = this.isSmallKeyboardKey(a, b, d),
                    f = this.isAddShiftKey(a, b),
                    g = this.isAddAltGr(a, b),
                    h = this.isDeleteShiftKey(a, b);
                if (e || f || h || g) {
                    if (e || f) this.sendKey(16, !1, !1), this.sendKey(b, !1, !c), this.sendKey(16, !0, !1);
                    h && (this.sendKey(16, !0, !1), this.sendKey(b, !1, !c), this.sendKey(16, !1, !1)), g && (this.sendKey(17, !1, !1), this.sendKey(18, !1, !1), this.sendKey(b, !1, !c), this.sendKey(17, !0, !1), this.sendKey(18, !0, !1))
                } else this.sendKey(b, !1, !c)
            }, this.isSmallKeyboardKey = function(a, b, c) {
                var d = !1,
                    e = WMKS.keyboardUtils._SmallKeyboardKey[this.keyboardLayoutId];
                return a.shiftKey === !1 && a.altKey === !1 && a.ctrlKey === !1 && this.isFitSmallKeyboardPendingKey(b, c) && !!e && e.indexOf(b) !== -1 && (d = !0), d
            }, this.isFitSmallKeyboardPendingKey = function(a, b) {
                var c = !0,
                    d = WMKS.keyboardUtils._smallKeyboardPendingKey[this.keyboardLayoutId];
                return d && d[a] && WMKS.BROWSER.isWindows() && (d[a] === b || typeof d[a] == "object" && d[a].indexOf(b) !== -1) && (c = !1), c
            }, this.isMacChromeVscancodeFr = function() {
                return WMKS.BROWSER.isMacOS() && !this._vncDecoder.allowVMWKeyEvent2UnicodeAndRaw && this.keyboardLayoutId == "fr-FR"
            }, this.isAddShiftKey = function(a, b) {
                var c = a.keyCode || a.charCode;
                return c == 167 && this.isMacChromeVscancodeFr()
            }, this.isDeleteShiftKey = function(a, b) {
                var c = a.keyCode || a.charCode;
                return (c == 95 || c == 42 || c == 35) && this.isMacChromeVscancodeFr() && a.shiftKey
            }, this.isAddAltGr = function(a, b) {
                var c = a.keyCode || a.charCode;
                return (c == 64 || c == 35) && this.isMacChromeVscancodeFr()
            }, this.handleUnusefulKeys = function(a) {
                var b = !1,
                    c;
                return WMKS.BROWSER.isIE() && WMKS.BROWSER.version.major > 10 && (c = WMKS.keyboardUtils._keypressUnusefulKeys[this.keyboardLayoutId], !!c && c.indexOf(a.keyCode) !== -1 && (b = !0)), b
            }, this.remapUncontrolKey = function(a, b, c) {
                var d = WMKS.BROWSER.isChrome() && a.altKey && a.ctrlKey,
                    c = !0,
                    e, f = WMKS.keyboardUtils._remapUncontrolKeys[this.keyboardLayoutId];
                return d && (f !== undefined && (e = f[a.keyCode]), e !== undefined && (c = !1, b = e)), {
                    keyCode: b,
                    isRaw: c
                }
            }, this.handleInternationalKeyboard = function(a, b, c) {
                var d = WMKS.BROWSER.isChrome() && a.altKey && a.ctrlKey,
                    e = WMKS.BROWSER.isFirefox() && a.altKey && a.ctrlKey,
                    f = a.keyCode === 0 && WMKS.BROWSER.isFirefox() && a.key && a.charCode === 0,
                    g = WMKS.CONST.KB.WestEuroLanguage.indexOf(this.keyboardLayoutId) !== -1;
                !g && a.keyCode === 0 && WMKS.BROWSER.isFirefox() && a.key && !b && (b = 0);
                switch (this.keyboardLayoutId) {
                    case "ja-JP_106/109":
                        b = this.getRemapPendingKey(b, c);
                        break;
                    case "de-DE":
                        (b == 123 || b == 124 || b == 125) && (d || WMKS.BROWSER.isFirefox) && WMKS.BROWSER.isWindows() && (b == 123 ? b = 124 : b == 124 ? b = 8804 : b == 125 && (b = 8800)), b == 192 && WMKS.BROWSER.isFirefox() && WMKS.BROWSER.isMacOS() && (b = 187), b = this.getRemapPendingKey(b, c);
                        break;
                    case "it-IT":
                        a.keyCode === 37 && d && (b = a.keyCode + 1e3), f && a.key == "#" && WMKS.BROWSER.isWindows() && (b = 35);
                        break;
                    case "es-ES":
                        a.keyCode === 0 && WMKS.BROWSER.isFirefox() && (a.key == " " || a.key == "Dead") && a.charCode == 0 && a.which == 0 && (b = 222), f && a.key == "\\" && WMKS.BROWSER.isWindows() && (b = 186), (a.keyCode === 48 || a.keyCode === 34) && d && (b = a.keyCode + 1e3), b = this.getRemapPendingKey(b, c);
                        break;
                    case "pt-PT":
                        (b == 123 || b == 167) && (d || WMKS.BROWSER.isFirefox) && WMKS.BROWSER.isWindows() && (b == 123 ? b = 55 : b == 167 && (b = 52)), WMKS.BROWSER.isFirefox && WMKS.BROWSER.isWindows() && (a.key == "Â«" && (b = 187), a.key == "Ã€" && (b = 333)), a.keyCode == 192 && WMKS.BROWSER.isChrome && WMKS.BROWSER.isWindows() && (b = 333);
                        break;
                    case "fr-FR":
                        a.keyCode === 29 && WMKS.BROWSER.isChrome() && a.key != "â‚¬" && WMKS.BROWSER.isMacOS() && (b = 124), b = this.getRemapPendingKey(b, c);
                        break;
                    case "fr-CH":
                    case "de-CH":
                        if (a.ctrlKey === !0 && a.altKey === !0) {
                            var h = a.keyCode || a.which;
                            WMKS.BROWSER.isMacOS() && (h === 61 && (b = 187), a.keyCode === 34 && WMKS.BROWSER.isChrome() && (b = 224))
                        }(a.keyCode || a.which) === 96 && (WMKS.BROWSER.isChrome() || WMKS.BROWSER.isFirefox()) && WMKS.BROWSER.isWindows() && a.key === "`" && (b = 94);
                        if (f || WMKS.BROWSER.isMacOS() && e) b = this.getKeyCodeFromKey(a, b) || b;
                        WMKS.BROWSER.isWindows() && e && (b = this.getRemapFrChAltgrKey(b)), a.key === "Â§" && e && WMKS.BROWSER.isMacOS() && (b = 232), a.key === "Ã€" && (a.keyCode || a.which) === 192 && this.keyboardLayoutId === "de-CH" && c !== 65 && (WMKS.BROWSER.isMacOS() || WMKS.BROWSER.isFirefox() && WMKS.BROWSER.isWindows()) && (b = 1224), b = this.getRemapPendingKey(b, c)
                }
                return g && (f || e) && this.keyboardLayoutId !== "fr-CH" && this.keyboardLayoutId !== "de-CH" && (a.charCode === 176 && WMKS.BROWSER.isMacOS() && e ? b = 0 : b = this.getKeyCodeFromKey(a, b) || b), b
            }, this.getRemapFrChAltgrKey = function(a) {
                var b = WMKS.keyboardUtils._remapFrChAltgrKey;
                return b && b[a] && (a = b[a]), a
            }, this.getRemapPendingKey = function(a, b) {
                var c = WMKS.keyboardUtils._remapPending[this.keyboardLayoutId];
                return c && c[a] && c[a] === b && WMKS.BROWSER.isWindows() && (a = b), a
            }, this.getKeyCodeFromKey = function(a, b) {
                var c = WMKS.keyboardUtils._charToKeycode[this.keyboardLayoutId];
                return c && (b = c[a.key]), b
            }, this.onKeyUp = function(a) {
                var b, c, d, e, f = !1;
                return a.preventDefault ? a.preventDefault() : a.returnValue = !1, this.keyDownIdentifier = null, c = this._extractKeyCodeFromEvent(a), c ? (b = c.keyCode, f = c.isUnicode, this._syncModifiers(a), b === 0 ? (WMKS.LOGGER.log("onKeyUp: Do not send 0 to server."), !0) : $.inArray(b, WMKS.CONST.KB.Modifiers) !== -1 ? !1 : (this._handleCapsLockKey(b), f ? (WMKS.LOGGER.log("Sending unicode key up from keyIdentifier: " + b), this.sendKey(b, !0, !0)) : this.keyToUnicodeMap.hasOwnProperty(b) ? (d = this.keyToUnicodeMap[b], this.sendKey(d, !0, !0), delete this.keyToUnicodeMap[b]) : this.keyToRawMap.hasOwnProperty(b) ? (e = this.keyToRawMap[b], this.sendKey(e, !0, !1), delete this.keyToRawMap[b]) : this.sendKey(b, !0, !1), !1)) : (WMKS.LOGGER.debug("Extraction of keyCode from keyUp event failed."), !1)
            }, this.onKeyUpSoftKb = function(a) {
                return a.stopPropagation(), a.preventDefault(), !1
            }, this.onKeyDownSoftKb = function(a) {
                var b = a.keyCode || a.which;
                return b && WMKS.CONST.KB.SoftKBRawKeyCodes.indexOf(b) !== -1 ? (this.handleSoftKb(b, !1), !1) : !0
            }, this.onKeyPressSoftKb = function(a) {
                var b = a.keyCode || a.which;
                return WMKS.BROWSER.isAndroid() && WMKS.BROWSER.isChrome() ? !0 : ($(a.target).val(WMKS.CONST.KB.keyInputDefaultValue), this.handleSoftKb(b, !0), !0)
            }, this.onInputTextSoftKb = function(a) {
                var b = $(a.target),
                    c = b.val(),
                    d = WMKS.CONST.KB.keyInputDefaultValue.length;
                return WMKS.BROWSER.isIOS() ? (b.val(WMKS.CONST.KB.keyInputDefaultValue), !1) : (d > 0 && (c = c.substring(d)), c.length > 1 ? (c = c.charAt(0).toLowerCase() + c.slice(1), this.processInputString(c)) : WMKS.BROWSER.isAndroid() && WMKS.BROWSER.isChrome() && this.processInputString(c), b.val(WMKS.CONST.KB.keyInputDefaultValue), !0)
            }, this.processInputString = function(a, b) {
                var c, d = !1;
                for (c = 0; c < a.length; c++) {
                    if (b && a.charAt(c) === "\n") {
                        this.sendKey(WMKS.CONST.KB.KEY_CODE.Enter, !1, !1), this.sendKey(WMKS.CONST.KB.KEY_CODE.Enter, !0, !1);
                        continue
                    }
                    d = a.charCodeAt(c), isNaN(d) || this.handleSoftKb(d, !0)
                }
                return d
            }, this.handleSoftKb = function(a, b) {
                var c, d;
                c = b && WMKS.CONST.KB.UnicodeWithShift[a], c ? (d = $.inArray(WMKS.CONST.KB.KEY_CODE.Shift, this.activeModifiers) !== -1, !d && !this._isUnicodeInputSupported() && this.sendKey(WMKS.CONST.KB.KEY_CODE.Shift, !1, !1), this.sendKey(a, !1, b), this.sendKey(a, !0, b), !d && !this._isUnicodeInputSupported() ? this.sendKey(WMKS.CONST.KB.KEY_CODE.Shift, !0, !1) : d && this._isUnicodeInputSupported() && this.sendKey(WMKS.CONST.KB.KEY_CODE.Shift, !1, !1)) : (this.sendKey(a, !1, b), this.sendKey(a, !0, b))
            }, this.isBrowserCapsLockOn = function(a, b, c) {
                return !WMKS.BROWSER.isTouchDevice() && b && (WMKS.CONST.KB.UnicodeOnly[a] && c || WMKS.CONST.KB.UnicodeWithShift[a] && !c)
            }, this.sendKey = function(a, b, c) {
                if (!this._vncDecoder.useVMWKeyEvent) return;
                if (!c && this.ignoredRawKeyCodes.indexOf(a) !== -1) return;
                this._vncDecoder.allowVMWKeyEvent2UnicodeAndRaw ? this._vncDecoder.onVMWKeyUnicode(a, !b, !c) : this._sendVScanCode(a, b, c)
            }, this._sendVScanCode = function(a, b, c) {
                var d = null;
                if (c || a === 13) d = this.UnicodeToVScanMap[a];
                if (!d) {
                    var e = WMKS.keyboardUtils._jsToVScanTables[this.keyboardLayoutId];
                    !e || (d = e[a]), d || (d = WMKS.keyboardUtils._jsToVScanTable[a]), d || (d = this.UnicodeToVScanMap[a])
                }
                d ? this._vncDecoder.onKeyVScan(d, !b) : WMKS.LOGGER.debug("unknown key: " + a + (b ? "-up" : "-d"))
            }, this.clearState = function() {
                this.activeModifiers.length = 0, this.sendKey(WMKS.CONST.KB.KEY_CODE.Alt, !0, !1), this.sendKey(WMKS.CONST.KB.KEY_CODE.Ctrl, !0, !1), this.sendKey(WMKS.CONST.KB.KEY_CODE.Shift, !0, !1), WMKS.BROWSER.isMacOS() && this.sendKey(WMKS.CONST.KB.KEY_CODE.Meta, !0, !1)
            }, this.enableWindowsKey = function(a) {
                this._windowsKeyManager.enabled = a
            }, this.setIgnoredRawKeyCodes = function(a) {
                this.ignoredRawKeyCodes = a
            }, this._isUnicodeInputSupported = function() {
                return this._vncDecoder.allowVMWKeyEvent2UnicodeAndRaw
            }
        }, WMKS.CONST.KB.UnicodeOnly = {
            32: 57,
            13: 28,
            97: 30,
            98: 48,
            99: 46,
            100: 32,
            101: 18,
            102: 33,
            103: 34,
            104: 35,
            105: 23,
            106: 36,
            107: 37,
            108: 38,
            109: 50,
            110: 49,
            111: 24,
            112: 25,
            113: 16,
            114: 19,
            115: 31,
            116: 20,
            117: 22,
            118: 47,
            119: 17,
            120: 45,
            121: 21,
            122: 44,
            49: 2,
            50: 3,
            51: 4,
            52: 5,
            53: 6,
            54: 7,
            55: 8,
            56: 9,
            57: 10,
            48: 11,
            59: 39,
            61: 13,
            44: 51,
            45: 12,
            46: 52,
            47: 53,
            96: 41,
            91: 26,
            92: 43,
            93: 27,
            39: 40
        }, WMKS.CONST.KB.UnicodeWithShift = {
            65: 30,
            66: 48,
            67: 46,
            68: 32,
            69: 18,
            70: 33,
            71: 34,
            72: 35,
            73: 23,
            74: 36,
            75: 37,
            76: 38,
            77: 50,
            78: 49,
            79: 24,
            80: 25,
            81: 16,
            82: 19,
            83: 31,
            84: 20,
            85: 22,
            86: 47,
            87: 17,
            88: 45,
            89: 21,
            90: 44,
            33: 2,
            64: 3,
            35: 4,
            36: 5,
            37: 6,
            94: 7,
            38: 8,
            42: 9,
            40: 10,
            41: 11,
            58: 39,
            43: 13,
            60: 51,
            95: 12,
            62: 52,
            63: 53,
            126: 41,
            123: 26,
            124: 43,
            125: 27,
            34: 40
        }, WMKS.CONST.KB.VScanMap["en-US"] = $.extend({}, WMKS.CONST.KB.UnicodeOnly, WMKS.CONST.KB.UnicodeWithShift), WMKS.keyboardUtils = {}, WMKS.keyboardUtils._keyInfoTemplate = {
            jsScanCode: 0,
            vScanCode: 0
        }, WMKS.keyboardUtils.keyDownUpInfo = function(a) {
            var b = a || window.event,
                c = this._keyInfoTemplate;
            if (b.type === "keydown" || b.type === "keyup") c.jsScanCode = b.keyCode, c.vScanCode = this._jsToVScanTable[c.jsScanCode], WMKS.BROWSER.isIE() && WMKS.BROWSER.version.major <= 10 && c.jsScanCode == 13 && (c.vScanCode = 28);
            return c
        }, WMKS.keyboardUtils.keyPressInfo = function(a) {
            var b = a || window.event,
                c = 0;
            if (b.type === "keypress") {
                c = b.which;
                if (c == 8 || c == 9 || c == 27) c = 0
            }
            return c
        }, WMKS.keyboardUtils._jsToVScanTable = {
            9: 15,
            27: 1,
            8: 14,
            16: 42,
            17: 29,
            18: 56,
            20: 58,
            240: 58,
            144: 69,
            37: 331,
            38: 328,
            39: 333,
            40: 336,
            45: 338,
            46: 339,
            36: 327,
            35: 335,
            33: 329,
            34: 337,
            112: 59,
            113: 60,
            114: 61,
            115: 62,
            116: 63,
            117: 64,
            118: 65,
            119: 66,
            120: 67,
            121: 68,
            122: 87,
            123: 88,
            224: 56,
            91: 347,
            92: 348,
            93: 0,
            42: 84,
            19: 256
        }, WMKS.keyboardUtils._jsToVScanTables = {
            "de-DE": {
                220: 41,
                221: 13,
                192: 13,
                192: 41,
                187: 13,
                96: 13
            },
            "es-ES": {
                94: 26,
                96: 26,
                180: 40,
                168: 40,
                192: 26,
                219: 26,
                222: 40,
                186: 26,
                242: 26
            },
            "it-IT": {
                5: 18,
                30: 26,
                29: 27,
                64: 39,
                35: 40,
                91: 26,
                93: 27,
                171: 27,
                109: 39,
                186: 39,
                123: 26,
                125: 27,
                8364: 18
            }
        }, WMKS.keyboardUtils._charToKeycode = {
            "de-DE": {
                "Ã¼": 252,
                "Ãœ": 252,
                "Ã¶": 246,
                "Ã–": 246,
                "Ã¤": 228,
                "Ã„": 228
            },
            "es-ES": {
                "[": 192,
                "]": 43,
                "{": 222,
                "}": 231,
                0: 186,
                '"': 1034,
                "Âº": 186,
                "Âª": 186,
                "Ã§": 231,
                "Ã‡": 231,
                "Ã‘": 209,
                "Ã±": 209,
                "Â¡": 261,
                "Â¿": 161
            },
            "it-IT": {
                "[": 30,
                "{": 30,
                "]": 29,
                "}": 29,
                "@": 1109,
                "%": 1037,
                "Ã ": 1224,
                "Â°": 1224,
                "Ã¨": 232,
                "Ã©": 232,
                "Ã¬": 94,
                "^": 94,
                "Ã²": 242,
                "Ã§": 242,
                "Ã¹": 249,
                "Â§": 249
            },
            "pt-BR": {
                "Ã§": 231,
                "Ã‡": 231
            },
            "pt-PT": {
                "Ã§": 231,
                "Ã‡": 231,
                "Âº": 186,
                "Âª": 186,
                "Â°": 43,
                "Â«": 187,
                "Â»": 187,
                "Ã€": 333
            },
            "fr-FR": {
                "Â²": 178
            },
            "fr-CH": {
                "Â§": 167,
                "Â°": 176,
                "Ã¨": 232,
                "Ã¼": 252,
                "Ã©": 233,
                "Ã¶": 246,
                "Ã ": 1224,
                "Ã¤": 228,
                "[": 232,
                "{": 224,
                '"': 1224
            },
            "de-CH": {
                "Â§": 167,
                "Â°": 176,
                "Ã¨": 232,
                "Ã¼": 252,
                "Ã©": 233,
                "Ã¶": 246,
                "Ã ": 1224,
                "Ã¤": 228,
                "[": 232,
                "{": 224,
                '"': 1224,
                "Ã„": 228,
                "Ã–": 246,
                "Ãœ": 252,
                "Ã€": 1224,
                "Ãˆ": 232,
                "Ã‰": 233
            }
        }, WMKS.keyboardUtils._remapUncontrolKeys = {
            "de-DE": {
                17: 81,
                13: 77
            },
            "es-ES": {
                27: 219
            },
            "it-IT": {
                13: 186
            },
            "pt-PT": {
                27: 168
            },
            "fr-FR": {
                27: 93
            },
            "fr-CH": {
                27: 232
            },
            "de-CH": {
                27: 232
            }
        }, WMKS.keyboardUtils._keypressUnusefulKeys = {
            "de-DE": [180, 94, 96],
            "es-ES": [180, 96]
        }, WMKS.keyboardUtils._SmallKeyboardKey = {
            "de-DE": [47, 42, 61],
            "it-IT": [47, 42, 61],
            "es-ES": [47, 42, 61],
            "ja-JP_106/109": [42, 43],
            "en-US": [42, 43],
            "pt-BR": [42],
            "pt-PT": [42, 47],
            "fr-FR": [43, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57],
            "fr-CH": [42, 43, 47],
            "de-CH": [42, 43, 47]
        }, WMKS.keyboardUtils._smallKeyboardPendingKey = {
            "de-DE": {
                47: 55,
                61: 48
            },
            "fr-FR": {
                43: [187, 61],
                46: [190, 59],
                47: [191, 58],
                48: 48,
                49: 49,
                50: 50,
                51: 51,
                52: 52,
                53: 53,
                54: 54,
                55: 55,
                56: 56,
                57: 57
            }
        }, WMKS.keyboardUtils._WindowsFirefoxAltGr = {
            "pt-PT": [64, 91, 93, 123, 125, 163, 167, 168, 178, 8364],
            "de-DE": [64, 91, 92, 93, 123, 124, 125, 126, 172, 178, 179, 181, 8364],
            "it-IT": [35, 64, 91, 93, 123, 125, 8364],
            "es-ES": [35, 64, 91, 92, 93, 123, 124, 125, 126, 172, 178, 179, 181, 8364],
            "fr-FR": [35, 64, 91, 92, 93, 94, 96, 123, 124, 125, 126, 164, 8364],
            "fr-CH": [35, 64, 91, 92, 93, 123, 124, 125, 126, 162, 166, 172, 167, 176, 219, 8364],
            "de-CH": [35, 64, 91, 92, 93, 123, 124, 125, 126, 162, 166, 172, 167, 176, 8364]
        }, WMKS.keyboardUtils._remapPending = {
            "fr-CH": {
                224: 65,
                232: 69,
                233: 69,
                228: 65,
                246: 79,
                252: 85,
                192: 65,
                219: 85,
                241: 78,
                209: 78,
                176: 52,
                167: 53
            },
            "fr-FR": {
                224: 65,
                232: 69,
                236: 73,
                242: 79,
                249: 85,
                241: 78
            },
            "de-CH": {
                224: 65,
                232: 69,
                233: 69,
                228: 65,
                246: 79,
                252: 85,
                192: 65,
                241: 78,
                209: 78,
                176: 52,
                167: 53,
                196: 65,
                214: 79,
                220: 85,
                200: 69,
                192: 65,
                201: 69,
                219: 85
            },
            "de-DE": {
                193: 65,
                201: 69,
                205: 73,
                211: 79,
                218: 85,
                194: 65,
                202: 69,
                206: 73,
                212: 79,
                219: 85
            },
            "ja-JP_106/109": {
                92: 226
            },
            "es-ES": {
                193: 65,
                201: 69,
                205: 73,
                211: 79,
                218: 85,
                192: 65,
                200: 69,
                204: 73,
                210: 79,
                217: 85,
                241: 78
            }
        }, WMKS.keyboardUtils._remapFrChAltgrKey = {
            91: 180,
            93: 94,
            59: 91,
            94: 93,
            92: 123,
            0: 125
        }, WMKS.keyboardMapper = function(a) {
            return a.vncDecoder ? (this._vncDecoder = a.vncDecoder, this._keysDownVScan = [], this._keysDownUnicode = [], this.VSCAN_CAPS_LOCK_KEY = 58, this.VSCAN_CMD_KEY = 347, this._typematicKeyVScan = 0, this._typematicDelayTimer = null, this) : null
        }, WMKS.keyboardMapper.prototype.doKeyVScan = function(a, b) {
            if (!this._vncDecoder.useVMWKeyEvent) return;
            if (a === this.VSCAN_CAPS_LOCK_KEY && navigator.platform.indexOf("Mac") !== -1) {
                this._vncDecoder.onKeyVScan(a, 1), this._vncDecoder.onKeyVScan(a, 0);
                return
            }
            if (b) this._keysDownVScan.indexOf(a) <= -1 && this._keysDownVScan.push(a), this.beginTypematic(a);
            else {
                this.cancelTypematic(a);
                var c = this._keysDownVScan.indexOf(a);
                c >= 0 && this._keysDownVScan.splice(c, 1)
            }
            this._vncDecoder.onKeyVScan(a, b)
        }, WMKS.keyboardMapper.prototype.doKeyUnicode = function(a, b) {
            if (!this._vncDecoder.useVMWKeyEvent) return;
            if (b) this._keysDownUnicode.push(a);
            else {
                var c = this._keysDownUnicode.indexOf(a);
                c >= 0 && this._keysDownUnicode.splice(c, 1)
            }
            var d = this._tableUnicodeToVScan[a];
            d && (b ? this.beginTypematic(d & 511) : this.cancelTypematic(d & 511), this._vncDecoder.onKeyVScan(d & 511, b))
        }, WMKS.keyboardMapper.prototype.doReleaseAll = function() {
            var a;
            for (a = 0; a < this._keysDownUnicode.length; a++) this.doKeyUnicode(this._keysDownUnicode[a], 0);
            this._keysDownUnicode.length > 0 && console.log("Warning: Could not release all Unicode keys.");
            for (a = 0; a < this._keysDownVScan.length; a++) this.cancelTypematic(this._keysDownVScan[a]), this._vncDecoder.onKeyVScan(this._keysDownVScan[a], 0);
            this._keysDownVScan = []
        }, WMKS.keyboardMapper.prototype.beginTypematic = function(a) {
            if (this._keysDownVScan.indexOf(this.VSCAN_CMD_KEY) >= 0) return;
            this.cancelTypematicDelay(), this.cancelTypematicPeriod(), this._vncDecoder.typematicState === 1 && this.startTypematicDelay(a)
        }, WMKS.keyboardMapper.prototype.cancelTypematic = function(a) {
            this._typematicKeyVScan === a && (this.cancelTypematicDelay(), this.cancelTypematicPeriod())
        }, WMKS.keyboardMapper.prototype.cancelTypematicDelay = function() {
            this._typematicDelayTimer !== null && clearTimeout(this._typematicDelayTimer), this._typematicDelayTimer = null
        }, WMKS.keyboardMapper.prototype.cancelTypematicPeriod = function() {
            this._typematicPeriodTimer !== null && clearInterval(this._typematicPeriodTimer), this._typematicPeriodTimer = null
        }, WMKS.keyboardMapper.prototype.startTypematicDelay = function(a) {
            var b = this;
            this._typematicKeyVScan = a, this._typematicDelayTimer = setTimeout(function() {
                b._typematicPeriodTimer = setInterval(function() {
                    b._vncDecoder.onKeyVScan(b._typematicKeyVScan, 1)
                }, b._vncDecoder.typematicPeriod / 1e3)
            }, this._vncDecoder.typematicDelay / 1e3)
        }, WMKS.keyboardMapper.prototype._tableUnicodeToVScan = {
            32: 57,
            13: 28,
            97: 30,
            98: 48,
            99: 46,
            100: 32,
            101: 18,
            102: 33,
            103: 34,
            104: 35,
            105: 23,
            106: 36,
            107: 37,
            108: 38,
            109: 50,
            110: 49,
            111: 24,
            112: 25,
            113: 16,
            114: 19,
            115: 31,
            116: 20,
            117: 22,
            118: 47,
            119: 17,
            120: 45,
            121: 21,
            122: 44,
            49: 2,
            50: 3,
            51: 4,
            52: 5,
            53: 6,
            54: 7,
            55: 8,
            56: 9,
            57: 10,
            48: 11,
            59: 39,
            61: 13,
            44: 51,
            45: 12,
            46: 52,
            47: 53,
            96: 41,
            91: 26,
            92: 43,
            93: 27,
            39: 40,
            65: 30,
            66: 48,
            67: 46,
            68: 32,
            69: 18,
            70: 33,
            71: 34,
            72: 35,
            73: 23,
            74: 36,
            75: 37,
            76: 38,
            77: 50,
            78: 49,
            79: 24,
            80: 25,
            81: 16,
            82: 19,
            83: 31,
            84: 20,
            85: 22,
            86: 47,
            87: 17,
            88: 45,
            89: 21,
            90: 44,
            33: 2,
            64: 3,
            35: 4,
            36: 5,
            37: 6,
            94: 7,
            38: 8,
            42: 9,
            40: 10,
            41: 11,
            58: 39,
            43: 13,
            60: 51,
            95: 12,
            62: 52,
            63: 53,
            126: 41,
            123: 26,
            124: 43,
            125: 27,
            34: 40
        }, WMKS.CONST.TOUCH = {
            FEATURE: {
                SoftKeyboard: 0,
                ExtendedKeypad: 1,
                Trackpad: 2
            },
            tapMoveCorrectionDistancePx: 10,
            additionalTouchIgnoreGapMs: 1200,
            touchMoveSampleMinCount: 2,
            minKeyboardToggleTime: 50,
            leftDragDelayMs: 300,
            OP: {
                none: "none",
                scroll: "scroll",
                drag: "drag",
                move: "move",
                tap_twice: "double-click",
                tap_1finger: "click",
                tap_3finger: "tap-3f"
            },
            SCROLL: {
                minDeltaDistancePx: 20
            },
            DOUBLE_TAP: {
                tapGapInTime: 250,
                tapGapBonusTime: 200,
                tapGapBonus4TimeRatio: .4,
                tapGapInDistance: 40
            }
        }, WMKS.TouchHandler = function(a) {
            "use strict";
            if (!a || !a.canvas || !a.widgetProto || !a.keyboardManager) return WMKS.LOGGER.warn("Invalid params set for TouchHandler."), null;
            var b = a.widgetProto,
                c = a.keyboardManager,
                d = {
                    visible: !1,
                    lastToggleTime: 0
                },
                e = [],
                f = a.canvas,
                g = a.onToggle,
                h = null,
                i = {
                    currentTouchFingers: -1,
                    firstTouch: null,
                    currentTouch: null,
                    touchArray: [],
                    tapStartTime: null,
                    touchMoveCount: 0,
                    skipScrollCount: 0,
                    scrollCount: 0,
                    zoomCount: 0,
                    opType: WMKS.CONST.TOUCH.OP.none
                },
                j = {
                    inputProxy: null,
                    cursorIcon: null,
                    clickFeedback: null,
                    dragFeedback: null,
                    pulseFeedback: null,
                    scrollFeedback: null,
                    keypad: null,
                    trackpad: null
                };
            this._verifyQuickTouches = function(a, b, c) {
                return i.opType === WMKS.CONST.TOUCH.OP.none && b > 50 && c === 1 ? (WMKS.LOGGER.debug("Special case - touchmove#: " + c + ", targetTouches#: " + a.targetTouches.length + ", dist: " + b + ", scale: " + a.scale), !0) : !1
            }, this._initDragEventAndSendFeedback = function(a) {
                if (i.opType === WMKS.CONST.TOUCH.OP.drag) {
                    var c = this._applyZoomCorrectionToTouchXY(a);
                    b.sendMouseButtonMessage(c, !0, WMKS.CONST.CLICK.left), this._showFeedback(j.dragFeedback, a)
                }
            }, this._initTwoFingerTouch = function(a, b) {
                i.opType === WMKS.CONST.TOUCH.OP.none && (i.currentTouchFingers = 2, i.touchArray.push(a), i.touchArray.push(b), i.firstTouch = WMKS.UTIL.TOUCH.copyTouch(WMKS.UTIL.TOUCH.leftmostOf(a, b)), i.currentTouch = WMKS.UTIL.TOUCH.copyTouch(i.firstTouch))
            }, this._sendScrollEventMessage = function(a) {
                var c = 0,
                    d = 0,
                    e, f, g, h;
                if (i.opType === WMKS.CONST.TOUCH.OP.scroll) {
                    e = a.clientX - i.currentTouch.clientX, f = a.clientY - i.currentTouch.clientY, g = this._calculateMouseWheelDeltas(e, f), c = g.wheelDeltaX, d = g.wheelDeltaY;
                    if (c !== 0 || d !== 0) h = this._applyZoomCorrectionToTouchXY(i.touchArray[0]), b.sendScrollMessage(h, c, d), c !== 0 && (i.currentTouch.clientX = a.clientX), d !== 0 && (i.currentTouch.clientY = a.clientY)
                }
            }, this._calculateMouseWheelDeltas = function(a, c) {
                var d = 0,
                    e = 0,
                    f = Math.abs(a),
                    g = Math.abs(c),
                    h = f > WMKS.CONST.TOUCH.SCROLL.minDeltaDistancePx,
                    i = g > WMKS.CONST.TOUCH.SCROLL.minDeltaDistancePx,
                    j;
                return h && i && (g < f ? i = !1 : h = !1), h && (d = a > 0 ? 1 : -1), i && (e = c > 0 ? -1 : 1), b.options.reverseScrollY && (e *= -1), {
                    wheelDeltaX: d,
                    wheelDeltaY: e
                }
            }, this._updatePreScrollState = function(a) {
                var b = a.clientY - i.currentTouch.clientY;
                i.scrollCount++, b < 0 ? i.skipScrollCount-- : i.skipScrollCount++
            }, this._sendResidualScrollEventMessage = function() {
                if (i.skipScrollCount !== 0 && i.currentTouch) {
                    var a, c;
                    c = i.skipScrollCount < 0 ? -1 : 1, WMKS.LOGGER.debug("Sending a residual scroll message."), WMKS.LOGGER.debug("Cur touch: " + i.currentTouch.pageX + " , " + i.currentTouch.pageY), i.skipScrollCount = 0, a = this._applyZoomCorrectionToTouchXY(i.currentTouch), b.sendScrollMessage(a, c, 0)
                }
            }, this._isDoubleTap = function(a, b) {
                var c, d;
                if (i.currentTouch === null || i.tapStartTime === null || i.opType !== WMKS.CONST.TOUCH.OP.none) return !1;
                c = WMKS.UTIL.TOUCH.touchDistance(i.currentTouch, a.targetTouches[0]), d = b - i.tapStartTime;
                if (c < WMKS.CONST.TOUCH.DOUBLE_TAP.tapGapInDistance) {
                    if (d < WMKS.CONST.TOUCH.DOUBLE_TAP.tapGapInTime) return !0;
                    if (c / WMKS.CONST.TOUCH.DOUBLE_TAP.tapGapInDistance < WMKS.CONST.TOUCH.DOUBLE_TAP.tapGapBonus4TimeRatio && d < WMKS.CONST.TOUCH.DOUBLE_TAP.tapGapInTime + WMKS.CONST.TOUCH.DOUBLE_TAP.tapGapBonusTime) return !0
                }
                return !1
            }, this._onTouchStart = function(a) {
                var c, d, e = this,
                    f = $.now();
                if (a.targetTouches.length === 1) return this._isDoubleTap(a, f) ? (i.firstTouch = WMKS.UTIL.TOUCH.copyTouch(i.currentTouch), i.opType = WMKS.CONST.TOUCH.OP.tap_twice) : (i.firstTouch = WMKS.UTIL.TOUCH.copyTouch(a.targetTouches[0]), i.currentTouch = WMKS.UTIL.TOUCH.copyTouch(a.targetTouches[0])), i.currentTouchFingers = 1, i.tapStartTime = f, h !== null && clearTimeout(h), h = setTimeout(function() {
                    h = null, i.opType = WMKS.CONST.TOUCH.OP.drag, e._initDragEventAndSendFeedback(i.firstTouch)
                }, WMKS.CONST.TOUCH.leftDragDelayMs), !0;
                if (a.targetTouches.length === 2) return i.currentTouchFingers === 1 && (d = f - i.tapStartTime, d > WMKS.CONST.TOUCH.additionalTouchIgnoreGapMs && i.opType === WMKS.CONST.TOUCH.OP.drag && (c = this._applyZoomCorrectionToTouchXY(a.targetTouches[0]), b.sendMouseButtonMessage(c, !0, WMKS.CONST.CLICK.left), this._resetTouchState())), this._initTwoFingerTouch(WMKS.UTIL.TOUCH.copyTouch(a.targetTouches[0]), WMKS.UTIL.TOUCH.copyTouch(a.targetTouches[1])), !0;
                if (a.targetTouches.length === 3) return i.opType === WMKS.CONST.TOUCH.OP.none && (i.opType = WMKS.CONST.TOUCH.OP.tap_3finger, this.toggleKeyboard(), i.currentTouchFingers = 3), !1
            }, this._onTouchMove = function(a) {
                var c, d;
                h !== null && (clearTimeout(h), h = null), i.touchMoveCount++;
                if (i.currentTouchFingers === -1) return !0;
                if (i.opType === WMKS.CONST.TOUCH.OP.scroll) return this._sendScrollEventMessage(a.targetTouches[0]), !1;
                if (i.opType === WMKS.CONST.TOUCH.OP.drag) return i.currentTouch = WMKS.UTIL.TOUCH.copyTouch(a.targetTouches[0]), this.moveCursor(a.targetTouches[0].pageX, a.targetTouches[0].pageY), d = this._applyZoomCorrectionToTouchXY(a.targetTouches[0]), b.sendMouseMoveMessage(d), !1;
                if (i.opType === WMKS.CONST.TOUCH.OP.tap_3finger) return !1;
                if (i.currentTouchFingers !== a.targetTouches.length) {
                    if (i.currentTouchFingers !== 2 || a.targetTouches.length !== 1) return i.currentTouchFingers === 1 && a.targetTouches.length === 2 ? (WMKS.LOGGER.debug("touch: 1 -> 2, init 2fingertap if no opType: " + i.opType), this._initTwoFingerTouch(WMKS.UTIL.TOUCH.copyTouch(a.targetTouches[0]), WMKS.UTIL.TOUCH.copyTouch(a.targetTouches[1])), !0) : (WMKS.LOGGER.debug("touch: 2 -> 1: infer as PINCH/ZOOM."), this._resetTouchState(), !0);
                    if (i.opType === WMKS.CONST.TOUCH.OP.none && a.scale === 1) return WMKS.LOGGER.debug("touch: 2 -> 1 & !scroll, hence right-click."), this._sendTwoTouchEvent(i.firstTouch, i.firstTouch, WMKS.CONST.CLICK.right, a), this._resetTouchState(), !1
                } else {
                    if (i.currentTouchFingers === 1) return c = WMKS.UTIL.TOUCH.touchDistance(a.targetTouches[0], i.currentTouch), this._verifyQuickTouches(a, c, i.touchMoveCount) ? (this._initTwoFingerTouch(WMKS.UTIL.TOUCH.copyTouch(i.firstTouch), WMKS.UTIL.TOUCH.copyTouch(a.targetTouches[0])), i.opType = WMKS.CONST.TOUCH.OP.scroll, !1) : c < WMKS.CONST.TOUCH.tapMoveCorrectionDistancePx ? !0 : (this._resetTouchState(), !0);
                    if (i.currentTouchFingers === 2 && i.opType === WMKS.CONST.TOUCH.OP.none) {
                        if (i.touchArray.length === 0 || i.touchArray.length !== 2) return this._resetTouchState(), !0;
                        if (a.scale === 1 && i.touchMoveCount < 5) return !0;
                        var e = WMKS.UTIL.TOUCH.touchAngleBwLines(i.touchArray[0], a.targetTouches[0], i.touchArray[1], a.targetTouches[1]);
                        e = Math.abs(e);
                        if (e === 0) return !0;
                        if (e < 1 || e > 5.2) {
                            this._updatePreScrollState(a.targetTouches[0]);
                            if (i.scrollCount >= WMKS.CONST.TOUCH.touchMoveSampleMinCount) return this._showFeedback(j.scrollFeedback, i.firstTouch, {
                                position: "left",
                                offsetLeft: -50,
                                offsetTop: -25
                            }), i.opType = WMKS.CONST.TOUCH.OP.scroll, i.currentTouch = WMKS.UTIL.TOUCH.copyTouch(a.targetTouches[0]), !1
                        } else {
                            i.zoomCount++;
                            if (i.zoomCount >= WMKS.CONST.TOUCH.touchMoveSampleMinCount) return this._resetTouchState(), !0
                        }
                        return !0
                    }
                }
                return !0
            }, this._onTouchEnd = function(a) {
                var c, d;
                h !== null && (clearTimeout(h), h = null);
                if (i.currentTouchFingers === -1) return !0;
                if (a.targetTouches.length === 0) {
                    i.skipScrollCount !== 0 && (i.opType = WMKS.CONST.TOUCH.OP.scroll);
                    switch (i.opType) {
                        case WMKS.CONST.TOUCH.OP.scroll:
                            return this._sendResidualScrollEventMessage(a), this._resetTouchState(), !1;
                        case WMKS.CONST.TOUCH.OP.tap_twice:
                            return this._sendTwoTouchEvent(i.firstTouch, i.currentTouch, WMKS.CONST.CLICK.left, a), this._resetTouchState(), !1;
                        case WMKS.CONST.TOUCH.OP.tap_3finger:
                            return this._resetTouchState(), !1;
                        case WMKS.CONST.TOUCH.OP.drag:
                            return d = a.changedTouches, d.length === 1 ? (c = this._applyZoomCorrectionToTouchXY(d[0]), b.sendMouseButtonMessage(c, !1, WMKS.CONST.CLICK.left)) : WMKS.LOGGER.warn("Unexpected touch# " + d.length + " changed in a drag operation!"), this._resetTouchState(), !1;
                        default:
                            if (i.currentTouchFingers === 1) return this._sendTwoTouchEvent(i.firstTouch, i.currentTouch, WMKS.CONST.CLICK.left, a), this._resetTouchState(!0), !1;
                            if (i.currentTouchFingers === 2) return this._sendTwoTouchEvent(i.firstTouch, i.firstTouch, WMKS.CONST.CLICK.right, a), this._resetTouchState(), !1
                    }
                    return this._resetTouchState(), !1
                }
            }, this._resetTouchState = function(a) {
                a || (i.tapStartTime = null, i.currentTouch = null), i.currentTouchFingers = -1, i.opType = WMKS.CONST.TOUCH.OP.none, i.firstTouch = null, i.touchArray.length = 0, i.touchMoveCount = 0, i.skipScrollCount = 0, i.scrollCount = 0, i.zoomCount = 0
            }, this._sendTwoTouchEvent = function(a, c, d) {
                var e = this._applyZoomCorrectionToTouchXY(a);
                return b.sendMouseButtonMessage(e, !0, d), i.opType === WMKS.CONST.TOUCH.OP.tap_twice ? (b.sendMouseButtonMessage(e, !1, d), this._showFeedback(j.clickFeedback, a, {
                    showTwice: !0
                })) : (e = this._applyZoomCorrectionToTouchXY(c), b.sendMouseButtonMessage(e, !1, d), this._showFeedback(j.clickFeedback, a)), !0
            }, this.addToRepositionQueue = function(a) {
                a && e.push(a)
            }, this.widgetRepositionOnRotation = function(a) {
                var b, c, d, e, f, g = !1;
                return WMKS.BROWSER.isTouchDevice() ? !a || a.is(":hidden") ? !1 : (b = a.width(), c = a.height(), e = window.innerWidth, f = window.innerHeight, WMKS.UTIL.TOUCH.isPortraitOrientation() ? a.offset().left + b > e && (a.offset({
                    left: String(e - b - 5)
                }), g = !0) : a.offset().top + c > f && (a.offset({
                    top: String(f - c - 5)
                }), g = !0), g) : (WMKS.LOGGER.warn("Widget reposition ignored, this is not a touch device."), !1)
            }, this._repositionFloatingElementsOnRotation = function(a) {
                var b = this,
                    c = f.offset();
                this.widgetRepositionOnRotation(j.inputProxy), this.widgetRepositionOnRotation(j.cursorIcon), j.clickFeedback.offset(c), j.dragFeedback.offset(c), j.pulseFeedback.offset(c), j.scrollFeedback.offset(c), $.each(e, function(a, c) {
                    try {
                        b.widgetRepositionOnRotation(c)
                    } catch (d) {
                        WMKS.LOGGER.warn("Custom element reposition failed: " + d)
                    }
                })
            }, this._onOrientationChange = function(a) {
                var b = this;
                this._isInputInFocus() && $(window).one("resize", function(a) {
                    setTimeout(function() {
                        $(window).trigger("orientationchange"), b._repositionFloatingElementsOnRotation()
                    }, 500)
                })
            }, this._applyZoomCorrectionToTouchXY = function(a) {
                return a === null ? (WMKS.LOGGER.warn("Unexpected: touch is null."), null) : b.getEventPosition(a)
            }, this._showFeedback = function(a, b, c) {
                var d, e, f, g = c || {};
                if (!b || !a) {
                    WMKS.LOGGER.trace("No touch value / feedback object, skip feedback.");
                    return
                }
                e = g.offsetLeft || 0, f = g.offsetTop || 0, d = WMKS.UTIL.TOUCH.getRelativePositionMultiplier(g.position), a.css({
                    left: b.pageX + e + a.outerWidth() * d.width,
                    top: b.pageY + f + a.outerHeight() * d.height
                }), this.moveCursor(b.pageX, b.pageY), a.removeClass("animate-feedback-indicator animate-double-feedback-indicator"), g.showTwice ? setTimeout(function() {
                    a.addClass("animate-double-feedback-indicator")
                }, 0) : setTimeout(function() {
                    a.addClass("animate-feedback-indicator")
                }, 0)
            }, this.moveCursor = function(a, b) {
                j.cursorIcon && j.cursorIcon.css({
                    left: a,
                    top: b
                })
            }, this.setCursorVisibility = function(a) {
                j.cursorIcon && (a ? j.cursorIcon.show() : j.cursorIcon.hide())
            }, this._sendKeyInput = function(a) {
                b.sendKeyInput(a)
            }, this.onCaretPositionChanged = function(a) {
                var b, c;
                j.inputProxy && (b = a.x, c = a.y, b < window.pageXOffset && (b = window.pageXOffset), c < window.pageYOffset && (c = window.pageYOffset), j.inputProxy.offset({
                    left: b,
                    top: c
                }))
            }, this._keyboardDisplay = function(a) {
                a ? (f.focus(), j.inputProxy.focus().select()) : (WMKS.BROWSER.isAndroid() && (j.inputProxy.attr("readonly", !0).attr("disabled", !0), setTimeout(function() {
                    j && (j.inputProxy.attr("readonly", !1).attr("disabled", !1), f.focus())
                }, 100)), document.activeElement.blur(), d.visible = !1)
            }, this._isInputInFocus = function() {
                return document.activeElement.id === "input-proxy"
            }, this._onInputFocus = function(a) {
                return this._sendUpdatedKeyboardState(!0), a.stopPropagation(), !0
            }, this._onInputBlur = function(a) {
                return this._sendUpdatedKeyboardState(!1), a.stopPropagation(), !0
            }, this._sendUpdatedKeyboardState = function(a) {
                d.visible = a, d.lastToggleTime = $.now(), $.isFunction(g) && g.call(this, ["KEYBOARD", d.visible])
            }, this.toggleKeyboard = function(a) {
                if (!WMKS.BROWSER.isTouchDevice()) {
                    WMKS.LOGGER.warn("Mobile keyboard not supported, this is not a touch device.");
                    return
                }
                if (!j.inputProxy) return;
                if (!!a && a.show === d.visible) return;
                if ($.now() - d.lastToggleTime < WMKS.CONST.TOUCH.minKeyboardToggleTime) {
                    WMKS.LOGGER.warn("Ignore kb toggle - Got request soon after focus/blur.");
                    return
                }
                this._keyboardDisplay(!d.visible)
            }, this.toggleTrackpad = function(a) {
                if (!WMKS.BROWSER.isTouchDevice()) {
                    WMKS.LOGGER.warn("Trackpad not supported. Not a touch device.");
                    return
                }
                j.trackpad && (a = $.extend({}, a, {
                    toggleCallback: g
                }), j.trackpad.toggle(a))
            }, this.toggleExtendedKeypad = function(a) {
                if (!WMKS.BROWSER.isTouchDevice()) {
                    WMKS.LOGGER.warn("Extended keypad not supported. Not a touch device.");
                    return
                }
                j.keypad && (a = $.extend({}, a, {
                    toggleCallback: g
                }), j.keypad.toggle(a))
            }, this.installTouchHandlers = function() {
                var a = this,
                    b = f.parent();
                if (!WMKS.BROWSER.isTouchDevice()) {
                    WMKS.LOGGER.log("Not a touch device, and hence skip touch handler");
                    return
                }
                f.css({
                    "-webkit-user-select": "none",
                    "-webkit-touch-callout": "none"
                }), f.bind("touchmove.wmks", function(b) {
                    return a._onTouchMove(b.originalEvent)
                }).bind("touchstart.wmks", function(b) {
                    return a._onTouchStart(b.originalEvent)
                }).bind("touchend.wmks", function(b) {
                    return a._onTouchEnd(b.originalEvent)
                }).bind("orientationchange.wmks", function(b) {
                    return a._onOrientationChange(b)
                }).bind("orientationchange.wmks.elements", function(b) {
                    a._repositionFloatingElementsOnRotation(b)
                }), j.cursorIcon = $("<div/>").addClass("feedback-container cursor-icon").appendTo(b), j.clickFeedback = $("<div/>").addClass("feedback-container tap-icon").appendTo(b), j.dragFeedback = $("<div/>").addClass("feedback-container drag-icon").appendTo(b), j.pulseFeedback = $("<div/>").addClass("feedback-container pulse-icon").appendTo(b), j.scrollFeedback = $("<div/>").addClass("feedback-container scroll-icon").appendTo(b), b.find(".feedback-container").bind("touchmove.wmks", function(b) {
                    return a._onTouchMove(b.originalEvent)
                }).bind("touchstart.wmks", function(b) {
                    return a._onTouchStart(b.originalEvent)
                }).bind("touchend.wmks", function(b) {
                    return a._onTouchEnd(b.originalEvent)
                })
            }, this.disconnectEvents = function() {
                if (!f) return;
                f.unbind("orientationchange.wmks.icons").unbind("orientationchange.wmks").unbind("touchmove.wmks").unbind("touchstart.wmks").unbind("touchend.wmks"), f.find(".feedback-container").unbind("touchmove.wmks").unbind("touchstart.wmks").unbind("touchend.wmks")
            }, this.initializeMobileFeature = function(a) {
                if (!WMKS.BROWSER.isTouchDevice()) return;
                switch (a) {
                    case WMKS.CONST.TOUCH.FEATURE.Trackpad:
                        j.trackpad = new WMKS.trackpadManager(b, f), j.trackpad.initialize();
                        break;
                    case WMKS.CONST.TOUCH.FEATURE.ExtendedKeypad:
                        j.keypad = new WMKS.extendedKeypad({
                            widget: b,
                            parentElement: f.parent(),
                            keyboardManager: c
                        }), j.keypad.initialize();
                        break;
                    case WMKS.CONST.TOUCH.FEATURE.SoftKeyboard:
                        j.inputProxy = this.initSoftKeyboard();
                        break;
                    default:
                        WMKS.LOGGER.error("Invalid mobile feature type: " + a)
                }
            }, this.initSoftKeyboard = function() {
                var a = this,
                    b = c,
                    d = $('<input type="text"/>').val(WMKS.CONST.KB.keyInputDefaultValue).attr({
                        id: "input-proxy",
                        autocorrect: "off",
                        autocapitalize: "off"
                    }).css({
                        "font-size": "1px",
                        width: "1px",
                        height: "1px",
                        "background-color": "transparent",
                        color: "transparent",
                        "box-shadow": 0,
                        outline: "none",
                        border: 0,
                        padding: 0,
                        left: -1,
                        top: -1,
                        overflow: "hidden",
                        position: "absolute"
                    }).bind("blur", function(b) {
                        return a._onInputBlur(b)
                    }).bind("focus", function(b) {
                        return a._onInputFocus(b)
                    }).bind("input", function(a) {
                        return b.onInputTextSoftKb(a)
                    }).bind("keydown", function(a) {
                        return b.onKeyDownSoftKb(a)
                    }).bind("keyup", function(a) {
                        return b.onKeyUpSoftKb(a)
                    }).bind("keypress", function(a) {
                        return b.onKeyPressSoftKb(a)
                    }).insertBefore(f.parent());
                return WMKS.BROWSER.isIOS() && d.css({
                    "-webkit-touch-callout": "none"
                }), d
            }, this.removeMobileFeature = function(a) {
                switch (a) {
                    case WMKS.CONST.TOUCH.FEATURE.Trackpad:
                        j.trackpad && (j.trackpad.destroy(), j.trackpad = null);
                        break;
                    case WMKS.CONST.TOUCH.FEATURE.ExtendedKeypad:
                        j.keypad && (j.keypad.destroy(), j.keypad = null);
                        break;
                    case WMKS.CONST.TOUCH.FEATURE.SoftKeyboard:
                        j.inputProxy && (d.visible && this.toggleKeyboard(!1), j.inputProxy.remove(), j.inputProxy = null);
                        break;
                    default:
                        WMKS.LOGGER.error("Invalid mobile feature type: " + a)
                }
            }, this.destroy = function() {
                this.disconnectEvents(), this.removeMobileFeature(WMKS.CONST.TOUCH.FEATURE.SoftKeyboard), this.removeMobileFeature(WMKS.CONST.TOUCH.FEATURE.ExtendedKeypad), this.removeMobileFeature(WMKS.CONST.TOUCH.FEATURE.Trackpad), b = null, f = null, c = null, i = null, j = null, e.length = 0, e = null
            }
        }, WMKS.UTIL.TOUCH = {
            isLandscapeOrientation: function() {
                return window.orientation === 90 || window.orientation === -90
            },
            isPortraitOrientation: function() {
                return window.orientation === 0 || window.orientation === 180
            },
            getRelativePositionMultiplier: function(a) {
                var b = -0.5,
                    c = -0.5;
                return !a || (a.indexOf("left") !== -1 ? b = -1 : a.indexOf("right") !== -1 && (b = 1), a.indexOf("top") !== -1 ? c = -1 : a.indexOf("bottom") !== -1 && (c = 1)), {
                    width: b,
                    height: c
                }
            },
            touchEqual: function(a, b) {
                return a.screenX === b.screenX && a.screenY === b.screenY
            },
            touchDistance: function(a, b) {
                return WMKS.UTIL.getLineLength(b.screenX - a.screenX, b.screenY - a.screenY)
            },
            touchAngleBwLines: function(a, b, c, d) {
                var e = Math.atan2(a.screenY - b.screenY, a.screenX - b.screenX),
                    f = Math.atan2(c.screenY - d.screenY, c.screenX - d.screenX);
                return e - f
            },
            copyTouch: function(a) {
                var b = {
                    screenX: a.screenX,
                    screenY: a.screenY,
                    clientX: a.clientX,
                    clientY: a.clientY,
                    pageX: a.pageX,
                    pageY: a.pageY
                };
                return b
            },
            leftmostOf: function(a, b) {
                return a.screenX < b.screenX ? a : b
            }
        }, WMKS.widgetProto = {}, WMKS.widgetProto.options = {
            fitToParent: !1,
            fitGuest: !1,
            useNativePixels: !1,
            allowMobileKeyboardInput: !0,
            useUnicodeKeyboardInput: !1,
            useVNCHandshake: !0,
            VCDProxyHandshakeVmxPath: null,
            reverseScrollY: !1,
            allowMobileExtendedKeypad: !0,
            allowMobileTrackpad: !0,
            enableVorbisAudioClips: !1,
            enableOpusAudioClips: !1,
            enableAacAudioClips: !1,
            enableVMWAudioMixer: !1,
            enableVVC: !0,
            enableMP4: !1,
            enableRawH264: !1,
            enableTopologyChange: !1,
            enableH264Multimon: !1,
            enableUint8Utf8: !1,
            retryConnectionInterval: -1,
            ignoredRawKeyCodes: [],
            fixANSIEquivalentKeys: !1,
            mapMetaToCtrlForKeys: [],
            enableWindowsKey: !1,
            keyboardLayoutId: "en-US",
            sendRelativeMouseEvent: !1
        }, WMKS.widgetProto._updatePixelRatio = function() {
            this.options.useNativePixels ? this._pixelRatio = window.devicePixelRatio || 1 : this._pixelRatio = 1
        }, WMKS.widgetProto._updateMobileFeature = function(a, b) {
            a ? this._touchHandler.initializeMobileFeature(b) : this._touchHandler.removeMobileFeature(b)
        }, WMKS.widgetProto._setOption = function(a, b) {
            $.Widget.prototype._setOption.apply(this, arguments);
            switch (a) {
                case "fitToParent":
                    this.rescaleOrResize(!1);
                    break;
                case "fitGuest":
                    this.rescaleOrResize(!0);
                    break;
                case "useNativePixels":
                    if (b && !WMKS.UTIL.isHighResolutionSupported()) {
                        WMKS.LOGGER.warn("Browser/device does not support this feature.");
                        return
                    }
                    this._updatePixelRatio(), this.options.fitGuest ? this.updateFitGuestSize(!0) : this.rescaleOrResize(!1);
                    break;
                case "allowMobileKeyboardInput":
                    this._updateMobileFeature(b, WMKS.CONST.TOUCH.FEATURE.SoftKeyboard);
                    break;
                case "allowMobileTrackpad":
                    this._updateMobileFeature(b, WMKS.CONST.TOUCH.FEATURE.Trackpad);
                    break;
                case "allowMobileExtendedKeypad":
                    this._updateMobileFeature(b, WMKS.CONST.TOUCH.FEATURE.ExtendedKeypad);
                    break;
                case "reverseScrollY":
                    this.options.reverseScrollY = b;
                    break;
                case "fixANSIEquivalentKeys":
                    this._keyboardManager.fixANSIEquivalentKeys = b;
                    break;
                case "VCDProxyHandshakeVmxPath":
                    this.setVCDProxyHandshakeVmxPath(b);
                    break;
                case "mapMetaToCtrlForKeys":
                    this._keyboardManager.mapMetaToCtrlForKeys = b;
                case "enableWindowsKey":
                    this._keyboardManager.enableWindowsKey(b);
                    break;
                case "keyboardLayoutId":
                    this._keyboardManager.keyboardLayoutId = b, this._keyboardManager.UnicodeToVScanMap = WMKS.CONST.KB.VScanMap[b];
                    break;
                case "ignoredRawKeyCodes":
                    this._keyboardManager.setIgnoredRawKeyCodes(b);
                    break;
                case "sendRelativeMouseEvent":
                    this._vncDecoder.options.sendRelativeMouseEvent = b
            }
        }, WMKS.widgetProto.getCanvasPosition = function(a, b) {
            var c, d, e, f;
            if (isNaN(a) || isNaN(b)) return {
                x: 0,
                y: 0
            };
            c = this._canvas.offset(), d = this._pixelRatio / this._scale;
            var g = Math.ceil((a - c.left) * d),
                h = Math.ceil((b - c.top) * d);
            return this.options.useNativePixels ? (e = Math.ceil(this._canvas.width() * d) - 1, f = Math.ceil(this._canvas.height() * d) - 1) : (e = Math.ceil(this._canvas.width()) - 1, f = Math.ceil(this._canvas.height()) - 1), g = Math.min(g, e), h = Math.min(h, f), g = Math.max(g, 0), h = Math.max(h, 0), {
                x: g,
                y: h
            }
        }, WMKS.widgetProto.getRelativeMouseCanvasPosition = function(a) {
            var b, c, d, e = a.x,
                f = a.y;
            if (isNaN(e) || isNaN(f)) return {
                x: 0,
                y: 0
            };
            b = this._canvas.offset(), c = this._canvas.parent().offset(), d = this._scale / this._pixelRatio;
            var g = Math.ceil(e * d + b.left),
                h = Math.ceil(f * d + b.top);
            return {
                x: g,
                y: h
            }
        }, WMKS.widgetProto.getEventPosition = function(a) {
            var b, c;
            if (a.pageX || a.pageY) b = a.pageX, c = a.pageY;
            else if (a.clientX || a.clientY) b = a.clientX + document.body.scrollLeft + document.documentElement.scrollLeft, c = a.clientY + document.body.scrollTop + document.documentElement.scrollTop;
            return this.getCanvasPosition(b, c)
        }, WMKS.widgetProto._isCanvasMouseEvent = function(a) {
            var b = a || window.event,
                c = b.target || b.srcElement;
            return this._mouseDownBMask !== 0 ? !0 : c === this._canvas[0] || this._video && c === this._video[0] || c === $("#relativePadSurface")
        }, WMKS.widgetProto._onMouseButton = function(a, b) {
            if (this._vncDecoder && this._isCanvasMouseEvent(a)) {
                var c = a || window.event,
                    d = this.getEventPosition(c),
                    e;
                return c.which ? WMKS.BROWSER.isMacOS() && WMKS.BROWSER.isGecko() && c.ctrlKey && c.button === 2 ? (WMKS.LOGGER.trace("FF on OSX: Rewrite Ctrl+Right-click as Ctrl+Left-click."), e = 1) : e = 1 << c.button : e = (c.button & 1) << 0 | (c.button & 2) << 1 | (c.button & 4) >> 1, this.sendMouseButtonMessage(d, b, e)
            }
        }, WMKS.widgetProto.sendMouseButtonMessage = function(a, b, c) {
            return this._vncDecoder && (b ? this._mouseDownBMask |= c : this._mouseDownBMask &= ~c, (this._mousePosGuest.x !== a.x || this._mousePosGuest.y !== a.y) && this.sendMouseMoveMessage(a), this._vncDecoder.onMouseButton(a.x, a.y, b, c)), !0
        }, WMKS.widgetProto._onMouseWheel = function(a) {
            if (this._vncDecoder && this._isCanvasMouseEvent(a)) {
                var b = a || window.event,
                    c = this.getEventPosition(b),
                    d = Math.max(Math.min(a.wheelDeltaX, 1), -1),
                    e = Math.max(Math.min(a.wheelDeltaY, 1), -1);
                return this.options.reverseScrollY && (e *= -1), this.sendScrollMessage(c, d, e), a.stopPropagation(), a.preventDefault(), !1
            }
        }, WMKS.widgetProto.sendScrollMessage = function(a, b, c) {
            this._vncDecoder && this._vncDecoder.onMouseWheel(a.x, a.y, b, c)
        }, WMKS.widgetProto._onMouseMove = function(a) {
            if (this._vncDecoder && this._isCanvasMouseEvent(a)) {
                var b = a || window.event,
                    c = this.getEventPosition(b);
                this.sendMouseMoveMessage(c)
            }
            return !0
        }, WMKS.widgetProto.sendMouseMoveMessage = function(a) {
            this._vncDecoder && (this._vncDecoder.onMouseMove(a.x, a.y), this._mousePosGuest = a, this._touchHandler && this._touchHandler.onCaretPositionChanged(a))
        }, WMKS.widgetProto._onBlur = function(a) {
            return this.connected && (this._keyboardManager.cancelModifiers(), this._vncDecoder.onMouseButton(this._mousePosGuest.x, this._mousePosGuest.y, 0, this._mouseDownBMask), this._mouseDownBMask = 0), !0
        }, WMKS.widgetProto._onPaste = function(a) {
            var b = a.originalEvent,
                c = this;
            if (b && b.clipboardData) {
                var d = b.clipboardData.items;
                if (d)
                    for (var e = 0; e < d.length; e++) d[e].kind === "string" && d[e].type === "text/plain" && d[e].getAsString(function(a) {
                        c._keyboardManager.processInputString(a)
                    })
            }
            return !0
        }, WMKS.widgetProto.disconnectEvents = function() {
            this.element.unbind("contextmenu.wmks").unbind("keydown.wmks").unbind("keypress.wmks").unbind("keyup.wmks").unbind("mousedown.wmks").unbind("mousewheel.wmks"), this.element.unbind("mousemove.wmks").unbind("mouseup.wmks").unbind("blur.wmks"), $(window).unbind("blur.wmks"), this._touchHandler && this._touchHandler.disconnectEvents()
        }, WMKS.widgetProto.connectEvents = function() {
            var a = this;
            this.element.bind("blur.wmks", function(b) {
                return a._onBlur(b)
            }), this.element.bind("contextmenu.wmks", function(a) {
                return !1
            }).bind("keydown.wmks", function(b) {
                return a.updateUserActivity(), a._keyboardManager.onKeyDown(b)
            }).bind("keypress.wmks", function(b) {
                return a._keyboardManager.onKeyPress(b)
            }).bind("keyup.wmks", function(b) {
                return a.updateUserActivity(), a._keyboardManager.onKeyUp(b)
            }), $(window).bind("blur.wmks", function(b) {
                return a._onBlur(b)
            }).bind("mousemove.wmks", function(b) {
                a.updateUserActivity();
                if (a.options.sendRelativeMouseEvent) return;
                return a._onMouseMove(b)
            }).bind("mousewheel.wmks", function(b) {
                a.updateUserActivity();
                if (a.options.sendRelativeMouseEvent) return;
                return a._onMouseWheel(b)
            }).bind("mouseup.wmks", function(b) {
                a.updateUserActivity();
                if (a.options.sendRelativeMouseEvent) return;
                return a._onMouseButton(b, 0)
            }).bind("mousedown.wmks", function(b) {
                a.updateUserActivity();
                if (a.options.sendRelativeMouseEvent) return;
                return a._onMouseButton(b, 1)
            }), this._touchHandler && this._touchHandler.installTouchHandlers(), this._relativeMouseHandler && this._relativeMouseHandler.installMouseHandlers()
        }, WMKS.widgetProto.maxFitWidth = function() {
            return this.element[0].scrollWidth * this._pixelRatio
        }, WMKS.widgetProto.maxFitHeight = function() {
            return this.element[0].scrollHeight * this._pixelRatio
        }, WMKS.widgetProto.hideKeyboard = function(a) {
            a = a || {}, a.show = !1, this.toggleKeyboard(a)
        }, WMKS.widgetProto.showKeyboard = function(a) {
            a = a || {}, a.show = !0, this.toggleKeyboard(a)
        }, WMKS.widgetProto.toggleKeyboard = function(a) {
            this.options.allowMobileKeyboardInput && this._touchHandler && this._touchHandler.toggleKeyboard(a)
        }, WMKS.widgetProto.toggleTrackpad = function(a) {
            this.options.allowMobileTrackpad && this._touchHandler && this._touchHandler.toggleTrackpad(a)
        }, WMKS.widgetProto.toggleRelativePad = function(a) {
            this._relativeMouseHandler && this._relativeMouseHandler.toggleRelativePad(a)
        }, WMKS.widgetProto.toggleExtendedKeypad = function(a) {
            this.options.allowMobileExtendedKeypad && this._touchHandler && this._touchHandler.toggleExtendedKeypad(a)
        }, WMKS.widgetProto.sendInputString = function(a) {
            this._keyboardManager.processInputString(a, !0)
        }, WMKS.widgetProto.sendKeyCodes = function(a) {
            var b, c = [];
            for (b = 0; b < a.length; b++) {
                var d = a[b];
                d > 0 ? (this._keyboardManager.sendKey(d, !1, !1), (d !== 20 || WMKS.BROWSER.isMacOS()) && c.push(d)) : d < 0 && this._keyboardManager.sendKey(0 - d, !0, !0)
            }
            for (b = c.length - 1; b >= 0; b--) this._keyboardManager.sendKey(c[b], !0, !1)
        }, WMKS.widgetProto.rescale = function() {
            this.rescaleOrResize(!0)
        }, WMKS.widgetProto.updateFitGuestSize = function(a) {
            var b = this.element.width() * this._pixelRatio,
                c = this.element.height() * this._pixelRatio;
            if (!this.options.fitGuest || a && this._guestWidth === b && this._guestWidth === c) return;
            this._vncDecoder.onRequestResolution(b, c)
        }, WMKS.widgetProto.updateTopology = function(a) {
            var b;
            if (!this.options.fitGuest) return;
            for (b = 0; b < a.length; b++) a[b].left = a[b].left * this._pixelRatio, a[b].top = a[b].top * this._pixelRatio, a[b].requestedWidth = a[b].requestedWidth * this._pixelRatio, a[b].requestedHeight = a[b].requestedHeight * this._pixelRatio;
            this._vncDecoder.onRequestTopology(a)
        }, WMKS.widgetProto.rescaleOrResize = function(a) {
            var b = 1,
                c = 0,
                d = 0,
                e = this.element.width(),
                f = this.element.height();
            this._canvas.css({
                width: this._guestWidth / this._pixelRatio,
                height: this._guestHeight / this._pixelRatio
            });
            var g = this._canvas.width(),
                h = this._canvas.height();
            if (this.transform === null || !!this.options.fitToParent || !!this.options.fitGuest)
                if (this.transform !== null && this.options.fitToParent) {
                    var i = e / g,
                        j = f / h;
                    c = (e - g) / 2, d = (f - h) / 2, b = Math.max(.1, Math.min(i, j))
                } else this.options.fitGuest && a ? this.updateFitGuestSize(!0) : this.transform === null && WMKS.LOGGER.warn("No scaling support");
            if (this.transform !== null) {
                b !== this._scale && (this._scale = b, this._canvas.css(this.transform, "scale(" + this._scale + ")"));
                if (c !== this._x || d !== this._y) this._x = c, this._y = d, this._canvas.css({
                    top: d,
                    left: c
                })
            }
        }, WMKS.widgetProto.setVCDProxyHandshakeVmxPath = function(a) {
            this.options.VCDProxyHandshakeVmxPath = a, this._vncDecoder && this._vncDecoder.options && (this._vncDecoder.options.VCDProxyHandshakeVmxPath = a)
        }, WMKS.widgetProto.disconnect = function() {
            this._vncDecoder.disconnect(), this.disconnectEvents(), this._keyboardManager.cancelModifiers()
        }, WMKS.widgetProto.connect = function(a) {
            this.disconnect(), this._vncDecoder.connect(a), this.connectEvents()
        }, WMKS.widgetProto.destroy = function() {
            this.disconnect(), this.element.removeClass("wmks"), this._touchHandler.destroy(), this._touchHandler = null, this._relativeMouseHandler.destroy(), this._relativeMouseHandler = null, this._canvas.remove(), this._video && this._video.remove(), this._backCanvas && this._backCanvas.remove(), this._blitTempCanvas && this._blitTempCanvas.remove(), $.Widget.prototype.destroy.call(this)
        }, WMKS.widgetProto.requestElementReposition = function(a, b) {
            if (this._touchHandler) {
                if (b) {
                    this._touchHandler.addToRepositionQueue(a);
                    return
                }
                this._touchHandler.widgetRepositionOnRotation(a)
            }
        }, WMKS.widgetProto.updateUserActivity = function() {
            this._trigger("useractivity", 0, $.now())
        }, WMKS.widgetProto._create = function() {
            var a = this;
            this._mouseDownBMask = 0, this._mousePosGuest = {
                x: 0,
                y: 0
            }, this._scale = 1, this._pixelRatio = 1, this.connected = !1, this.isCanvasActive = !1, this._canvas = WMKS.UTIL.createCanvas(!0).prop({
                id: "mainCanvas",
                tabindex: 1
            }), this._backCanvas = WMKS.UTIL.createCanvas(!0), this._blitTempCanvas = WMKS.UTIL.createCanvas(!0), this.element.addClass("wmks").append(this._canvas), this.options.enableMP4 && (this._video = WMKS.UTIL.createVideo(!0), this.element.append(this._video));
            var b = function(b) {
                return typeof a._canvas[0].style[b] != "undefined" ? b : null
            };
            this.transform = b("transform") || b("WebkitTransform") || b("MozTransform") || b("msTransform") || b("OTransform"), this._vncDecoder = new WMKS.VNCDecoder({
                useVNCHandshake: this.options.useVNCHandshake,
                VCDProxyHandshakeVmxPath: this.options.VCDProxyHandshakeVmxPath,
                useUnicodeKeyboardInput: this.options.useUnicodeKeyboardInput,
                enableVorbisAudioClips: this.options.enableVorbisAudioClips,
                enableOpusAudioClips: this.options.enableOpusAudioClips,
                enableAacAudioClips: this.options.enableAacAudioClips,
                enableVVC: this.options.enableVVC,
                enableUint8Utf8: this.options.enableUint8Utf8,
                enableVMWSessionClose: this.options.enableVMWSessionClose,
                enableVMWAudioMixer: this.options.enableVMWAudioMixer,
                retryConnectionInterval: this.options.retryConnectionInterval,
                sendRelativeMouseEvent: this.options.sendRelativeMouseEvent,
                canvas: this._canvas[0],
                backCanvas: this._backCanvas[0],
                blitTempCanvas: this._blitTempCanvas[0],
                mediaPlayer: this.options.enableMP4 ? this._video[0] : null,
                enableRawH264: this.options.enableRawH264,
                enableTopologyChange: this.options.enableTopologyChange,
                enableH264Multimon: this.options.enableH264Multimon,
                onConnecting: function(b, c) {
                    a._trigger("connecting", 0, {
                        vvc: b,
                        vvcSession: c
                    })
                },
                onConnected: function() {
                    a.connected = !0, a._trigger("connected"), a._keyboardManager.clearState(), a.rescaleOrResize(!0)
                },
                onBeforeDisconnected: function(b) {
                    a._trigger("beforedisconnected", 0, b)
                },
                onDisconnected: function(b, c) {
                    a.connected = !1, a._trigger("disconnected", 0, {
                        reason: b,
                        code: c
                    })
                },
                onAuthenticationFailed: function() {
                    a._trigger("authenticationFailed")
                },
                onError: function(b) {
                    a._trigger("error", 0, b)
                },
                onProtocolError: function() {
                    a._trigger("protocolError")
                },
                onNewDesktopSize: function(b, c) {
                    a._guestWidth = b, a._guestHeight = c;
                    var d = {
                            width: b,
                            height: c
                        },
                        e = {
                            width: b / a._pixelRatio,
                            height: c / a._pixelRatio
                        };
                    a._canvas.attr(d).css(e), d.y = c, a._backCanvas.attr(d).css(e), a._blitTempCanvas.attr(d).css(e), a._video && a._video.attr(d).css(e), a._trigger("resolutionchanged", null, d), a.rescaleOrResize(!1)
                },
                onEncodingChanged: function(b) {
                    b === "TightPNG" && !a.isCanvasActive ? (WMKS.LOGGER.info("Activate canvas element since we use TightPNG encoding."), a.isCanvasActive = !0, a._video && a._video.hide(), a._canvas.show()) : b === "MP4" && a.isCanvasActive ? (WMKS.LOGGER.info("Activate video element since we use MP4 encoding."), a._video ? (a.isCanvasActive = !1, a._canvas.hide(), a._video.show()) : WMKS.LOGGER.error("Video element doesn't exist.")) : b === "RawH264" && a.isCanvasActive && WMKS.LOGGER.info("Activate video element since we use raw H264 encoding.")
                },
                onKeyboardLEDsChanged: function(b) {
                    a._trigger("keyboardLEDsChanged", 0, b)
                },
                onCursorStateChanged: function(b) {
                    a._touchHandler && a._touchHandler.setCursorVisibility(b)
                },
                onHeartbeat: function(b) {
                    a._trigger("heartbeat", 0, b)
                },
                onUpdateCopyPasteUI: function(b, c) {
                    var d = {
                        noCopyUI: b,
                        noPasteUI: c
                    };
                    a._trigger("updateCopyPasteUI", 0, d)
                },
                onCopy: function(b) {
                    return typeof b != "string" ? (WMKS.LOGGER.debug("data format is not string, ignore."), !1) : (a._trigger("copy", 0, b), !0)
                },
                onSetReconnectToken: function(b) {
                    a._trigger("reconnecttoken", 0, b)
                },
                onAudio: function(b) {
                    a._trigger("audio", 0, [b])
                },
                onAudioMixer: function(b) {
                    a._trigger("audiomixer", 0, b)
                }
            }), this._keyboardManager = new WMKS.KeyboardManager({
                vncDecoder: this._vncDecoder,
                ignoredRawKeyCodes: this.options.ignoredRawKeyCodes,
                fixANSIEquivalentKeys: this.options.fixANSIEquivalentKeys,
                mapMetaToCtrlForKeys: this.options.mapMetaToCtrlForKeys,
                enableWindowsKey: this.options.enableWindowsKey,
                keyboardLayoutId: this.options.keyboardLayoutId
            }), this._touchHandler = new WMKS.TouchHandler({
                widgetProto: this,
                canvas: this._canvas,
                keyboardManager: this._keyboardManager,
                onToggle: function(b) {
                    a._trigger("toggle", 0, b)
                }
            }), this._relativeMouseHandler = new WMKS.RelativeMouseHandler({
                widgetProto: this,
                canvas: this._canvas,
                keyboardManager: this._keyboardManager,
                onToggle: function(b) {
                    a._trigger("toggle", 0, b), a._setOption("sendRelativeMouseEvent", b[1]), a._relativeMouseHandler.setCursorVisibility(b[1])
                }
            }), this._updatePixelRatio(), this.updateFitGuestSize(), this._relativeMouseHandler.initializeRelativeMouseFeature(), this._updateMobileFeature(this.options.allowMobileKeyboardInput, WMKS.CONST.TOUCH.FEATURE.SoftKeyboard), this._updateMobileFeature(this.options.allowMobileTrackpad, WMKS.CONST.TOUCH.FEATURE.Trackpad), this._updateMobileFeature(this.options.allowMobileExtendedKeypad, WMKS.CONST.TOUCH.FEATURE.ExtendedKeypad)
        },
        function() {
            "use strict",
            WMKS.dialogManager = function() {
                this.dialog = null, this.visible = !1, this.lastToggleTime = 0, this.options = {
                    name: "DIALOG_MGR",
                    toggleCallback: function(a, b) {},
                    minToggleTime: 50
                }
            },
            WMKS.dialogManager.prototype.setOption = function(a, b) {
                return this.options[a] = b, this
            },
            WMKS.dialogManager.prototype.setOptions = function(a) {
                var b;
                for (b in a) this.setOption(b, a[b]);
                return this
            },
            WMKS.dialogManager.prototype.initialize = function(a) {
                this.options = $.extend({}, this.options, a), this.dialog = this.create(), this.init()
            },
            WMKS.dialogManager.prototype.destroy = function() {
                !this.dialog || (this.disconnect(), this.remove())
            },
            WMKS.dialogManager.prototype.create = function() {
                return null
            },
            WMKS.dialogManager.prototype.init = function() {},
            WMKS.dialogManager.prototype.disconnect = function() {},
            WMKS.dialogManager.prototype.remove = function() {
                var a = this.dialog;
                !a || a.dialog("destroy").remove()
            },
            WMKS.dialogManager.prototype.toggle = function(a) {
                var b = this.dialog,
                    c = !this.visible,
                    d;
                if (!b) return;
                !a || this.setOptions(a), d = b.dialog("isOpen");
                if (c === d) return;
                if ($.now() - this.lastToggleTime < this.options.minToggleTime) return;
                d ? this.close() : this.open()
            },
            WMKS.dialogManager.prototype.sendUpdatedState = function(a) {
                this.visible = a, this.lastToggleTime = $.now(), $.isFunction(this.options.toggleCallback) && this.options.toggleCallback.call(this, [this.options.name, a])
            },
            WMKS.dialogManager.prototype.open = function() {
                !this.dialog || (this.visible = !this.visible, this.dialog.dialog("open"), this.sendUpdatedState(!0))
            },
            WMKS.dialogManager.prototype.close = function() {
                !this.dialog || (this.visible = !this.visible, this.dialog.dialog("close"), this.sendUpdatedState(!1))
            }
        }(),
        function() {
            "use strict",
            WMKS.extendedKeypad = function(a) {
                if (!a || !a.widget || !a.keyboardManager) return null;
                WMKS.dialogManager.call(this), this._widget = a.widget, this._kbManager = a.keyboardManager, this._parentElement = a.parentElement, this.manualModifiers = [], $.extend(this.options, {
                    name: "EXTENDED_KEYPAD"
                }), WMKS.LOGGER.warn("Key pad : " + this.options.name)
            },
            WMKS.extendedKeypad.prototype = new WMKS.dialogManager,
            WMKS.extendedKeypad.prototype.create = function() {
                var a = this,
                    b = $('<div id="ctrlPanePopup"></div>');
                return b.append(this.getControlPaneHtml()), b.dialog({
                    autoOpen: !1,
                    closeOnEscape: !0,
                    resizable: !1,
                    position: {
                        my: "center",
                        at: "center",
                        of: this._parentElement
                    },
                    zIndex: 1e3,
                    dialogClass: "ctrl-pane-wrapper",
                    close: function(c) {
                        return a._kbManager.cancelModifiers(!0), b.find(".ab-modifier-key.ab-modifier-key-down").removeClass("ab-modifier-key-down"), a.toggleFunctionKeys(!1), a.sendUpdatedState(!1), !0
                    },
                    dragStop: function(b) {
                        a.positionFunctionKeys()
                    }
                }), b
            },
            WMKS.extendedKeypad.prototype.init = function() {
                var a = this,
                    b = this.dialog,
                    c = function(b) {
                        var c = parseInt($(this).attr("abkeycode"), 10);
                        return a._kbManager.handleSoftKb(c, !1), !1
                    };
                b.find(".ab-modifier-key").on("touchstart", function(b) {
                    var c = $(this).hasClass("ab-modifier-key-down"),
                        d = parseInt($(this).attr("abkeycode"), 10);
                    return isNaN(d) ? (WMKS.LOGGER.debug("Got NaN as modifier key. Skipping it."), !1) : ($(this).toggleClass("ab-modifier-key-down"), a._kbManager.updateModifiers(d, c), !1)
                }), b.find("#fnMasterKey").off("touchstart").on("touchstart", function(b) {
                    return a.toggleFunctionKeys(), !1
                }), b.find(".ab-extended-key").off("touchstart").on("touchstart", c), b.find(".ab-flip").off("touchstart").on("touchstart", function() {
                    return $(this).parents(".flip-container").toggleClass("perform-flip"), a.toggleFunctionKeys(!1), !1
                }), b.parent().prop("id", "ctrlPaneWidget"), b.parent().parent().append(this.getFunctionKeyHtml()), $("#fnKeyPad").find(".ab-extended-key").off("touchstart").on("touchstart", c), b.parent().off("orientationchange.ctrlpane").on("orientationchange.ctrlpane", function() {
                    a._widget.requestElementReposition($(this)), a.positionFunctionKeys()
                })
            },
            WMKS.dialogManager.prototype.disconnect = function() {
                var a = this.dialog;
                a.find("#fnMasterKey").off("touchstart"), a.find(".ab-extended-key").off("touchstart"), a.find(".ab-flip").off("touchstart"), a.parent().off("orientationchange.ctrlpane"), $("#fnKeyPad").find(".ab-extended-key").off("touchstart")
            },
            WMKS.extendedKeypad.prototype.getControlPaneHtml = function() {
                var a = '<div class="ctrl-pane flip-container">            <div class="flipper">               <div class="back">                  <div class="ctrl-key-top-row ab-extended-key baseKey" abkeycode="36"><div>Home</div></div>                  <div class="ctrl-key-top-row ab-extended-key baseKey" abkeycode="38">                     <img class="touch-sprite up-arrow"/></div>                  <div class="ctrl-key-top-row ab-extended-key baseKey" abkeycode="35"><div>End</div></div>                  <div class="ctrl-key-top-row ab-extended-key baseKey" abkeycode="27"><div>Esc</div></div>                  <div class="ctrl-key-bottom-row ab-extended-key baseKey" abkeycode="37">                     <img class="touch-sprite left-arrow"/></div>                  <div class="ctrl-key-bottom-row ab-extended-key baseKey" abkeycode="40">                     <img class="touch-sprite down-arrow"/></div>                  <div class="ctrl-key-bottom-row ab-extended-key baseKey" abkeycode="39">                     <img class="touch-sprite right-arrow"/></div>                  <div class="ctrl-key-bottom-row ab-flip baseKey">                     <img class="touch-sprite more-keys"/></div>               </div>               <div class="front">                  <div class="ctrl-key-top-row ab-modifier-key baseKey" abkeycode="16"><div>Shift</div></div>                  <div class="ctrl-key-top-row ab-extended-key baseKey" abkeycode="46"><div>Del</div></div>                  <div class="ctrl-key-top-row ab-extended-key baseKey" abkeycode="33"><div>PgUp</div></div>                  <div id="fnMasterKey" class="ctrl-key-top-row baseKey">                     <div style="letter-spacing: -1px">F1-12</div></div>                  <div class="ctrl-key-bottom-row ab-modifier-key baseKey" abkeycode="17"><div>Ctrl</div></div>                  <div class="ctrl-key-bottom-row ab-modifier-key baseKey" abkeycode="18"><div>Alt</div></div>                  <div class="ctrl-key-bottom-row ab-extended-key baseKey" abkeycode="34"><div>PgDn</div></div>                  <div class="ctrl-key-bottom-row ab-flip baseKey">                     <img class="touch-sprite more-keys"/></div>               </div>            </div>         </div>';
                return a
            },
            WMKS.extendedKeypad.prototype.getFunctionKeyHtml = function() {
                var a = '<div class="fnKey-pane-wrapper hide" id="fnKeyPad">             <div class="ctrl-pane">                <div class="key-group up-position">                  <div class="border-key-top-left">                     <div class="fn-key-top-row ab-extended-key baseKey" abkeycode="112"><div>F1</div></div>                  </div>                  <div class="fn-key-top-row ab-extended-key baseKey" abkeycode="113"><div>F2</div></div>                  <div class="fn-key-top-row ab-extended-key baseKey" abkeycode="114"><div>F3</div></div>                  <div class="fn-key-top-row ab-extended-key baseKey" abkeycode="115"><div>F4</div></div>                  <div class="fn-key-top-row ab-extended-key baseKey" abkeycode="116"><div>F5</div></div>                  <div class="border-key-top-right">                     <div class="fn-key-top-row ab-extended-key baseKey" abkeycode="117"><div>F6</div></div>                  </div>                  <div class="border-key-bottom-left">                     <div class="fn-key-bottom-row ab-extended-key baseKey" abkeycode="118"><div>F7</div></div>                  </div>                  <div class="fn-key-bottom-row ab-extended-key baseKey" abkeycode="119"><div>F8</div></div>                  <div class="fn-key-bottom-row ab-extended-key baseKey" abkeycode="120"><div>F9</div></div>                  <div class="fn-key-bottom-row ab-extended-key baseKey" abkeycode="121"><div>F10</div></div>                  <div class="fn-key-bottom-row ab-extended-key baseKey" abkeycode="122"><div>F11</div></div>                  <div class="border-key-bottom-right">                     <div class="fn-key-bottom-row ab-extended-key baseKey" abkeycode="123"><div>F12</div></div>                  </div>               </div>            </div>            <div class="fnKey-inner-border-helper" id="fnKeyInnerBorder"></div>         </div>';
                return a
            },
            WMKS.extendedKeypad.prototype.toggleCtrlPane = function() {
                var a = this.dialog;
                a.dialog("isOpen") ? a.dialog("close") : a.dialog("open")
            },
            WMKS.extendedKeypad.prototype.toggleFunctionKeys = function(a) {
                var b = $("#fnKeyPad"),
                    c = a || typeof a == "undefined" && !b.is(":visible");
                b.toggle(c), $("#fnMasterKey").toggleClass("ab-modifier-key-down", c), this.positionFunctionKeys()
            },
            WMKS.extendedKeypad.prototype.positionFunctionKeys = function() {
                var a = $("#fnKeyPad"),
                    b = $("#ctrlPaneWidget");
                if (a.is(":visible")) {
                    a.position({
                        my: "right bottom",
                        at: "right top",
                        of: b,
                        collision: "flip"
                    }), $("#fnKeyInnerBorder").height(a.height() - 2).width(a.width() - 2);
                    var c = a.offset().top + a.height(),
                        d = c <= b.offset().top + b.height();
                    this.adjustFunctionKeyStyle(d);
                    var e;
                    d ? (e = parseInt(b.css("z-index"), 10) - 1, $("#fnKeyInnerBorder").css("border-color", "#d5d5d5")) : (e = parseInt($("#ctrlPaneWidget").css("z-index"), 10) + 1, $("#fnKeyInnerBorder").css("border-color", "#aaa")), a.css("z-index", e.toString())
                }
                return !0
            },
            WMKS.extendedKeypad.prototype.adjustFunctionKeyStyle = function(a) {
                var b = $("#fnKeyPad"),
                    c = b.find(".key-group");
                a ? c.hasClass("down-position") && (c.removeClass("down-position"), c.addClass("up-position"), b.removeClass("fnKey-pane-wrapper-down"), b.addClass("fnKey-pane-wrapper")) : c.hasClass("up-position") && (c.removeClass("up-position"), c.addClass("down-position"), b.removeClass("fnKey-pane-wrapper"), b.addClass("fnKey-pane-wrapper-down"))
            }
        }(),
        function() {
            "use strict",
            WMKS.CONST.TRACKPAD = {
                STATE: {
                    idle: 0,
                    tap: 1,
                    tap_2finger: 2,
                    drag: 3,
                    scroll: 4
                }
            },
            WMKS.trackpadManager = function(a, b) {
                WMKS.dialogManager.call(this), this._widget = a, this._canvas = b, this._cursorPosGuest = {
                    x: 0,
                    y: 0
                }, this._dragTimer = null, this._dragStartedByLongTap = !1, this.state = WMKS.CONST.TRACKPAD.STATE.idle, this.history = [], $.extend(this.options, {
                    name: "TRACKPAD",
                    speedControlMinMovePx: 5,
                    accelerator: 10,
                    minSpeed: 1,
                    maxSpeed: 10
                }), WMKS.LOGGER.warn("trackpad : " + this.options.name)
            },
            WMKS.trackpadManager.prototype = new WMKS.dialogManager,
            WMKS.trackpadManager.prototype.getTrackpadHtml = function() {
                var a = '<div id="trackpad" class="trackpad-container">                   <div class="left-border"></div>                   <div id="trackpadSurface" class="touch-area"></div>                   <div class="right-border"></div>                   <div class="bottom-border">                      <div class="button-container">                         <div id="trackpadLeft" class="button-left"></div>                         <div id="trackpadRight" class="button-right"></div>                      </div>                   </div>               </div>';
                return a
            },
            WMKS.trackpadManager.prototype.create = function() {
                var a, b = this;
                return !this._widget || !this._canvas ? (WMKS.LOGGER.debug("Trackpad dialog creation has been aborted. Widget or Canvas is not ready."), null) : (a = $(this.getTrackpadHtml()), a.dialog({
                    autoOpen: !1,
                    closeOnEscape: !0,
                    resizable: !1,
                    position: {
                        my: "center",
                        at: "center",
                        of: this._canvas
                    },
                    zIndex: 1e3,
                    draggable: !0,
                    dialogClass: "trackpad-wrapper",
                    close: function(a) {
                        b.sendUpdatedState(!1)
                    },
                    create: function(a) {
                        b.layout($(this).parent())
                    }
                }), a)
            },
            WMKS.trackpadManager.prototype.init = function() {
                var a = this.dialog,
                    b = this,
                    c, d, e;
                if (!a) {
                    WMKS.LOGGER.debug("Trackpad init aborted. Dialog is not created successfully.");
                    return
                }
                this._widget.requestElementReposition(a.parent(), !0), c = a.find("#trackpadSurface").on("touchstart", function(a) {
                    return b.trackpadTouchStart(a.originalEvent)
                }).on("touchmove", function(a) {
                    return b.trackpadTouchMove(a.originalEvent)
                }).on("touchend", function(a) {
                    return b.trackpadTouchEnd(a.originalEvent)
                }), d = a.find("#trackpadLeft").on("touchstart", function(a) {
                    return b.trackpadClickStart(a, WMKS.CONST.CLICK.left)
                }).on("touchend", function(a) {
                    return b.trackpadClickEnd(a, WMKS.CONST.CLICK.left)
                }), e = a.find("#trackpadRight").on("touchstart", function(a) {
                    return b.trackpadClickStart(a, WMKS.CONST.CLICK.right)
                }).on("touchend", function(a) {
                    return b.trackpadClickEnd(a, WMKS.CONST.CLICK.right)
                })
            },
            WMKS.trackpadManager.prototype.disconnect = function() {
                var a = this.dialog,
                    b, c, d;
                if (!a) return;
                b = a.find("#trackpadSurface").off("touchmove").off("touchstart").off("touchend"), c = a.find("#trackpadLeft").off("touchstart").off("touchend"), d = a.find("#trackpadRight").off("touchstart").off("touchend")
            },
            WMKS.trackpadManager.prototype.layout = function(a) {
                var b = this._canvas,
                    c, d;
                if (!a || !b) return;
                c = a.parent(), d = b.parent(), c !== d && d.append(a)
            },
            WMKS.trackpadManager.prototype.trackpadClickStart = function(a, b) {
                return b !== WMKS.CONST.CLICK.left && b !== WMKS.CONST.CLICK.right ? (WMKS.LOGGER.debug("assert: unknown button " + b), !1) : ($(a.target).addClass("button-highlight"), this._widget.sendMouseButtonMessage(this.getMousePosition(), !0, b), !1)
            },
            WMKS.trackpadManager.prototype.trackpadClickEnd = function(a, b) {
                return b !== WMKS.CONST.CLICK.left && b !== WMKS.CONST.CLICK.right ? (WMKS.LOGGER.debug("assert: unknown button " + b), !1) : ($(a.target).removeClass("button-highlight"), this._widget.sendMouseButtonMessage(this.getMousePosition(), !1, b), !1)
            },
            WMKS.trackpadManager.prototype.computeMovingDistance = function(a, b) {
                var c, d, e, f;
                return c = this.getTrackpadSpeed(a, this.history[0].x, this.history[1].x, this.history[2].x), d = this.getTrackpadSpeed(b, this.history[0].y, this.history[1].y, this.history[2].y), e = WMKS.UTIL.getLineLength(c, d), f = e * this.options.accelerator, f > this.options.maxSpeed ? f = this.options.maxSpeed : f < this.options.minSpeed && (f = this.options.minSpeed), [c * f, d * f]
            },
            WMKS.trackpadManager.prototype.getTrackpadSpeed = function(a, b, c, d) {
                return a * .3 + b * .1 - c * .1 - d * .3
            },
            WMKS.trackpadManager.prototype.trackpadTouchStart = function(a) {
                var b = this;
                return a.targetTouches.length > 2 ? this.state === WMKS.CONST.TRACKPAD.STATE.scroll ? WMKS.LOGGER.debug("Ignore new touchstart, currently scrolling, touch#: " + a.targetTouches.length) : (WMKS.LOGGER.debug("Aborting touch, too many fingers #: " + a.targetTouches.length), this.resetTrackpadState()) : a.targetTouches.length === 2 ? this.state = WMKS.CONST.TRACKPAD.STATE.tap_2finger : (this.state = WMKS.CONST.TRACKPAD.STATE.tap, this._dragTimer !== null && (clearTimeout(this._dragTimer), this._dragTimer = null), this._dragTimer = setTimeout(function() {
                    b._dragTimer = null, b._widget.sendMouseButtonMessage(b.getMousePosition(), !0, WMKS.CONST.CLICK.left), b._dragStartedByLongTap = !0
                }, WMKS.CONST.TOUCH.leftDragDelayMs)), !1
            },
            WMKS.trackpadManager.prototype.trackpadTouchMove = function(a) {
                var b, c, d, e = $(a.target),
                    f = this._widget;
                return this._dragTimer !== null && (clearTimeout(this._dragTimer), this._dragTimer = null), this.state === WMKS.CONST.TRACKPAD.STATE.idle ? !1 : (b = a.targetTouches[0].pageX, c = a.targetTouches[0].pageY, c < e.offset().top || c > e.offset().top + e.height() || b < e.offset().left || b > e.offset().left + e.width() ? (this.state === WMKS.CONST.TRACKPAD.STATE.drag && (this._dragStartedByLongTap && f.sendMouseButtonMessage(this.getMousePosition(), !1, WMKS.CONST.CLICK.left), this.state = WMKS.CONST.TRACKPAD.STATE.tap, this.history.length = 0), !1) : (this.state === WMKS.CONST.TRACKPAD.STATE.drag ? (d = this.computeNewCursorLocation(b, c), !f._touchHandler || f._touchHandler.moveCursor(d.x, d.y), f.sendMouseMoveMessage(d), this.history.shift(), this.history.push({
                    x: b,
                    y: c
                })) : this.state === WMKS.CONST.TRACKPAD.STATE.scroll && this.sendScrollMessageFromTrackpad(a.targetTouches[0]), this.state === WMKS.CONST.TRACKPAD.STATE.tap ? (this.state = WMKS.CONST.TRACKPAD.STATE.drag, this.history.push({
                    x: b,
                    y: c
                }, {
                    x: b,
                    y: c
                }, {
                    x: b,
                    y: c
                })) : this.state === WMKS.CONST.TRACKPAD.STATE.tap_2finger && a.targetTouches.length === 2 && (this.state = WMKS.CONST.TRACKPAD.STATE.scroll, this.history[0] = {
                    x: b,
                    y: c
                }), !1))
            },
            WMKS.trackpadManager.prototype.computeNewCursorLocation = function(a, b) {
                var c, d = this.getMousePosition();
                return c = WMKS.UTIL.getLineLength(a - this.history[2].x, b - this.history[2].y), isNaN(c) || c === 0 ? d : (c < this.options.speedControlMinMovePx ? (d.x += a - this.history[2].x, d.y += b - this.history[2].y) : (c = this.computeMovingDistance(a, b), d.x += Math.floor(c[0]), d.y += Math.floor(c[1])), this._widget.getCanvasPosition(d.x, d.y))
            },
            WMKS.trackpadManager.prototype.trackpadTouchEnd =
            function(a) {
                var b;
                return this._dragTimer !== null && (clearTimeout(this._dragTimer), this._dragTimer = null), a.targetTouches.length !== 0 || this.state === WMKS.CONST.TRACKPAD.STATE.idle ? !1 : (b = this.getMousePosition(), this.state === WMKS.CONST.TRACKPAD.STATE.tap ? (this._widget.sendMouseButtonMessage(b, !0, WMKS.CONST.CLICK.left), this._widget.sendMouseButtonMessage(b, !1, WMKS.CONST.CLICK.left)) : this.state === WMKS.CONST.TRACKPAD.STATE.tap_2finger ? (this._widget.sendMouseButtonMessage(b, !0, WMKS.CONST.CLICK.right), this._widget.sendMouseButtonMessage(b, !1, WMKS.CONST.CLICK.right)) : this.state === WMKS.CONST.TRACKPAD.STATE.drag && this._dragStartedByLongTap && this._widget.sendMouseButtonMessage(b, !1, WMKS.CONST.CLICK.left), this.resetTrackpadState(), !1)
            },
            WMKS.trackpadManager.prototype.resetTrackpadState = function() {
                this.state = WMKS.CONST.TRACKPAD.STATE.idle, this.history.length = 0, this._dragStartedByLongTap = !1
            },
            WMKS.trackpadManager.prototype.sendScrollMessageFromTrackpad = function(a) {
                var b = 0,
                    c = 0,
                    d, e, f, g;
                d = a.pageX - this.history[0].x, e = a.pageY - this.history[0].y, !this._widget._touchHandler || (f = this._widget._touchHandler._calculateMouseWheelDeltas(d, e), b = f.wheelDeltaX, c = f.wheelDeltaY);
                if (b !== 0 || c !== 0) this._widget.sendScrollMessage(this.getMousePosition(), b, c), b !== 0 && (this.history[0].x = a.pageX), c !== 0 && (this.history[0].y = a.pageY)
            },
            WMKS.trackpadManager.prototype.getMousePosition = function() {
                var a = this._widget._mousePosGuest;
                if (a.x === 0 && a.y === 0) {
                    if (this._cursorPosGuest.x !== a.x || this._cursorPosGuest.y !== a.y) a = this._cursorPosGuest, this._widget.sendMouseMoveMessage(a)
                } else this._cursorPosGuest = a;
                return a
            }
        }(), "use strict", WMKS.Packet = function(a, b, c) {
            this.length = b, this._buffer = a, this._readPosition = 0, this._byteOrder = c || WMKS.Packet.BYTE_ORDER.NETWORK_ORDER, this._byteOrder == WMKS.Packet.BYTE_ORDER.LITTLE_ENDIAN ? (this.setInt16 = this.setInt16le, this.setInt32 = this.setInt32le, this.setUint16 = this.setUint16le, this.setUint32 = this.setUint32le, this.getInt16 = this.getInt16le, this.getInt32 = this.getInt32le, this.getUint16 = this.getUint16le, this.getUint32 = this.getUint32le) : this._byteOrder == WMKS.Packet.BYTE_ORDER.BIG_ENDIAN && (this.setInt16 = this.setInt16be, this.setInt32 = this.setInt32be, this.setUint16 = this.setUint16be, this.setUint32 = this.setUint32be, this.getInt16 = this.getInt16be, this.getInt32 = this.getInt32be, this.getUint16 = this.getUint16be, this.getUint32 = this.getUint32be)
        }, WMKS.Packet.BYTE_ORDER = {
            LITTLE_ENDIAN: 1,
            BIG_ENDIAN: 2,
            NETWORK_ORDER: 2
        }, WMKS.Packet.createNewPacket = function(a, b) {
            return a = a || 512, new WMKS.Packet(new Uint8Array(a), 0, b)
        }, WMKS.Packet.createFromBuffer = function(a, b) {
            if (a instanceof ArrayBuffer) a = new Uint8Array(a);
            else if (!(a instanceof Uint8Array)) return null;
            return new WMKS.Packet(a, a.length, b)
        }, WMKS.Packet.createNewPacketBE = function(a) {
            return WMKS.Packet.createNewPacket(a, WMKS.Packet.BYTE_ORDER.BIG_ENDIAN)
        }, WMKS.Packet.createNewPacketLE = function(a) {
            return WMKS.Packet.createNewPacket(a, WMKS.Packet.BYTE_ORDER.LITTLE_ENDIAN)
        }, WMKS.Packet.createFromBufferBE = function(a) {
            return WMKS.Packet.createFromBuffer(a, WMKS.Packet.BYTE_ORDER.BIG_ENDIAN)
        }, WMKS.Packet.createFromBufferLE = function(a) {
            return WMKS.Packet.createFromBuffer(a, WMKS.Packet.BYTE_ORDER.LITTLE_ENDIAN)
        }, WMKS.Packet.prototype.reset = function() {
            this.length = 0, this._readPosition = 0
        }, WMKS.Packet.prototype.getData = function() {
            return this._buffer.subarray(0, this.length)
        }, WMKS.Packet.prototype.bytesRemaining = function() {
            return this.length - this._readPosition
        }, WMKS.Packet.prototype.getDataAsArrayBuffer = function() {
            return this._buffer.buffer.slice(this._buffer.byteOffset, this._buffer.byteOffset + this.length)
        }, WMKS.Packet.prototype.writeUint8 = function(a) {
            this._ensureWriteableBytes(1), this.setUint8(this.length, a), this.length += 1
        }, WMKS.Packet.prototype.writeUint16 = function(a) {
            this._ensureWriteableBytes(2), this.setUint16(this.length, a), this.length += 2
        }, WMKS.Packet.prototype.writeUint32 = function(a) {
            this._ensureWriteableBytes(4), this.setUint32(this.length, a), this.length += 4
        }, WMKS.Packet.prototype.writeInt8 = function(a) {
            this._ensureWriteableBytes(1), this.setInt8(this.length, a), this.length += 1
        }, WMKS.Packet.prototype.writeInt16 = function(a) {
            this._ensureWriteableBytes(2), this.setInt16(this.length, a), this.length += 2
        }, WMKS.Packet.prototype.writeInt32 = function(a) {
            this._ensureWriteableBytes(4), this.setInt32(this.length, a), this.length += 4
        }, WMKS.Packet.prototype.writeStringASCII = function(a) {
            var b;
            this._ensureWriteableBytes(a.length);
            for (b = 0; b < a.length; ++b) this.setUint8(this.length++, a.charCodeAt(b))
        }, WMKS.Packet.prototype.writeArray = function(a) {
            a && a.length && (this._ensureWriteableBytes(a.length), this._buffer.set(a, this.length), this.length += a.length)
        }, WMKS.Packet.prototype.readUint8 = function() {
            var a;
            return this._checkReadableBytes(1) && (a = this.getUint8(this._readPosition), this._readPosition += 1), a
        }, WMKS.Packet.prototype.readUint16 = function() {
            var a;
            return this._checkReadableBytes(2) && (a = this.getUint16(this._readPosition), this._readPosition += 2), a
        }, WMKS.Packet.prototype.readUint32 = function() {
            var a;
            return this._checkReadableBytes(4) && (a = this.getUint32(this._readPosition), this._readPosition += 4), a
        }, WMKS.Packet.prototype.readInt8 = function() {
            var a;
            return this._checkReadableBytes(1) && (a = this.getInt8(this._readPosition), this._readPosition += 1), a
        }, WMKS.Packet.prototype.readInt16 = function() {
            var a;
            return this._checkReadableBytes(2) && (a = this.getInt16(this._readPosition), this._readPosition += 2), a
        }, WMKS.Packet.prototype.readInt32 = function() {
            var a;
            return this._checkReadableBytes(4) && (a = this.getInt32(this._readPosition), this._readPosition += 4), a
        }, WMKS.Packet.prototype.readArray = function(a) {
            var b;
            return this._checkReadableBytes(a) && (a === 0 ? b = null : (b = this.getArray(this._readPosition, a), this._readPosition += a)), b
        }, WMKS.Packet.prototype.readStringASCII = function(a) {
            var b = this.readArray(a);
            return b && (b = String.fromCharCode.apply(String, b)), b
        }, WMKS.Packet.prototype.setUint8 = function(a, b) {
            this._buffer[a] = b & 255
        }, WMKS.Packet.prototype.setUint16be = function(a, b) {
            this._buffer[a + 1] = b & 255, this._buffer[a + 0] = b >> 8 & 255
        }, WMKS.Packet.prototype.setUint32be = function(a, b) {
            this._buffer[a + 3] = b & 255, this._buffer[a + 2] = b >> 8 & 255, this._buffer[a + 1] = b >> 16 & 255, this._buffer[a + 0] = b >> 24 & 255
        }, WMKS.Packet.prototype.setUint16le = function(a, b) {
            this._buffer[a + 0] = b & 255, this._buffer[a + 1] = b >> 8 & 255
        }, WMKS.Packet.prototype.setUint32le = function(a, b) {
            this._buffer[a + 0] = b & 255, this._buffer[a + 1] = b >> 8 & 255, this._buffer[a + 2] = b >> 16 & 255, this._buffer[a + 3] = b >> 24 & 255
        }, WMKS.Packet.prototype.setInt8 = function(a, b) {
            return this.setUint8(a, b)
        }, WMKS.Packet.prototype.setInt16be = function(a, b) {
            return this.setUint16be(a, b)
        }, WMKS.Packet.prototype.setInt32be = function(a, b) {
            return this.setUint32be(a, b)
        }, WMKS.Packet.prototype.setInt16le = function(a, b) {
            return this.setUint16le(a, b)
        }, WMKS.Packet.prototype.setInt32le = function(a, b) {
            return this.setUint32le(a, b)
        }, WMKS.Packet.prototype.getArray = function(a, b) {
            return this._buffer.subarray(a, a + b)
        }, WMKS.Packet.prototype.getInt8 = function(a) {
            var b = this._buffer[a];
            return b & 128 && (b = b - 255 - 1), b
        }, WMKS.Packet.prototype.getInt16be = function(a) {
            var b;
            return b = this._buffer[a + 1], b |= this._buffer[a + 0] << 8, b & 32768 && (b = b - 65535 - 1), b
        }, WMKS.Packet.prototype.getInt32be = function(a) {
            var b;
            return b = this._buffer[a + 3], b |= this._buffer[a + 2] << 8, b |= this._buffer[a + 1] << 16, b |= this._buffer[a + 0] << 24, b
        }, WMKS.Packet.prototype.getInt16le = function(a) {
            var b;
            return b = this._buffer[a + 0], b |= this._buffer[a + 1] << 8, b & 32768 && (b = b - 65535 - 1), b
        }, WMKS.Packet.prototype.getInt32le = function(a) {
            var b;
            return b = this._buffer[a + 0], b |= this._buffer[a + 1] << 8, b |= this._buffer[a + 2] << 16, b |= this._buffer[a + 3] << 24, b
        }, WMKS.Packet.prototype.getUint8 = function(a) {
            var b = this._buffer[a];
            return b
        }, WMKS.Packet.prototype.getUint16be = function(a) {
            var b;
            return b = this._buffer[a + 1], b |= this._buffer[a + 0] << 8, b
        }, WMKS.Packet.prototype.getUint32be = function(a) {
            var b;
            return b = this._buffer[a + 3], b |= this._buffer[a + 2] << 8, b |= this._buffer[a + 1] << 16, b |= this._buffer[a + 0] << 24, b < 0 && (b = 4294967295 + b + 1), b
        }, WMKS.Packet.prototype.getUint16le = function(a) {
            var b;
            return b = this._buffer[a + 0], b |= this._buffer[a + 1] << 8, b
        }, WMKS.Packet.prototype.getUint32le = function(a) {
            var b;
            return b = this._buffer[a + 0], b |= this._buffer[a + 1] << 8, b |= this._buffer[a + 2] << 16, b |= this._buffer[a + 3] << 24, b < 0 && (b = 4294967295 + b + 1), b
        }, WMKS.Packet.prototype._resizeBuffer = function(a) {
            if (a > 0) {
                var b = new Uint8Array(a);
                b.set(this._buffer), this._buffer = b
            }
        }, WMKS.Packet.prototype._ensureWriteableBytes = function(a) {
            if (a > 0) {
                var b = this.length + a,
                    c = this._buffer.length;
                while (c < b) c = Math.floor(c * 1.5);
                c > this._buffer.length && this._resizeBuffer(c)
            }
        }, WMKS.Packet.prototype._checkReadableBytes = function(a) {
            return this._readPosition + a <= this.length
        }, "use strict";
    var g = function() {
        return this._sessions = [], this._listeners = [], this._lastError = null, this
    };
    g.ENABLE_PROBE_CHANNEL = 1, g.MAJOR_VER = 3, g.MINOR_VER = 0, g.SUPPORTED_VER = [{
        MAJOR: 2,
        MINOR: 0,
        FLAGS: 0
    }, {
        MAJOR: 1,
        MINOR: 0,
        FLAGS: 0
    }], g.CAPS_V10_1 = 0, g.CAPS_V10_2 = 0, g.ALL_SESSIONS = -1, g.RTT_HISTORY_SIZE = 30, g.MIN_CHANNEL_NAME_LEN = 1, g.MAX_CHANNEL_NAME_LEN = 255, g.MAX_INITIAL_DATA_LEN = 4096, g.STATUS = {
        SUCCESS: 0,
        ERROR: 1,
        OUT_OF_MEMORY: 2,
        INVALID_ARGS: 3,
        INVALID_STATE: 4,
        CLOSED: 5,
        PROTOCOL_ERROR: 6,
        TRANSPORT_ERROR: 7,
        OPEN_REJECTED: 8,
        OPEN_TIMEOUT: 9
    }, g.prototype.openSession = function(a) {
        var b;
        return b = new m(this), b.attachToWebSocket(a), this._sessions.push(b), b
    }, g.prototype.closeSession = function(a) {
        var b;
        return a instanceof m ? a.state === g.SESSION_STATE.CLOSING ? !0 : (b = this._sessions.indexOf(a), b === -1 ? (this.setLastError(g.STATUS.INVALID_ARGS, "VVC.closeSession", "Invalid session, session is not registered with this vvc instance"), !1) : (a.onSessionClose(), this._sessions = this._sessions.splice(b, 1), !0)) : (this.setLastError(g.STATUS.INVALID_ARGS, "VVC.closeSession", "Invalid session, not instanceof VVCSession"), !1)
    }, g.prototype.createListener = function(a, b) {
        var c, d, e;
        if (a instanceof m) {
            if (b.length < g.MIN_CHANNEL_NAME_LEN || b.length > g.MAX_CHANNEL_NAME_LEN) return this.setLastError(g.STATUS.INVALID_ARGS, "VVC.createListener", 'Invalid name "' + b + '",' + " length must be between " + g.MIN_CHANNEL_NAME_LEN + " and " + g.MAX_CHANNEL_NAME_LEN + " characters."), null;
            d = this._findSessionListeners(a);
            for (e = 0; e < d.length; ++e)
                if (d[e].name === b) return this.setLastError(g.STATUS.INVALID_ARGS, "VVC.createListener", 'Invalid name "' + b + '",' + " a listener on this session" + " with this name already exists."), null;
            return c = new k(this, a, b), this._listeners.push(c), c
        }
        return this.setLastError(g.STATUS.INVALID_ARGS, "VVC.createListener", "Invalid session: not an instanceof VVCSession"), null
    }, g.prototype.closeListener = function(a) {
        var b = this._listeners.indexOf(a);
        return a instanceof k ? a.state === g.LISTENER_STATE.CLOSING ? !0 : b === -1 ? (this.setLastError(g.STATUS.INVALID_ARGS, "VVC.closeListener", "Invalid listener, listener is not registered with this vvc instance"), !1) : (a.onclose && a.onclose(), this._listeners = this._listeners.splice(b, 1), !0) : (this.setLastError(g.STATUS.INVALID_ARGS, "VVC.closeListener", "Invalid listener, not instanceof VVCListener"), !1)
    }, g.prototype._findListenerByName = function(a) {
        var b, c;
        for (c = 0; c < this._listeners.length; ++c) {
            b = this._listeners[c];
            if (b.name === a) return b
        }
        return null
    }, g.prototype._findSessionListeners = function(a) {
        var b, c, d = [];
        for (c = 0; c < this._listeners.length; ++c) b = this._listeners[c], (b.session === g.ALL_SESSIONS || b.session === a) && d.push(b);
        return d
    };
    var h = function(a, b, c) {
        return this.status = a, this.where = b, this.msg = c, this
    };
    g.prototype.getLastError = function() {
        return this._lastError
    }, g.prototype.setLastError = function(a, b, c) {
        this._lastError = new h(a, b, c), a !== g.STATUS.SUCCESS && console.error(b + ": " + c)
    }, "use strict";
    var i = function(a, b, c, d, e, f) {
        return this.id = b, this.name = c, this.priority = d || 0, this.flags = e || 0, this.timeout = f || 0, this.protocol = "binary", this.state = g.CHANNEL_STATE.INIT, this.onopen = this.onopen || null, this.onclose = this.onclose || null, this.onerror = this.onerror || null, this.onmessage = this.onmessage || null, this._session = a, this._vvcInstance = a.vvcInstance, this
    };
    g.CHANNEL_STATE = {
        INIT: 0,
        OPEN_FAILED: 1,
        OPEN: 2,
        CLOSING: 3,
        PEER_CLOSING: 4,
        PEER_CLOSED: 5,
        CLOSED: 6
    }, i.prototype.send = function(a) {
        return this._session.send(this, a)
    }, i.prototype.close = function() {
        return this._session.closeChannel(this)
    }, "use strict";
    var j = function(a) {
        i.call(this, a, g.CONTROL_CHANNEL_ID, g.CONTROL_CHANNEL_NAME, 0, 0, 0), this.state = g.CHANNEL_STATE.OPEN, this.versionMajor = 0, this.versionMinor = 0, this._rttSendTimeMS = 0
    };
    j.prototype = Object.create(i.prototype), g.CTRL_HEADER_SIZE = 4, g.CONTROL_CHANNEL_ID = 0, g.CONTROL_CHANNEL_NAME = "vvcctrl", g.CTRL_OP = {
        RECV_ACK: 1,
        INIT: 2,
        INIT_ACK: 3,
        OPEN_CHAN: 4,
        OPEN_CHAN_ACK: 5,
        OPEN_CHAN_CANCEL: 6,
        CLOSE_CHAN: 7,
        CLOSE_CHAN_ACK: 8,
        RTT: 9,
        RTT_ACK: 10
    }, g.CTRL_FLAG = {
        ODAT: 128
    }, g.INIT_EXT_FLAG = {
        EDAT: 32768
    }, g.INIT_EXT_TYPE = {
        VERSION: 1,
        PROBE: 2
    }, g.INIT_EXT_PROBE_FLAG = {
        SUPPORTED: 1
    }, g.OPEN_CHAN_STATUS = {
        SUCCESS: 0,
        REJECT: 1,
        TIMEOUT: 2
    }, g.CLOSE_CHAN_REASON = {
        NORMAL: 0,
        ERROR: 1
    }, g.CLOSE_CHAN_STATUS = {
        SUCCESS: 0,
        ERROR: 1
    }, g.CTRL_OP.SIZE = [], g.CTRL_OP.SIZE[g.CTRL_OP.RECV_ACK] = 0, g.CTRL_OP.SIZE[g.CTRL_OP.INIT] = 12, g.CTRL_OP.SIZE[g.CTRL_OP.INIT_ACK] = 12, g.CTRL_OP.SIZE[g.CTRL_OP.OPEN_CHAN] = 20, g.CTRL_OP.SIZE[g.CTRL_OP.OPEN_CHAN_ACK] = 12, g.CTRL_OP.SIZE[g.CTRL_OP.OPEN_CHAN_CANCEL] = 0, g.CTRL_OP.SIZE[g.CTRL_OP.CLOSE_CHAN] = 8, g.CTRL_OP.SIZE[g.CTRL_OP.CLOSE_CHAN_ACK] = 8, g.CTRL_OP.SIZE[g.CTRL_OP.RTT] = 0, g.CTRL_OP.SIZE[g.CTRL_OP.RTT_ACK] = 0, g.CTRL_OP.NAME = [], g.CTRL_OP.NAME[g.CTRL_OP.RECV_ACK] = "VVC.CTRL_OP.RECV_ACK", g.CTRL_OP.NAME[g.CTRL_OP.INIT] = "VVC.CTRL_OP.INIT", g.CTRL_OP.NAME[g.CTRL_OP.INIT_ACK] = "VVC.CTRL_OP.INIT_ACK", g.CTRL_OP.NAME[g.CTRL_OP.OPEN_CHAN] = "VVC.CTRL_OP.OPEN_CHAN", g.CTRL_OP.NAME[g.CTRL_OP.OPEN_CHAN_ACK] = "VVC.CTRL_OP.OPEN_CHAN_ACK", g.CTRL_OP.NAME[g.CTRL_OP.OPEN_CHAN_CANCEL] = "VVC.CTRL_OP.OPEN_CHAN_CANCEL", g.CTRL_OP.NAME[g.CTRL_OP.CLOSE_CHAN] = "VVC.CTRL_OP.CLOSE_CHAN", g.CTRL_OP.NAME[g.CTRL_OP.CLOSE_CHAN_ACK] = "VVC.CTRL_OP.CLOSE_CHAN_ACK", g.CTRL_OP.NAME[g.CTRL_OP.RTT] = "VVC.CTRL_OP.RTT", g.CTRL_OP.NAME[g.CTRL_OP.RTT_ACK] = "VVC.CTRL_OP.RTT_ACK", j.prototype.sendInit = function(a) {
        var b, c, d;
        a === undefined && (a = g.CTRL_OP.INIT);
        if (a !== g.CTRL_OP.INIT && a !== g.CTRL_OP.INIT_ACK) return this._vvcInstance.setLastError(g.STATUS.INVALID_ARGS, "VVCControlChannel.sendInit", "Invalid code,  expected INIT or INIT_ACK"), !1;
        d = this._createControlPacket(a), d.writeUint16(g.MAJOR_VER), d.writeUint16(g.MINOR_VER), d.writeUint32(g.CAPS_V10_1), d.writeUint32(g.CAPS_V10_2);
        if (g.MAJOR_VER >= 2) {
            b = 16 * g.SUPPORTED_VER.length, g.ENABLE_PROBE_CHANNEL && (b += 8), d.writeUint32(b);
            for (c = 0; c < g.SUPPORTED_VER.length; ++c) d.writeUint16(g.INIT_EXT_TYPE.VERSION), d.writeUint16(g.INIT_EXT_FLAG.EDAT), d.writeUint32(8), d.writeUint16(g.SUPPORTED_VER[c].MAJOR), d.writeUint16(g.SUPPORTED_VER[c].MINOR), d.writeUint32(g.SUPPORTED_VER[c].FLAGS);
            g.ENABLE_PROBE_CHANNEL && (d.writeUint16(g.INIT_EXT_TYPE.PROBE), d.writeUint16(0), d.writeUint32(g.INIT_EXT_PROBE_FLAG.SUPPORTED))
        }
        return this._sendControlPacket(d)
    }, j.prototype.sendRtt = function() {
        return this._rttSendTimeMS = Date.now(), this._sendControlPacket(this._createControlPacket(g.CTRL_OP.RTT))
    }, j.prototype.sendRecvAck = function(a) {
        var b;
        while (a > 65535) b = this._createControlPacket(g.CTRL_OP.RECV_ACK, 0, 65534), this._sendControlPacket(b), a -= 65535;
        return a > 0 && (b = this._createControlPacket(g.CTRL_OP.RECV_ACK, 0, a - 1), this._sendControlPacket(b)), !0
    }, j.prototype.sendOpenChannel = function(a, b) {
        var c, d = 0;
        return a instanceof i ? (d = 0, !b || (d = b.length), c = this._createControlPacket(g.CTRL_OP.OPEN_CHAN), c.writeUint32(a.id), c.writeUint32(a.priority), c.writeUint32(a.flags), c.writeUint32(a.timeout), c.writeUint16(0), c.writeUint8(0), c.writeUint8(a.name.length), c.writeUint32(d), c.writeStringASCII(a.name), d && c.writeArray(b), this._sendControlPacket(c)) : (this._vvcInstance.setLastError(g.STATUS.INVALID_ARGS, "VVCControlChannel.sendOpenChannel", "Invalid channel,  expected instanceof VVCChannel"), !1)
    }, j.prototype.sendOpenChannelAck = function(a, b, c) {
        var d = this._createControlPacket(g.CTRL_OP.OPEN_CHAN_ACK);
        return d.writeUint32(a.id), d.writeUint32(b), c ? (d.writeUint32(c.length), d.writeArray(c)) : d.writeUint32(0), this._sendControlPacket(d)
    }, j.prototype.sendCloseChannel = function(a, b) {
        var c = this._createControlPacket(g.CTRL_OP.CLOSE_CHAN);
        return c.writeUint32(a.id), c.writeUint32(b), this._sendControlPacket(c)
    }, j.prototype.sendCloseChannelAck = function(a, b) {
        var c = this._createControlPacket(g.CTRL_OP.CLOSE_CHAN_ACK);
        return c.writeUint32(a.id), c.writeUint32(b), this._sendControlPacket(c)
    }, j.prototype.onmessage = function(a) {
        var b = WMKS.Packet.createFromBuffer(a.data),
            c = b.readUint8(),
            d = b.readUint8(),
            e = b.readUint16();
        switch (c) {
            case g.CTRL_OP.INIT:
            case g.CTRL_OP.INIT_ACK:
                this._onInit(b, c);
                break;
            case g.CTRL_OP.RTT:
                this._onRtt(b);
                break;
            case g.CTRL_OP.RTT_ACK:
                this._onRttAck(b);
                break;
            case g.CTRL_OP.OPEN_CHAN:
                this._onOpenChannel(b);
                break;
            case g.CTRL_OP.OPEN_CHAN_ACK:
                this._onOpenChannelAck(b);
                break;
            case g.CTRL_OP.CLOSE_CHAN:
                this._onCloseChannel(b);
                break;
            case g.CTRL_OP.CLOSE_CHAN_ACK:
                this._onCloseChannelAck(b);
                break;
            case g.CTRL_OP.RECV_ACK:
                this._onRecvAck(b, e);
                break;
            default:
                return this._session.onSessionError(g.STATUS.PROTOCOL_ERROR, "VVCControlChannel.onmessage", "Unknown control opcode: " + c), !1
        }
        return !0
    }, j.prototype._onRtt = function(a) {
        var b;
        return this._checkErrorMinimumSize(g.CTRL_OP.RTT, a) ? (b = this._createControlPacket(g.CTRL_OP.RTT_ACK), this._sendControlPacket(b)) : !1
    }, j.prototype._onRttAck = function(a) {
        return this._checkErrorMinimumSize(g.CTRL_OP.RTT_ACK, a) ? (this._session.addRttTime(Date.now() - this._rttSendTimeMS), !0) : !1
    }, j.prototype._onRecvAck = function(a, b) {
        return this._checkErrorMinimumSize(g.CTRL_OP.RECV_ACK, a) ? !0 : !1
    }, j.prototype._isVersionSupported = function(a, b) {
        if (g.MAJOR_VER == a && g.MINOR_VER == b) return !0;
        for (var c = 0; c < g.SUPPORTED_VER.length; ++c)
            if (g.SUPPORTED_VER[c].MAJOR == a && g.SUPPORTED_VER[c].MINOR == b) return !0;
        return !1
    }, j.prototype._onInit = function(a, b) {
        var c, d, e, f, h = [];
        if (!this._checkErrorMinimumSize(b, a)) return !1;
        if (!this._checkErrorSessionState(b, g.SESSION_STATE.INIT)) return !1;
        c = a.readUint16(), d = a.readUint16(), e = a.readUint32(), f = a.readUint32();
        if (c >= 2) {
            var i;
            if (!this._checkErrorRemainingSize(b, a, 4)) return !1;
            i = a.readUint32();
            if (!this._checkErrorRemainingSize(b, a, i)) return !1;
            while (a.bytesRemaining() >= 8) {
                var j, k, l, m, n, o, p, q, r;
                j = a.readUint16(), k = a.readUint16(), l = a.readUint32(), k & g.INIT_EXT_FLAG.EDAT ? m = l : m = 0;
                if (!this._checkErrorRemainingSize(b, a, m)) return !1;
                q = a.bytesRemaining();
                switch (j) {
                    case g.INIT_EXT_TYPE.VERSION:
                        if (!this._checkErrorRemainingSize(b, a, 8)) return !1;
                        n = a.readUint16(), o = a.readUint16(), p = a.readUint32(), h.push({
                            major: n,
                            minor: o
                        });
                        break;
                    case g.INIT_EXT_TYPE.PROBE:
                        l & g.INIT_EXT_PROBE_FLAG.SUPPORTED && this._session.createProbeChannel()
                }
                r = q - a.bytesRemaining(), r < m && a.readArray(m - r)
            }
        }
        this.versionMajor = 0, this.versionMinor = 0;
        if (this._isVersionSupported(c, d)) this.versionMajor = c, this.versionMinor = d;
        else
            for (var s = 0; s < h.length; s++) n = h[s].major, o = h[s].minor, this._isVersionSupported(n, o) && n >= this.versionMajor && o >= this.versionMinor && (this.versionMajor = n, this.versionMinor = o);
        return this.versionMajor == 0 && this.versionMinor == 0 ? (this._vvcInstance.setLastError(g.STATUS.PROTOCOL_ERROR, "VVCControlChannel._onInit", "Failed to negotiate compatible protocol  version."), !1) : (b === g.CTRL_OP.INIT && this.sendInit(g.CTRL_OP.INIT_ACK), this._session.onConnect(), !0)
    }, j.prototype._onOpenChannel = function(a) {
        if (!this._checkErrorMinimumSize(g.CTRL_OP.OPEN_CHAN, a)) return !1;
        var b, c, d, e = a.readUint32(),
            f = a.readUint32(),
            h = a.readUint32(),
            i = a.readUint32(),
            j = a.readUint16(),
            k = a.readUint8(),
            l = a.readUint8(),
            m = a.readUint32();
        return this._checkErrorMinimumSize(g.CTRL_OP.OPEN_CHAN, a, l + m) ? (b = a.readStringASCII(l), c = a.readArray(m), d = this._session.createChannel(e, b, f, h, i), d.initialData = c, this._session.onPeerOpen(d), !0) : !1
    }, j.prototype._onOpenChannelAck = function(a) {
        var b, c, d, e;
        return this._checkErrorMinimumSize(g.CTRL_OP.OPEN_CHAN_ACK, a) ? (b = a.readUint32(), c = a.readUint32(), d = a.readUint32(), this._checkErrorMinimumSize(g.CTRL_OP.OPEN_CHAN_ACK, a, d) ? (e = a.readArray(d), this._checkErrorValidChannel(g.CTRL_OP.OPEN_CHAN_ACK, b, g.CHANNEL_STATE.INIT) ? (this._session.onChannelOpen(this._session.getChannel(b), c, e), !0) : !1) : !1) : !1
    }, j.prototype._onCloseChannel = function(a) {
        var b, c;
        return this._checkErrorMinimumSize(g.CTRL_OP.CLOSE_CHAN, a) ? (b = a.readUint32(), c = a.readUint32(), this._checkErrorValidChannel(g.CTRL_OP.CLOSE_CHAN, b) ? (this._session.onChannelClose(this._session.getChannel(b), c), !0) : !1) : !1
    }, j.prototype._onCloseChannelAck = function(a) {
        var b, c;
        return this._checkErrorMinimumSize(g.CTRL_OP.CLOSE_CHAN_ACK, a) ? (b = a.readUint32(), c = a.readUint32(), this._checkErrorValidChannel(g.CTRL_OP.CLOSE_CHAN_ACK, b, g.CHANNEL_STATE.CLOSING) ? (this._session.onChannelClose(this._session.getChannel(b), c), !0) : !1) : !1
    }, j.prototype._checkErrorMinimumSize = function(a, b, c) {
        var d = b.length - 4,
            e = g.CTRL_OP.SIZE[a];
        c = c || 0;
        if (d < e + c) {
            var f = g.CTRL_OP.NAME[a];
            return this._session.onSessionError(g.STATUS.PROTOCOL_ERROR, "VVCControlChannel._checkErrorMinimumSize", "Received invalid " + f + " message, " + "message too small, received " + d + " bytes, expected " + e + " + " + c), !1
        }
        return !0
    }, j.prototype._checkErrorRemainingSize = function(a, b, c) {
        var d = b.bytesRemaining();
        if (d < c) {
            var e = g.CTRL_OP.NAME[a];
            return this._session.onSessionError(g.STATUS.PROTOCOL_ERROR, "VVCControlChannel._checkErrorRemainingSize", "Received invalid " + e + " message, " + "message too small, expected " + (c - d) + " more bytes."), !1
        }
        return !0
    }, j.prototype._checkErrorSessionState = function(a, b) {
        var c = g.CTRL_OP.NAME[a];
        return this._session.state !== b ? (this._session.onSessionError(g.STATUS.PROTOCOL_ERROR, "VVCControlChannel._checkErrorSessionState", "Received invalid " + c + " message, " + "invaild session state, " + "found " + this._session.state + " expected " + b), !1) : !0
    }, j.prototype._checkErrorValidChannel = function(a, b, c) {
        var d = g.CTRL_OP.NAME[a],
            e = this._session.getChannel(b);
        return b === g.CONTROL_CHANNEL_ID ? (this._session.onSessionError(g.STATUS.PROTOCOL_ERROR, "VVCControlChannel._checkErrorValidChannel", "Received invalid " + d + " message, " + "unexpected use of control channel id"), !1) : e ? c !== undefined && e.state !== c ? (this._session.onSessionError(g.STATUS.PROTOCOL_ERROR, "VVCControlChannel._checkErrorValidChannel", "Received invalid " + d + " message, " + "unexpected channel state, " + "found " + e.state + " " + " expected " + c), !1) : !0 : (this._session.onSessionError(g.STATUS.PROTOCOL_ERROR, "VVCControlChannel._checkErrorValidChannel", "Received invalid " + d + " message, " + "unknown channel " + b), !1)
    }, j.prototype._createControlPacket = function(a, b, c) {
        var d = WMKS.Packet.createNewPacket();
        return c = c || 0, b = b || 0, d.control = {
            code: a,
            flags: b,
            param: c
        }, d.writeUint8(a), d.writeUint8(b), d.writeUint16(c), d
    }, j.prototype._sendControlPacket = function(a) {
        return a.length > g.CTRL_HEADER_SIZE && (a.control.flags |= g.CTRL_FLAG.ODAT, a.control.param = a.length - g.CTRL_HEADER_SIZE), a.setUint8(1, a.control.flags), a.setUint16(2, a.control.param), this.send(a.getData())
    }, "use strict";
    var k = function(a, b, c) {
        return this.name = c, this.session = b, this.state = g.LISTENER_STATE.ACTIVE, this.onconnect = null, this.onpeeropen = null, this.onclose = null, this._vvcInstance = a, this
    };
    g.LISTENER_STATE = {
        INIT: 0,
        ACTIVE: 1,
        CLOSING: 2
    }, k.prototype.close = function() {
        return this._vvcInstance.closeListener(this)
    }, k.prototype.matchName = function(a) {
        var b = this.name.indexOf("*");
        return b !== -1 ? this.name.substr(0, b) === a.substring(0, b) : this.name === a
    }, "use strict";
    var l = function(a) {
        i.call(this, a, g.PROBE_CHANNEL_ID, g.PROBE_CHANNEL_NAME, 0, 0, 0), this.state = g.CHANNEL_STATE.OPEN
    };
    l.prototype = Object.create(i.prototype), g.PROBE_CHANNEL_ID = 1, g.PROBE_CHANNEL_NAME = "vvcprobe", "use strict";
    var m = function(a, b) {
        var c = !1;
        return b && "server" in b && (c = b.server), this.state = g.SESSION_STATE.INIT, this.onerror = null, this.ontransportclose = null, this.ontransporterror = null, this._vvcInstance = a, this._server = c, this._channels = [], this._channelIdCtrl = this._server ? 3 : 2, this._bytesRead = 0, this._bytesRequested = g.CHUNK_COMMON_HEADER_SIZE, this._rttHistory = [], this._rttHistoryIndex = 0, this._chunk = {}, this._chunk.channel = 0, this._chunk.flags = 0, this._chunk.length = 0, this._chunk.ext = {}, this._chunk.ext.code = 0, this._chunk.ext.flags = 0, this._chunk.ext.param = 0, this._chunk.ext.length = 0, this._buffers = {}, this._buffers.ext = null, this._buffers.data = [], this._buffers.send = WMKS.Packet.createNewPacket(32), this._buffers.header = WMKS.Packet.createNewPacket(g.CHUNK_COMMON_HEADER_SIZE + g.CHUNK_LARGE_HEADER_SIZE + g.CHUNK_EXTENSION_HEADER_SIZE), this._receiveState = g.SESSION_RECEIVE_STATE.COMMON_HEADER, this._setReceiveState(g.SESSION_RECEIVE_STATE.COMMON_HEADER), this
    };
    g.CHUNK_COMMON_HEADER_SIZE = 4, g.CHUNK_LARGE_HEADER_SIZE = 4, g.CHUNK_EXTENSION_HEADER_SIZE = 4, g.CHUNK_MAX_LEN = 65536, g.CHUNK_LARGE_MAX_LEN = 4294966272, g.SESSION_STATE = {
            INIT: 0,
            ESTABLISHED: 1,
            ERROR: 2,
            CLOSING: 3
        }, g.SESSION_RECEIVE_STATE = {
            COMMON_HEADER: 0,
            LARGE_HEADER: 1,
            EXTENSION_HEADER: 2,
            EXTENSION_DATA: 3,
            DATA: 4
        }, g.CHUNK_FLAG = {
            PAD: 16,
            EXT: 32,
            LC: 64,
            FIN: 128
        }, g.CHUNK_EXT_FLAG = {
            ELST: 64,
            EDAT: 128
        }, g.CHUNK_EXT_CODE = {
            LARGE_CHANNEL_ID: 1
        }, m.prototype.close = function() {
            return this._vvcInstance.closeSession(this)
        }, m.prototype.openChannel = function(a, b, c, d, e) {
            var f;
            return b = b || 0, c = c || 0, d = d || 0, e = e || null, this._checkErrorNameLength("openChannel", a) ? this._checkErrorSessionState("openChannel", g.SESSION_STATE.ESTABLISHED) ? this._checkErrorInitialData("openChannel", e) ? (f = this.createChannel(this._nextChannelId(), a, b, c, d), this.controlChannel.sendOpenChannel(f, e), f) : null : null : null
        }, m.prototype.acceptChannel = function(a, b, c) {
            return b = b || 0, c = c || null, this._checkErrorSessionState("acceptChannel", g.SESSION_STATE.ESTABLISHED) ? this._checkErrorInitialData("acceptChannel", c) ? this._checkErrorIsChannel("acceptChannel", a) ? (this.controlChannel.sendOpenChannelAck(a, g.OPEN_CHAN_STATUS.SUCCESS, c), this.onChannelOpen(a, g.OPEN_CHAN_STATUS.SUCCESS, a.initialData), delete a.initialData, a) : null : null : null
        }, m.prototype.rejectChannel = function(a, b) {
            return b = b || null, this._checkErrorSessionState("rejectChannel", g.SESSION_STATE.ESTABLISHED) ? this._checkErrorInitialData("rejectChannel", b) ? this._checkErrorIsChannel("rejectChannel", a) ? (this.controlChannel.sendOpenChannelAck(a, g.OPEN_CHAN_STATUS.REJECT, b), a.state = g.CHANNEL_STATE.CLOSED, this._releaseChannel(a), !0) : !1 : !1 : !1
        }, m.prototype.closeChannel = function(a) {
            return this._checkErrorSessionState("closeChannel", g.SESSION_STATE.ESTABLISHED) ? this._checkErrorIsChannel("closeChannel", a) ? this._checkErrorChannelState("closeChannel", a, g.CHANNEL_STATE.OPEN) ? (a.state = g.CHANNEL_STATE.CLOSING, this.controlChannel.sendCloseChannel(a, g.CLOSE_CHAN_REASON.NORMAL), !0) : !1 : !1 : !1
        }, m.prototype.addRttTime = function(a) {
            this._rttHistory[this._rttHistoryIndex] = a, this._rttHistoryIndex++, this._rttHistoryIndex >= g.RTT_HISTORY_SIZE && (this._rttHistoryIndex = 0)
        }, m.prototype.attachToWebSocket = function(a) {
            var b = this;
            return this.socket = a, a.onopen = function(a) {
                this.binaryType = "arraybuffer", b._onTransportOpen()
            }, a.onclose = function(a) {
                b._onTransportClose(a)
            }, a.onerror = function(a) {
                b._onTransportError(a)
            }, a.onmessage = function(a) {
                if (a.data instanceof ArrayBuffer) b._onTransportRecv(new Uint8Array(a.data));
                else throw "Expected ArrayBuffer from websocket"
            }, a.readyState && a.onopen({}), !0
        }, m.prototype._nextChannelId = function() {
            var a = this._channelIdCtrl;
            return this._channelIdCtrl += 2, a
        }, m.prototype.createChannel = function(a, b, c, d, e) {
            var f;
            return c = c || 0, e = e || 0, d = d || 0, f = new i(this, a, b, c, d, e), this._channels[a] = f, this._buffers.data[a] = [], f
        }, m.prototype._releaseChannel = function(a) {
            a.state === g.CHANNEL_STATE.OPEN && this._vvcInstance.setLastError(g.STATUS.PROTOCOL_ERROR, "VVCSession._releaseChannel", "Releasing an open channel!"), delete this._channels[a.id], delete this._buffers.data[a.id]
        }, m.prototype.getChannel = function(a) {
            return this._channels[a] ? this._channels[a] : null
        }, m.prototype.onSessionError = function(a, b, c) {
            this.state = g.SESSION_STATE.ERROR, this._vvcInstance.setLastError(a, b, c), this.onerror && this.onerror(a)
        }, m.prototype.onSessionClose = function() {
            var a, b, c;
            this.state === g.SESSION_STATE.ERROR ? b = g.CLOSE_CHAN_REASON.ERROR : b = g.CLOSE_CHAN_REASON.NORMAL, this.state = g.SESSION_STATE.CLOSING;
            try {
                this.socket.close()
            } catch (d) {
                this._onTransportException(d)
            }
            for (c = 0; c < this._channels.length; ++c) {
                a = this._channels[c];
                if (a)
                    if (a.state === g.CHANNEL_STATE.INIT) this.onChannelOpen(a, g.STATUS.ERROR);
                    else if (a.state === g.CHANNEL_STATE.OPEN || a.state === g.CHANNEL_STATE.CLOSING) a.state = g.CHANNEL_STATE.CLOSING, this.onChannelClose(a, b)
            }
        }, m.prototype.onConnect = function() {
            var a, b, c;
            c = this._vvcInstance._findSessionListeners(this), this.state = g.SESSION_STATE.ESTABLISHED;
            for (a = 0; a < c.length; ++a) b = c[a], b.onconnect && b.onconnect(this)
        }, m.prototype.onPeerOpen = function(a) {
            var b, c, d;
            d = this._vvcInstance._findSessionListeners(this);
            for (b = 0; b < d.length; ++b) c = d[b], c.matchName(a.name) && c.onpeeropen && c.onpeeropen(this, a)
        }, m.prototype.onChannelOpen = function(a, b, c) {
            b === g.OPEN_CHAN_STATUS.SUCCESS ? (a.state = g.CHANNEL_STATE.OPEN, a.onopen && a.onopen(this._createEvent("open", {
                data: c
            }))) : (a.state = g.CHANNEL_STATE.OPEN_FAILED, this._releaseChannel(a), this.onChannelError(a))
        }, m.prototype.onChannelError = function(a) {
            a.onerror && a.onerror(this._createEvent("error"))
        }, m.prototype.onChannelMessage = function(a, b) {
            if (!a) {
                this.onSessionError(g.STATUS.PROTOCOL_ERROR, "VVCSession.onChannelMessage", "Unknown channel in chunk");
                return
            }
            a.onmessage && a.onmessage(this._createEvent("message", {
                data: b
            }))
        }, m.prototype.onChannelClose = function(a, b) {
            var c;
            b === g.CLOSE_CHAN_REASON.NORMAL ? (c = 1e3, a.state === g.CHANNEL_STATE.CLOSING ? a.state = g.CHANNEL_STATE.PEER_CLOSED : (a.state = g.CHANNEL_STATE.PEER_CLOSING, this.controlChannel.sendCloseChannelAck(a, g.CLOSE_CHAN_STATUS.SUCCESS))) : c = 1002, a.onclose && a.onclose(this._createEvent("close", {
                wasClean: b === g.CLOSE_CHAN_REASON.NORMAL,
                reason: b,
                code: c
            })), a.state = g.CHANNEL_STATE.CLOSED, this._releaseChannel(a)
        }, m.prototype.createProbeChannel = function() {
            this.probeChannel = new l(this), this._channels[this.probeChannel.id] = this.probeChannel, this._buffers.data[this.probeChannel.id] = []
        }, m.prototype._createControlChannel = function() {
            this.controlChannel = new j(this), this._channels[this.controlChannel.id] = this.controlChannel, this._buffers.data[this.controlChannel.id] = []
        }, m.prototype._onTransportOpen = function() {
            this._createControlChannel(), this._server || this.controlChannel.sendInit(g.CTRL_OP.INIT)
        }, m.prototype._onTransportClose = function(a) {
            this.state === g.SESSION_STATE.ESTABLISHED && this.onSessionError(g.TRANSPORT_ERROR, "VVCSession._onTransportClose", "The WebSocket closed whilst the session was open."), this.ontransportclose && this.ontransportclose(a)
        }, m.prototype._onTransportError = function(a) {
            this.onSessionError(g.TRANSPORT_ERROR, "VVCSession._onTransportError", "An error occurred in the WebSocket."), this.ontransporterror && this.ontransporterror(a)
        }, m.prototype._onTransportException = function(a) {
            this.onSessionError(g.TRANSPORT_ERROR, "VVCSession._onTransportException", "An exception occurred in the WebSocket: " + a.message), this.ontransporterror && this.ontransporterror(this._createEvent("error"))
        }, m.prototype._combineBuffers = function(a) {
            var b, c, d, e;
            if (a.length === 0) return null;
            e = 0;
            for (d = 0; d < a.length; ++d) e += a[d].length;
            c = new ArrayBuffer(e), b = new Uint8Array(c), e = 0;
            for (d = 0; d < a.length; ++d) b.set(a[d], e), e += a[d].length;
            return c
        }, m.prototype._setReceiveState = function(a) {
            this._receiveState = a;
            switch (a) {
                case g.SESSION_RECEIVE_STATE.COMMON_HEADER:
                    this._bytesRequested = g.CHUNK_COMMON_HEADER_SIZE, this._bytesRead = 0, this._buffers.header.reset();
                    break;
                case g.SESSION_RECEIVE_STATE.LARGE_HEADER:
                    this._bytesRequested += g.CHUNK_LARGE_HEADER_SIZE;
                    break;
                case g.SESSION_RECEIVE_STATE.EXTENSION_HEADER:
                    this._bytesRequested += g.CHUNK_EXTENSION_HEADER_SIZE;
                    break;
                case g.SESSION_RECEIVE_STATE.EXTENSION_DATA:
                    this._bytesRequested += this._chunk.ext.length;
                    break;
                case g.SESSION_RECEIVE_STATE.DATA:
                    this._bytesRequested += this._chunk.length
            }
        }, m.prototype._onTransportRecv = function(a) {
            var b, c, d, e;
            c = this._bytesRequested - this._bytesRead, d = Math.min(a.length, c), e = a.subarray(0, d), b = null;
            switch (this._receiveState) {
                case g.SESSION_RECEIVE_STATE.COMMON_HEADER:
                case g.SESSION_RECEIVE_STATE.LARGE_HEADER:
                case g.SESSION_RECEIVE_STATE.EXTENSION_HEADER:
                    b = this._buffers.header;
                    break;
                case g.SESSION_RECEIVE_STATE.EXTENSION_DATA:
                    b = this._buffers.ext;
                    break;
                case g.SESSION_RECEIVE_STATE.DATA:
                    this._buffers.data[this._chunk.channel].push(e), this._chunk.channel !== g.CONTROL_CHANNEL_ID && d && this.controlChannel.sendRecvAck(d)
            }
            b && b.writeArray(e), this._bytesRead += d;
            if (a.length < c) return;
            switch (this._receiveState) {
                case g.SESSION_RECEIVE_STATE.COMMON_HEADER:
                    this._chunk.channel = b.readUint8(), this._chunk.flags = b.readUint8(), this._chunk.length = b.readUint16() + 1, this._chunk.flags & g.CHUNK_FLAG.LC ? this._setReceiveState(g.SESSION_RECEIVE_STATE.LARGE_HEADER) : this._chunk.flags & g.CHUNK_FLAG.EXT ? this._setReceiveState(g.SESSION_RECEIVE_STATE.EXTENSION_HEADER) : this._setReceiveState(g.SESSION_RECEIVE_STATE.DATA);
                    break;
                case g.SESSION_RECEIVE_STATE.LARGE_HEADER:
                    this._chunk.length = b.readUint32() + 1, this._chunk.flags & g.CHUNK_FLAG.EXT ? this._setReceiveState(g.SESSION_RECEIVE_STATE.EXTENSION_HEADER) : this._setReceiveState(g.SESSION_RECEIVE_STATE.DATA);
                    break;
                case g.SESSION_RECEIVE_STATE.EXTENSION_HEADER:
                    this._chunk.ext.code = b.readUint8(), this._chunk.ext.flags = b.readUint8(), this._chunk.ext.param = b.readUint16(), this._chunk.ext.flags & g.CHUNK_EXT_FLAG.EDAT ? (this._chunk.ext.length = this._chunk.ext.param + 1, this._buffers.ext = new WMKS.Packet.createNewPacket(this._chunk
                        .ext.length), this._setReceiveState(g.SESSION_RECEIVE_STATE.EXTENSION_DATA)) : (this._chunk.ext.length = 0, this._chunk.ext.flags & g.CHUNK_EXT_FLAG.ELST ? this._setReceiveState(g.SESSION_RECEIVE_STATE.DATA) : this._setReceiveState(g.SESSION_RECEIVE_STATE.EXTENSION_HEADER));
                    break;
                case g.SESSION_RECEIVE_STATE.EXTENSION_DATA:
                    this._chunk.ext.code == g.CHUNK_EXT_CODE.LARGE_CHANNEL_ID && b.bytesRemaining() >= 4 && (this._chunk.channel = b.readUint32()), this._buffers.ext = null, this._chunk.ext.flags & g.CHUNK_EXT_FLAG.ELST ? this._setReceiveState(g.SESSION_RECEIVE_STATE.DATA) : this._setReceiveState(g.SESSION_RECEIVE_STATE.EXTENSION_HEADER);
                    break;
                case g.SESSION_RECEIVE_STATE.DATA:
                    this._chunk.flags & g.CHUNK_FLAG.FIN && (b = this._combineBuffers(this._buffers.data[this._chunk.channel]), this.onChannelMessage(this._channels[this._chunk.channel], b), this._buffers.data[this._chunk.channel] = []), this._setReceiveState(g.SESSION_RECEIVE_STATE.COMMON_HEADER)
            }
            a.length > d && this._onTransportRecv(a.subarray(d))
        }, m.prototype.send = function(a, b) {
            var c, d, e;
            if (!this._checkErrorIsChannel("send", a)) return !1;
            if (!this._checkErrorChannelState("send", a, g.CHANNEL_STATE.OPEN)) return !1;
            if (b instanceof Uint8Array || b instanceof ArrayBuffer) {
                c = this._buffers.send, c.reset(), c.writeUint8(a.id), e = b.byteLength, d = g.CHUNK_FLAG.FIN, e > g.CHUNK_MAX_LEN ? (c.writeUint8(g.CHUNK_FLAG.LC | d), c.writeUint16(0), c.writeUint32(e - 1)) : (c.writeUint8(d), c.writeUint16(e - 1));
                try {
                    this.socket.send(c.getData()), this.socket.send(b)
                } catch (f) {
                    this._onTransportException(f)
                }
                return !0
            }
            return this._vvcInstance.setLastError(g.STATUS.INVALID_ARGS, "VVCSession.send", "Invalid data, must be Uint8Array or ArrayBuffer"), !1
        }, m.prototype._createEvent = function(a, b) {
            var c = document.createEvent("Event");
            c.initEvent(a, !1, !1);
            for (var d in b) c[d] = b[d];
            return c
        }, m.prototype._checkErrorIsChannel = function(a, b) {
            return b instanceof i ? !0 : (this._vvcInstance.setLastError(g.STATUS.INVALID_ARGS, "VVCSession." + a, "Invalid channel, must be instanceof VVCChannel"), !1)
        }, m.prototype._checkErrorSessionState = function(a, b) {
            return this.state !== b ? (this._vvcInstance.setLastError(g.STATUS.INVALID_STATE, "VVCSession." + a, "Invalid state " + this.state + " expected " + b), !1) : !0
        }, m.prototype._checkErrorChannelState = function(a, b, c) {
            return b.state !== c ? (this._vvcInstance.setLastError(g.STATUS.INVALID_STATE, "VVCSession." + a, "Invalid state " + b.state + " expected " + c), !1) : !0
        }, m.prototype._checkErrorNameLength = function(a, b) {
            return b.length < g.MIN_CHANNEL_NAME_LEN || b.length > g.MAX_CHANNEL_NAME_LEN ? (this._vvcInstance.setLastError(g.STATUS.INVALID_ARGS, "VVCSession." + a, "Invalid name " + b + " length must be between " + g.MIN_CHANNEL_NAME_LEN + " and " + g.MAX_CHANNEL_NAME_LEN + " bytes"), !1) : !0
        }, m.prototype._checkErrorInitialData = function(a, b) {
            return !b || b instanceof Uint8Array ? b && b.length > g.MAX_INITIAL_DATA_LEN ? (this._vvcInstance.setLastError(g.STATUS.INVALID_ARGS, "VVCSession." + a, "Invalid initial data, must be smaller than " + g.MAX_INITIAL_DATA_LEN + " bytes"), !1) : !0 : (this._vvcInstance.setLastError(g.STATUS.INVALID_ARGS, "VVCSession." + a, "Invalid initial data, must be instanceof Uint8Array"), !1)
        }, WMKS.RelativeMouseHandler = function(a) {
            "use strict";
            if (!a || !a.canvas || !a.widgetProto) return WMKS.LOGGER.warn("Invalid params set for RelativeMouseHandler."), null;
            var b = a.widgetProto,
                c = a.canvas,
                d = a.onToggle,
                e = {
                    inputProxy: null,
                    cursorIcon: null,
                    clickFeedback: null,
                    dragFeedback: null,
                    pulseFeedback: null,
                    scrollFeedback: null,
                    keypad: null,
                    relativePad: null
                };
            this.toggleRelativePad = function(a) {
                e.relativePad && (a = $.extend({}, a, {
                    toggleCallback: d
                }), e.relativePad.toggle(a))
            }, this.installMouseHandlers = function() {
                var a = this,
                    b = c.parent();
                c.css({
                    "-webkit-user-select": "none",
                    "-webkit-touch-callout": "none"
                }), e.cursorIcon = $("<div/>").addClass("feedback-container cursor-icon").appendTo(b), e.clickFeedback = $("<div/>").addClass("feedback-container tap-icon").appendTo(b), e.dragFeedback = $("<div/>").addClass("feedback-container drag-icon").appendTo(b), e.pulseFeedback = $("<div/>").addClass("feedback-container pulse-icon").appendTo(b), e.scrollFeedback = $("<div/>").addClass("feedback-container scroll-icon").appendTo(b)
            }, this.initializeRelativeMouseFeature = function() {
                e.relativePad = new WMKS.relativePadManager(b, c), e.relativePad.initialize()
            }, this.moveCursor = function(a, b) {
                e.cursorIcon && e.cursorIcon.css({
                    left: a,
                    top: b
                })
            }, this.setCursorVisibility = function(a) {
                e.cursorIcon && (a ? e.cursorIcon.show() : e.cursorIcon.hide())
            }, this.destroy = function() {
                b = null, c = null, e = null
            }
        },
        function() {
            "use strict",
            WMKS.CONST.RELATIVEPAD = {
                STATE: {
                    idle: 0,
                    click: 1,
                    move: 3,
                    scroll: 4
                }
            },
            WMKS.relativePadManager = function(a, b) {
                WMKS.dialogManager.call(this), this._widget = a, this._canvas = b, this.state = WMKS.CONST.RELATIVEPAD.STATE.idle, this.history = [], $.extend(this.options, {
                    name: "RELATIVEPAD",
                    speedControlMinMovePx: 5,
                    accelerator: 10,
                    minSpeed: 1,
                    maxSpeed: 10
                }), WMKS.LOGGER.warn("relativepad : " + this.options.name)
            },
            WMKS.relativePadManager.prototype = new WMKS.dialogManager,
            WMKS.relativePadManager.prototype.getTrackpadHtml = function() {
                var a = '<div id="relativePad" class="relativepad-container">                   <div class="left-border"></div>                   <div id="relativePadSurface" style="height:200px; border:1px solid black;"></div>                   <div class="right-border"></div>                   <div class="bottom-border">                      <div class="button-container">                         <div id="relativepadLeft" class="button-left"></div>                         <div id="relativepadRight" class="button-right"></div>                      </div>                   </div>               </div>';
                return a
            },
            WMKS.relativePadManager.prototype.create = function() {
                var a, b = this;
                return !this._widget || !this._canvas ? (WMKS.LOGGER.debug("Trackpad dialog creation has been aborted. Widget or Canvas is not ready."), null) : (a = $(this.getTrackpadHtml()), a.dialog({
                    autoOpen: !1,
                    closeOnEscape: !0,
                    resizable: !1,
                    position: {
                        my: "center",
                        at: "center",
                        of: this._canvas
                    },
                    zIndex: 1e3,
                    draggable: !0,
                    dialogClass: "relativepad-wrapper",
                    close: function(a) {
                        b.sendUpdatedState(!1)
                    },
                    create: function(a) {
                        b.layout($(this).parent())
                    }
                }), a)
            },
            WMKS.relativePadManager.prototype.init = function() {
                var a = this.dialog,
                    b = this,
                    c, d, e;
                if (!a) {
                    WMKS.LOGGER.debug("Relativepad init aborted. Dialog is not created successfully.");
                    return
                }
                this._widget.requestElementReposition(a.parent(), !0), c = a.find("#relativePadSurface").on("mousemove", function(a) {
                    return b.relativepadMouseMove(a.originalEvent)
                }).on("mouseup", function(a) {
                    return b.relativepadMouseClick(a, 0)
                }).on("mousedown", function(a) {
                    return b.relativepadMouseClick(a, 1)
                }).on("contextmenu", function() {
                    return !1
                })
            },
            WMKS.relativePadManager.prototype.disconnect = function() {
                var a = this.dialog,
                    b;
                if (!a) return;
                b = a.find("#relativepadSurface").off("mousemove").off("mouseup").off("mousedown")
            },
            WMKS.relativePadManager.prototype.layout = function(a) {
                var b = this._canvas,
                    c, d;
                if (!a || !b) return;
                c = a.parent(), d = b.parent(), c !== d && d.append(a)
            },
            WMKS.relativePadManager.prototype.relativepadMouseMove = function(a) {
                var b, c, d, e, f = $(a.target),
                    g = this._widget;
                return this.state === WMKS.CONST.RELATIVEPAD.STATE.idle ? !1 : (b = a.pageX, c = a.pageY, c < f.offset().top || c > f.offset().top + f.height() || b < f.offset().left || b > f.offset().left + f.width() ? (this.state === WMKS.CONST.RELATIVEPAD.STATE.move && (this.state = WMKS.CONST.RELATIVEPAD.STATE.idle, this.history.length = 0), !1) : (this.state === WMKS.CONST.RELATIVEPAD.STATE.move && (d = this.computeNewCursorLocation(b, c), WMKS.VNCDecoder.cursorPosition && (e = this._widget.getRelativeMouseCanvasPosition(WMKS.VNCDecoder.cursorPosition), g._relativeMouseHandler.moveCursor(e.x, e.y)), g.sendMouseMoveMessage(d), this.history.shift(), this.history.push({
                    x: b,
                    y: c
                })), this.state === WMKS.CONST.RELATIVEPAD.STATE.click && (this.state = WMKS.CONST.RELATIVEPAD.STATE.move, this.history.push({
                    x: b,
                    y: c
                }, {
                    x: b,
                    y: c
                }, {
                    x: b,
                    y: c
                })), !1))
            },
            WMKS.relativePadManager.prototype.computeNewCursorLocation = function(a, b) {
                var c, d = {
                    x: 0,
                    y: 0
                };
                return c = WMKS.UTIL.getLineLength(a - this.history[2].x, b - this.history[2].y), isNaN(c) || c === 0 ? d : (c < this.options.speedControlMinMovePx ? (d.x = a - this.history[2].x, d.y = b - this.history[2].y) : (c = this.computeMovingDistance(a, b), d.x = Math.floor(c[0]), d.y = Math.floor(c[1])), d)
            },
            WMKS.relativePadManager.prototype.computeMovingDistance = function(a, b) {
                var c, d, e, f;
                return c = this.getTrackpadSpeed(a, this.history[0].x, this.history[1].x, this.history[2].x), d = this.getTrackpadSpeed(b, this.history[0].y, this.history[1].y, this.history[2].y), e = WMKS.UTIL.getLineLength(c, d), f = e * this.options.accelerator, f > this.options.maxSpeed ? f = this.options.maxSpeed : f < this.options.minSpeed && (f = this.options.minSpeed), [c * f, d * f]
            },
            WMKS.relativePadManager.prototype.getTrackpadSpeed = function(a, b, c, d) {
                return a * .3 + b * .1 - c * .1 - d * .3
            },
            WMKS.relativePadManager.prototype.relativepadMouseClick = function(a, b) {
                if (this.state == WMKS.CONST.RELATIVEPAD.STATE.idle) return this.state = WMKS.CONST.RELATIVEPAD.STATE.click, !1;
                this.state = WMKS.CONST.RELATIVEPAD.STATE.click;
                var c = a || window.event,
                    d = {
                        x: 0,
                        y: 0
                    },
                    e;
                return c.which ? c.which == 1 ? e = 1 : c.which == 2 ? e = 4 : c.which == 3 && (e = 2) : c.button == 0 ? e = 1 : c.button == 1 ? e = 4 : c.button == 2 && (e = 2), this._widget.sendMouseButtonMessage(d, b, e), this.resetRelativepadState(), !1
            },
            WMKS.relativePadManager.prototype.resetRelativepadState = function() {
                this.history.length = 0
            }
        }(), $.widget("wmks.wmks", WMKS.widgetProto)
})(),
function() {
    $.extend(WMKS.CONST, {
        Position: {
            CENTER: 0,
            LEFT_TOP: 1
        },
        ConnectionState: {
            CONNECTING: "connecting",
            CONNECTED: "connected",
            DISCONNECTED: "disconnected"
        },
        Events: {
            CONNECTION_STATE_CHANGE: "connectionstatechange",
            REMOTE_SCREEN_SIZE_CHANGE: "screensizechange",
            FULL_SCREEN_CHANGE: "fullscreenchange",
            ERROR: "error",
            KEYBOARD_LEDS_CHANGE: "keyboardledschanged",
            HEARTBEAT: "heartbeat",
            AUDIO: "audio",
            COPY: "copy",
            TOGGLE: "toggle"
        },
        ErrorType: {
            AUTHENTICATION_FAILED: "authenticationfailed",
            WEBSOCKET_ERROR: "websocketerror",
            PROTOCOL_ERROR: "protocolerror"
        },
        AudioEncodeType: {
            VORBIS: "vorbis",
            OPUS: "opus",
            AAC: "aac"
        },
        InputDeviceType: {
            KEYBOARD: 0,
            EXTENDED_KEYBOARD: 1,
            TRACKPAD: 2
        }
    }), WMKS.CONST.KB.VScanMap["ja-JP_106/109"] = {
        32: 57,
        49: 2,
        33: 2,
        50: 3,
        34: 3,
        51: 4,
        35: 4,
        52: 5,
        36: 5,
        53: 6,
        37: 6,
        54: 7,
        38: 7,
        55: 8,
        39: 8,
        56: 9,
        40: 9,
        57: 10,
        41: 10,
        48: 11,
        45: 12,
        61: 12,
        94: 13,
        126: 13,
        157: 125,
        124: 61565,
        113: 16,
        81: 16,
        119: 17,
        87: 17,
        101: 18,
        69: 18,
        114: 19,
        82: 19,
        116: 20,
        84: 20,
        121: 21,
        89: 21,
        117: 22,
        85: 22,
        105: 23,
        73: 23,
        111: 24,
        79: 24,
        112: 25,
        80: 25,
        64: 26,
        96: 26,
        91: 27,
        123: 27,
        93: 43,
        125: 43,
        97: 30,
        65: 30,
        115: 31,
        83: 31,
        100: 32,
        68: 32,
        102: 33,
        70: 33,
        103: 34,
        71: 34,
        104: 35,
        72: 35,
        106: 36,
        74: 36,
        107: 37,
        75: 37,
        108: 38,
        76: 38,
        59: 39,
        43: 39,
        58: 40,
        42: 40,
        122: 44,
        90: 44,
        120: 45,
        88: 45,
        99: 46,
        67: 46,
        118: 47,
        86: 47,
        98: 48,
        66: 48,
        110: 49,
        78: 49,
        109: 50,
        77: 50,
        44: 51,
        60: 51,
        46: 52,
        62: 52,
        47: 53,
        63: 53,
        95: 115,
        92: 125,
        13: 28,
        165: 125,
        124: 125,
        226: 115,
        29: 123,
        28: 121,
        242: 112,
        243: 41,
        244: 41
    }, WMKS.CONST.KB.VScanMap["de-DE"] = {
        32: 57,
        176: 41,
        94: 41,
        160: 41,
        192: 41,
        180: 13,
        96: 13,
        49: 2,
        33: 2,
        50: 3,
        34: 3,
        51: 4,
        167: 4,
        52: 5,
        36: 5,
        53: 6,
        37: 6,
        54: 7,
        38: 7,
        55: 8,
        47: 8,
        56: 9,
        40: 9,
        57: 10,
        41: 10,
        48: 11,
        61: 11,
        223: 12,
        63: 12,
        187: 13,
        113: 16,
        81: 16,
        119: 17,
        87: 17,
        101: 18,
        69: 18,
        114: 19,
        82: 19,
        116: 20,
        84: 20,
        122: 21,
        90: 21,
        117: 22,
        85: 22,
        105: 23,
        73: 23,
        111: 24,
        79: 24,
        112: 25,
        80: 25,
        252: 26,
        220: 26,
        43: 27,
        42: 27,
        35: 43,
        39: 43,
        97: 30,
        65: 30,
        115: 31,
        83: 31,
        100: 32,
        68: 32,
        102: 33,
        70: 33,
        103: 34,
        71: 34,
        104: 35,
        72: 35,
        106: 36,
        74: 36,
        107: 37,
        75: 37,
        108: 38,
        76: 38,
        246: 39,
        214: 39,
        228: 40,
        196: 40,
        121: 44,
        89: 44,
        120: 45,
        88: 45,
        99: 46,
        67: 46,
        118: 47,
        86: 47,
        98: 48,
        66: 48,
        110: 49,
        78: 49,
        109: 50,
        77: 50,
        44: 51,
        59: 51,
        46: 52,
        58: 52,
        45: 53,
        95: 53,
        60: 86,
        62: 86,
        13: 28,
        226: 30,
        225: 30,
        224: 30,
        234: 18,
        233: 18,
        232: 18,
        238: 23,
        237: 23,
        236: 23,
        244: 24,
        243: 24,
        242: 24,
        251: 22,
        250: 22,
        249: 22,
        253: 44,
        8220: 3,
        178: 3,
        182: 4,
        179: 4,
        124: 8,
        123: 9,
        91: 9,
        125: 10,
        93: 10,
        8800: 11,
        191: 12,
        92: 12,
        64: 16,
        8364: 18,
        5: 18,
        177: 27,
        126: 27,
        181: 50,
        188: 86,
        8804: 86
    }, WMKS.CONST.KB.VScanMap["it-IT"] = {
        32: 57,
        92: 41,
        124: 41,
        94: 13,
        180: 13,
        49: 2,
        33: 2,
        50: 3,
        34: 3,
        51: 4,
        163: 4,
        52: 5,
        36: 5,
        53: 6,
        37: 6,
        54: 7,
        38: 7,
        55: 8,
        47: 8,
        56: 9,
        40: 9,
        57: 10,
        41: 10,
        48: 11,
        61: 11,
        39: 12,
        63: 12,
        236: 13,
        113: 16,
        81: 16,
        119: 17,
        87: 17,
        101: 18,
        69: 18,
        114: 19,
        82: 19,
        116: 20,
        84: 20,
        121: 21,
        89: 21,
        117: 22,
        85: 22,
        105: 23,
        73: 23,
        111: 24,
        79: 24,
        112: 25,
        80: 25,
        232: 26,
        233: 26,
        43: 27,
        42: 27,
        249: 43,
        167: 43,
        97: 30,
        65: 30,
        115: 31,
        83: 31,
        100: 32,
        68: 32,
        102: 33,
        70: 33,
        103: 34,
        71: 34,
        104: 35,
        72: 35,
        106: 36,
        74: 36,
        107: 37,
        75: 37,
        108: 38,
        76: 38,
        242: 39,
        231: 39,
        224: 40,
        1224: 40,
        176: 40,
        122: 44,
        90: 44,
        120: 45,
        88: 45,
        99: 46,
        67: 46,
        118: 47,
        86: 47,
        98: 48,
        66: 48,
        110: 49,
        78: 49,
        109: 50,
        77: 50,
        44: 51,
        59: 51,
        46: 52,
        58: 52,
        45: 53,
        95: 53,
        60: 86,
        62: 86,
        5: 18,
        1037: 40,
        30: 26,
        29: 27,
        171: 27,
        1109: 39,
        186: 39,
        64: 39,
        8364: 18,
        35: 40,
        91: 26,
        93: 27,
        123: 26,
        125: 27,
        13: 28
    }, WMKS.CONST.KB.VScanMap["es-ES"] = {
        32: 57,
        161: 13,
        191: 13,
        186: 41,
        170: 41,
        49: 2,
        124: 2,
        33: 2,
        50: 3,
        64: 3,
        34: 3,
        51: 4,
        1035: 4,
        183: 4,
        52: 5,
        36: 5,
        53: 6,
        8364: 6,
        37: 6,
        54: 7,
        172: 7,
        38: 7,
        55: 8,
        47: 8,
        56: 9,
        40: 9,
        57: 10,
        41: 10,
        48: 11,
        61: 11,
        39: 12,
        63: 12,
        236: 13,
        113: 16,
        81: 16,
        119: 17,
        87: 17,
        101: 18,
        5: 18,
        69: 18,
        114: 19,
        82: 19,
        116: 20,
        84: 20,
        121: 21,
        89: 21,
        117: 22,
        85: 22,
        105: 23,
        73: 23,
        111: 24,
        79: 24,
        112: 25,
        80: 25,
        219: 26,
        221: 26,
        43: 27,
        42: 27,
        29: 27,
        93: 27,
        171: 27,
        231: 43,
        199: 43,
        125: 43,
        28: 43,
        97: 30,
        65: 30,
        115: 31,
        83: 31,
        100: 32,
        68: 32,
        102: 33,
        70: 33,
        103: 34,
        71: 34,
        104: 35,
        72: 35,
        106: 36,
        74: 36,
        107: 37,
        75: 37,
        108: 38,
        76: 38,
        209: 39,
        241: 39,
        222: 40,
        122: 44,
        90: 44,
        120: 45,
        88: 45,
        99: 46,
        67: 46,
        118: 47,
        86: 47,
        98: 48,
        66: 48,
        110: 49,
        78: 49,
        109: 50,
        77: 50,
        44: 51,
        59: 51,
        46: 52,
        58: 52,
        45: 53,
        95: 53,
        60: 86,
        62: 86,
        13: 28,
        226: 30,
        225: 30,
        224: 30,
        227: 30,
        228: 30,
        234: 18,
        233: 18,
        232: 18,
        235: 18,
        238: 23,
        237: 23,
        236: 23,
        239: 23,
        244: 24,
        243: 24,
        242: 24,
        245: 24,
        246: 24,
        251: 22,
        250: 22,
        249: 22,
        252: 22,
        255: 21,
        1034: 40,
        1048: 41,
        91: 26,
        92: 41,
        35: 4,
        126: 5,
        192: 26,
        123: 40
    }, WMKS.CONST.KB.VScanMap["pt-BR"] = {
        32: 57,
        176: 115,
        186: 43,
        170: 27,
        49: 2,
        33: 2,
        50: 3,
        64: 3,
        34: 41,
        51: 4,
        1035: 4,
        183: 4,
        52: 5,
        36: 5,
        53: 6,
        37: 6,
        54: 7,
        172: 7,
        38: 8,
        55: 8,
        56: 9,
        40: 10,
        57: 10,
        41: 11,
        48: 11,
        61: 13,
        39: 41,
        63: 115,
        47: 115,
        236: 13,
        113: 16,
        81: 16,
        119: 17,
        87: 17,
        101: 18,
        5: 18,
        69: 18,
        114: 19,
        82: 19,
        116: 20,
        84: 20,
        121: 21,
        89: 21,
        117: 22,
        85: 22,
        105: 23,
        73: 23,
        111: 24,
        79: 24,
        112: 25,
        80: 25,
        219: 26,
        43: 13,
        42: 9,
        29: 27,
        93: 43,
        171: 27,
        231: 39,
        199: 39,
        125: 43,
        28: 43,
        97: 30,
        65: 30,
        115: 31,
        83: 31,
        100: 32,
        68: 32,
        102: 33,
        70: 33,
        103: 34,
        71: 34,
        104: 35,
        72: 35,
        106: 36,
        74: 36,
        107: 37,
        75: 37,
        108: 38,
        76: 38,
        209: 39,
        241: 39,
        222: 40,
        122: 44,
        90: 44,
        120: 45,
        88: 45,
        99: 46,
        67: 46,
        118: 47,
        86: 47,
        98: 48,
        66: 48,
        110: 49,
        78: 49,
        109: 50,
        77: 50,
        44: 51,
        59: 53,
        46: 52,
        58: 53,
        45: 12,
        95: 12,
        60: 51,
        62: 52,
        13: 28,
        226: 30,
        225: 30,
        224: 30,
        227: 30,
        228: 30,
        234: 18,
        233: 18,
        232: 18,
        235: 18,
        238: 23,
        237: 23,
        236: 23,
        239: 23,
        244: 24,
        243: 24,
        242: 24,
        245: 24,
        246: 24,
        251: 22,
        250: 22,
        249: 22,
        252: 22,
        255: 21,
        1034: 40,
        1048: 41,
        91: 27,
        92: 86,
        124: 86,
        35: 4,
        126: 40,
        192: 26,
        123: 27,
        185: 2,
        8220: 3,
        178: 3,
        182: 4,
        179: 4,
        163: 5,
        162: 6,
        167: 13
    }, WMKS.CONST.KB.VScanMap["pt-PT"] = {
        32: 57,
        167: 41,
        177: 41,
        49: 2,
        33: 2,
        50: 3,
        64: 3,
        34: 3,
        51: 4,
        35: 4,
        163: 4,
        52: 5,
        36: 5,
        53: 6,
        8364: 6,
        37: 6,
        54: 7,
        38: 7,
        55: 8,
        47: 8,
        56: 9,
        40: 9,
        93: 10,
        57: 10,
        41: 10,
        48: 11,
        125: 11,
        61: 11,
        39: 12,
        63: 12,
        43: 26,
        42: 26,
        168: 26,
        171: 13,
        187: 13,
        113: 16,
        81: 16,
        119: 17,
        87: 17,
        101: 18,
        69: 18,
        114: 19,
        82: 19,
        116: 20,
        84: 20,
        121: 21,
        89: 21,
        117: 22,
        85: 22,
        105: 23,
        73: 23,
        111: 24,
        79: 24,
        112: 25,
        80: 25,
        186: 40,
        170: 40,
        221: 27,
        192: 27,
        333: 30,
        180: 27,
        96: 27,
        97: 30,
        65: 30,
        115: 31,
        83: 31,
        100: 32,
        68: 32,
        102: 33,
        70: 33,
        103: 34,
        71: 34,
        104: 35,
        72: 35,
        106: 36,
        74: 36,
        107: 37,
        75: 37,
        108: 38,
        76: 38,
        231: 39,
        199: 39,
        222: 43,
        126: 43,
        94: 43,
        176: 43,
        92: 41,
        124: 41,
        60: 86,
        62: 86,
        122: 44,
        90: 44,
        120: 45,
        88: 45,
        99: 46,
        67: 46,
        118: 47,
        86: 47,
        98: 48,
        66: 48,
        110: 49,
        78: 49,
        109: 50,
        77: 50,
        44: 51,
        59: 51,
        46: 52,
        58: 52,
        45: 53,
        95: 53,
        13: 28,
        226: 30,
        194: 30,
        225: 30,
        224: 30,
        227: 30,
        228: 30,
        234: 18,
        202: 18,
        233: 18,
        232: 18,
        200: 18,
        235: 18,
        238: 23,
        206: 23,
        237: 23,
        236: 23,
        204: 23,
        239: 23,
        244: 24,
        212: 24,
        243: 24,
        242: 24,
        210: 24,
        245: 24,
        246: 24,
        251: 22,
        219: 22,
        250: 22,
        249: 22,
        217: 22,
        252: 22,
        241: 49,
        5: 6,
        29: 10,
        91: 9,
        123: 88
    }, WMKS.CONST.KB.VScanMap["fr-FR"] = {
        32: 57,
        97: 16,
        65: 16,
        226: 16,
        227: 16,
        228: 16,
        122: 17,
        90: 17,
        101: 18,
        69: 18,
        234: 18,
        235: 18,
        114: 19,
        82: 19,
        116: 20,
        84: 20,
        121: 21,
        89: 21,
        255: 21,
        117: 22,
        85: 22,
        251: 22,
        252: 22,
        105: 23,
        73: 23,
        238: 23,
        239: 23,
        111: 24,
        79: 24,
        244: 24,
        245: 24,
        246: 24,
        112: 25,
        80: 25,
        113: 30,
        81: 30,
        115: 31,
        83: 31,
        100: 32,
        68: 32,
        102: 33,
        70: 33,
        103: 34,
        71: 34,
        104: 35,
        72: 35,
        106: 36,
        74: 36,
        107: 37,
        75: 37,
        108: 38,
        76: 38,
        109: 39,
        77: 39,
        49: 2,
        38: 2,
        50: 3,
        233: 3,
        201: 3,
        51: 4,
        34: 4,
        52: 5,
        39: 5,
        53: 6,
        40: 6,
        54: 7,
        45: 7,
        55: 8,
        232: 8,
        56: 9,
        95: 9,
        295: 7,
        57: 10,
        231: 10,
        48: 11,
        224: 11,
        60: 86,
        62: 86,
        119: 44,
        87: 44,
        120: 45,
        88: 45,
        99: 46,
        67: 46,
        118: 47,
        86: 47,
        98: 48,
        66: 48,
        110: 49,
        78: 49,
        44: 50,
        63: 50,
        59: 51,
        46: 51,
        47: 52,
        58: 52,
        36: 27,
        29: 27,
        163: 27,
        94: 26,
        221: 26,
        168: 26,
        219: 26,
        160: 26,
        249: 40,
        37: 40,
        42: 43,
        181: 43,
        178: 41,
        41: 12,
        176: 12,
        61: 13,
        43: 13,
        33: 53,
        167: 53,
        35: 4,
        64: 11,
        91: 6,
        93: 12,
        169: 12,
        31: 13,
        173: 13,
        123: 5,
        125: 13,
        8364: 18,
        5: 18,
        124: 7,
        164: 27,
        126: 3,
        92: 9,
        94: 10,
        28: 10,
        96: 8,
        13: 28
    }, WMKS.CONST.KB.VScanMap["fr-CH"] = {
        32: 57,
        49: 2,
        43: 2,
        166: 2,
        50: 3,
        34: 3,
        64: 3,
        51: 4,
        42: 4,
        35: 4,
        52: 5,
        231: 5,
        53: 6,
        37: 6,
        54: 7,
        38: 7,
        172: 7,
        55: 8,
        47: 8,
        124: 8,
        56: 9,
        40: 9,
        162: 9,
        57: 10,
        41: 10,
        48: 11,
        61: 11,
        39: 12,
        63: 12,
        222: 12,
        31: 12,
        219: 12,
        180: 12,
        187: 13,
        160: 13,
        126: 13,
        94: 13,
        113: 16,
        81: 16,
        119: 17,
        87: 17,
        101: 18,
        69: 18,
        8364: 18,
        5: 18,
        234: 18,
        235: 18,
        114: 19,
        82: 19,
        116: 20,
        84: 20,
        122: 21,
        90: 21,
        117: 22,
        85: 22,
        251: 22,
        250: 22,
        249: 22,
        105: 23,
        73: 23,
        238: 23,
        239: 23,
        237: 23,
        236: 23,
        111: 24,
        79: 24,
        245: 24,
        244: 24,
        243: 24,
        242: 24,
        112: 25,
        80: 25,
        97: 30,
        65: 30,
        226: 30,
        227: 30,
        225: 30,
        115: 31,
        83: 31,
        100: 32,
        68: 32,
        102: 33,
        70: 33,
        103: 34,
        71: 34,
        104: 35,
        72: 35,
        106: 36,
        74: 36,
        107: 37,
        75: 37,
        108: 38,
        76: 38,
        60: 86,
        62: 86,
        96: 86,
        92: 86,
        121: 44,
        89: 44,
        255: 44,
        120: 45,
        88: 45,
        99: 46,
        67: 46,
        118: 47,
        86: 47,
        98: 48,
        66: 48,
        110: 49,
        78: 49,
        109: 50,
        77: 50,
        44: 51,
        59: 51,
        46: 52,
        58: 52,
        45: 53,
        95: 53,
        226: 30,
        194: 30,
        196: 30,
        193: 30,
        225: 30,
        224: 30,
        227: 30,
        228: 30,
        195: 30,
        234: 18,
        202: 18,
        201: 18,
        203: 18,
        233: 18,
        232: 18,
        200: 18,
        235: 18,
        238: 23,
        206: 23,
        205: 23,
        207: 23,
        237: 23,
        236: 23,
        204: 23,
        239: 23,
        244: 24,
        212: 24,
        211: 24,
        214: 24,
        243: 24,
        242: 24,
        210: 24,
        245: 24,
        246: 24,
        213: 24,
        251: 22,
        218: 22,
        220: 22,
        250: 22,
        249: 22,
        217: 22,
        252: 22,
        233: 39,
        232: 26,
        91: 26,
        224: 40,
        123: 40,
        1224: 40,
        246: 39,
        252: 26,
        228: 40,
        221: 27,
        161: 27,
        192: 27,
        168: 27,
        29: 27,
        33: 27,
        93: 27,
        36: 43,
        125: 43,
        28: 43,
        164: 43,
        163: 43,
        167: 41,
        176: 41,
        13: 28
    }, WMKS.CONST.KB.VScanMap["de-CH"] = {
        32: 57,
        49: 2,
        43: 2,
        166: 2,
        50: 3,
        34: 3,
        64: 3,
        51: 4,
        42: 4,
        35: 4,
        52: 5,
        231: 5,
        53: 6,
        37: 6,
        54: 7,
        38: 7,
        172: 7,
        55: 8,
        47: 8,
        124: 8,
        56: 9,
        40: 9,
        162: 9,
        57: 10,
        41: 10,
        48: 11,
        61: 11,
        39: 12,
        63: 12,
        222: 12,
        31: 12,
        219: 12,
        180: 12,
        187: 13,
        160: 13,
        126: 13,
        94: 13,
        113: 16,
        81: 16,
        119: 17,
        87: 17,
        101: 18,
        69: 18,
        8364: 18,
        5: 18,
        234: 18,
        235: 18,
        114: 19,
        82: 19,
        116: 20,
        84: 20,
        122: 21,
        90: 21,
        117: 22,
        85: 22,
        251: 22,
        250: 22,
        249: 22,
        105: 23,
        73: 23,
        238: 23,
        239: 23,
        237: 23,
        236: 23,
        111: 24,
        79: 24,
        245: 24,
        244: 24,
        243: 24,
        242: 24,
        112: 25,
        80: 25,
        97: 30,
        65: 30,
        226: 30,
        227: 30,
        225: 30,
        115: 31,
        83: 31,
        100: 32,
        68: 32,
        102: 33,
        70: 33,
        103: 34,
        71: 34,
        104: 35,
        72: 35,
        106: 36,
        74: 36,
        107: 37,
        75: 37,
        108: 38,
        76: 38,
        60: 86,
        62: 86,
        96: 86,
        92: 86,
        121: 44,
        89: 44,
        255: 44,
        120: 45,
        88: 45,
        99: 46,
        67: 46,
        118: 47,
        86: 47,
        98: 48,
        66: 48,
        110: 49,
        78: 49,
        109: 50,
        77: 50,
        44: 51,
        59: 51,
        46: 52,
        58: 52,
        45: 53,
        95: 53,
        226: 30,
        194: 30,
        196: 40,
        193: 30,
        225: 30,
        224: 30,
        227: 30,
        228: 30,
        195: 30,
        234: 18,
        202: 18,
        201: 39,
        203: 18,
        233: 18,
        232: 18,
        200: 26,
        235: 18,
        238: 23,
        206: 23,
        205: 23,
        207: 23,
        237: 23,
        236: 23,
        204: 23,
        239: 23,
        244: 24,
        212: 24,
        211: 24,
        214: 39,
        243: 24,
        242: 24,
        210: 24,
        245: 24,
        246: 24,
        213: 24,
        251: 22,
        218: 22,
        220: 26,
        250: 22,
        249: 22,
        217: 22,
        252: 22,
        233: 39,
        232: 26,
        91: 26,
        224: 40,
        123: 40,
        1224: 40,
        246: 39,
        252: 26,
        228: 40,
        221: 27,
        161: 27,
        192: 27,
        168: 27,
        29: 27,
        33: 27,
        93: 27,
        36: 43,
        125: 43,
        28: 43,
        164: 43,
        163: 43,
        167: 41,
        176: 41,
        1167: 41,
        1176: 41,
        13: 28
    }, $.widget("wmks.nwmks", $.wmks.wmks, {
        options: {
            rescale: !0,
            position: WMKS.CONST.Position.CENTER,
            changeResolution: !0,
            audioEncodeType: null,
            useNativePixels: !1,
            useUnicodeKeyboardInput: !1,
            useVNCHandshake: !0,
            sendProperMouseWheelDeltas: !1,
            reverseScrollY: !1,
            retryConnectionInterval: -1,
            ignoredRawKeyCodes: [],
            fixANSIEquivalentKeys: !1
        },
        _create: function() {
            this.options.changeResolution && (this.options.fitGuest = !0);
            if (this.options.audioEncodeType) {
                var a = WMKS.CONST.AudioEncodeType;
                switch (this.options.audioEncodeType) {
                    case a.AAC:
                        this.options.enableAacAudioClips = !0;
                        break;
                    case a.OPUS:
                        this.options.enableOpusAudioClips = !0;
                        break;
                    case a.VORBIS:
                        this.options.enableVorbisAudioClips = !0
                }
            }
            this.element.unbind(), WMKS.widgetProto._create.apply(this)
        },
        _init: function() {
            var a = this,
                b = function(b) {
                    return typeof a._canvas[0].style[b] != "undefined" ? b : null
                };
            this.transformOrigin = b("transformOrigin") || b("WebkitTransformOrigin") || b("MozTransformOrigin") || b("msTransformOrigin") || b("OTransformOrigin")
        },
        rescaleOrResize: function(a) {
            var b = 1,
                c = 0,
                d = 0,
                e = "center center",
                f = this.element.width(),
                g = this.element.height();
            this._canvas.css({
                width: this._guestWidth / this._pixelRatio,
                height: this._guestHeight / this._pixelRatio
            });
            var h = this._canvas.width(),
                i = this._canvas.height();
            a && this.options.changeResolution && this.updateFitGuestSize(!0);
            if (this.transform !== null) {
                if (this.options.rescale) {
                    var j = f / h,
                        k = g / i;
                    b = Math.max(.1, Math.min(j, k))
                }
                if (this.options.position !== null) switch (this.options.position) {
                    case WMKS.CONST.Position.CENTER:
                        c = (f - h) / 2, d = (g - i) / 2;
                        break;
                    case WMKS.CONST.Position.LEFT_TOP:
                        e = "left top"
                }
                b !== this._scale && (this._scale = b, this._canvas.css(this.transform, "scale(" + this._scale + ")"), this._canvas.css(this.transformOrigin, e));
                if (c !== this._x || d !== this._y) this._x = c, this._y = d, this._canvas.css({
                    top: d,
                    left: c
                })
            } else WMKS.LOGGER.warn("No scaling support")
        },
        _setOption: function(a, b) {
            $.Widget.prototype._setOption.apply(this, arguments);
            switch (a) {
                case "rescale":
                case "position":
                case "changeResolution":
                    this.rescaleOrResize(!0);
                    break;
                case "useNativePixels":
                    if (b && !WMKS.UTIL.isHighResolutionSupported()) {
                        WMKS.LOGGER.warn("Browser/device does not support this feature.");
                        return
                    }
                    this._updatePixelRatio(), this.rescaleOrResize(!0);
                    break;
                case "fixANSIEquivalentKeys":
                    this._keyboardManager.fixANSIEquivalentKeys = b;
                    break;
                case "keyboardLayoutId":
                    this._keyboardManager.keyboardLayoutId = b, this._keyboardManager.UnicodeToVScanMap = WMKS.CONST.KB.VScanMap[b];
                    break;
                case "sendRelativeMouseEvent":
                    this._vncDecoder.options.sendRelativeMouseEvent = b
            }
        },
        setRemoteScreenSize: function(a, b) {
            var c = a * this._pixelRatio,
                d = b * this._pixelRatio;
            if (!this.options.changeResolution || this._guestWidth === c && this._guestHeight === d) return;
            this._vncDecoder.onRequestResolution(c, d)
        }
    }), WMKS.CoreWMKS = function(a) {
        this.wmks = a, this.wmksData = a.data("nwmks") || a.data("wmks-nwmks"), this.oldCssText = "", this.connectionState = WMKS.CONST.ConnectionState.DISCONNECTED, this.eventHandlers = {};
        var b = this.wmksData.widgetEventPrefix,
            c = this,
            d = function(b, d, e) {
                var f = c.eventHandlers[b];
                if (f && f.length > 0) {
                    var g = f.length;
                    for (var h = 0; h < g; h++) f[h].apply(a, [d, e])
                }
            },
            e = [b + "connecting", b + "connected", b + "disconnected"].join(" ");
        a.bind(e, function(a, e) {
            e = e || {};
            var f = a.type;
            c.connectionState = f.substring(b.length, f.length), e.state = c.connectionState, d(WMKS.CONST.Events.CONNECTION_STATE_CHANGE, a, e)
        });
        var f = [b + "authenticationfailed", b + "error", b + "protocolerror"].join(" ");
        a.bind(f, function(a, c) {
            var e, f = WMKS.CONST.ErrorType;
            type = a.type.substring(b.length, a.type.length), c = c || {};
            switch (type) {
                case "authenticationfailed":
                    e = f.AUTHENTICATION_FAILED;
                    break;
                case "error":
                    e = f.WEBSOCKET_ERROR;
                    break;
                case "protocolerror":
                    e = f.PROTOCOL_ERROR
            }
            e && (c.errorType = e, d(WMKS.CONST.Events.ERROR, a, c))
        }), a.bind(b + "resolutionchanged", function(a, b) {
            d(WMKS.CONST.Events.REMOTE_SCREEN_SIZE_CHANGE, a, b)
        });
        var g = [b + "keyboardledschanged", b + "heartbeat", b + "copy", b + "audio", b + "toggle"].join(" ");
        a.bind(g, function(a, c) {
            var e = a.type.substring(b.length, a.type.length);
            e == "toggle" && (c = {
                type: arguments[1],
                visibility: arguments[2]
            }), d(e, a, c)
        });
        var h = function(a) {
                if (!WMKS.UTIL.isFullscreenNow()) return;
                c.wmks[0].style.cssText = "position:fixed; margin:0px; left:0px; top:0px; height:" + window.innerHeight + "px;" + "width:" + window.innerWidth + "px;", c.wmksData.rescaleOrResize(!0), d(WMKS.CONST.Events.FULL_SCREEN_CHANGE, a, {
                    isFullScreen: !0
                })
            },
            i = function(a) {
                $(window).off("resize.wmks", h), c.wmks[0].style.cssText = c.oldCssText, c.wmksData.rescaleOrResize(!0), $(window).off("resize.wmks", i), d(WMKS.CONST.Events.FULL_SCREEN_CHANGE, a, {
                    isFullScreen: !1
                })
            };
        this.fullScreenChangeEventStr = ["fullscreenchange", "webkitfullscreenchange", "mozfullscreenchange", "MSFullscreenChange"].join(" "), this.fullScreenChangeHandler = function(a) {
            WMKS.UTIL.isFullscreenNow() || i(a)
        }, $(document).on(this.fullScreenChangeEventStr, this.fullScreenChangeHandler), WMKS.CoreWMKS.prototype.enterFullScreen = function() {
            if (WMKS.UTIL.isFullscreenNow() || !WMKS.UTIL.isFullscreenEnabled()) return;
            this.oldCssText = a[0].style.cssText, console.log("old css is " + this.oldCssText), $(window).off("resize.wmks", h), $(window).on("resize.wmks", h), WMKS.UTIL.toggleFullScreen(!0, a[0])
        }, WMKS.CoreWMKS.prototype.exitFullScreen = function() {
            if (!WMKS.UTIL.isFullscreenNow()) return;
            $(window).on("resize.wmks", i), WMKS.UTIL.toggleFullScreen(!1)
        }
    }, WMKS.CoreWMKS.prototype.register = function(a, b) {
        if (!a || !b) return;
        var c = this.eventHandlers[a];
        return c || (c = this.eventHandlers[a] = []), c.push(b), this
    }, WMKS.CoreWMKS.prototype.unregister = function(a, b) {
        if (!a && !b) return this.eventHandlers = {}, this;
        if (a && !b) return delete this.eventHandlers[a], this;
        var c = this.eventHandlers[a];
        if (c && c.length > 0) {
            var d = c.length;
            for (var e = 0; e < d; e++)
                if (c[e] === b) {
                    c.splice(e, 1);
                    break
                }
            c.length === 0 && delete this.eventHandlers[a]
        }
        return this
    }, WMKS.CoreWMKS.prototype.getVersion = function() {
        return WMKS.version
    }, WMKS.CoreWMKS.prototype.getConnectionState = function() {
        return this.connectionState
    }, WMKS.CoreWMKS.prototype.connect = function(a) {
        this.wmksData.connect(a)
    }, WMKS.CoreWMKS.prototype.disconnect = function() {
        this.wmksData.disconnect()
    }, WMKS.CoreWMKS.prototype.destroy = function() {
        this.wmksData && (clearTimeout(this.wmksData._vncDecoder.resolutionTimer), this.wmksData.destroy()), $(document).off(this.fullScreenChangeEventStr, this.fullScreenChangeHandler), $(window).off("resize.wmks"), this.wmksData = null, this.wmks = null
    }, WMKS.CoreWMKS.prototype.setRemoteScreenSize = function(a, b) {
        this.wmksData.setRemoteScreenSize(a, b)
    }, WMKS.CoreWMKS.prototype.getRemoteScreenSize = function() {
        return {
            width: this.wmksData._guestWidth,
            height: this.wmksData._guestHeight
        }
    }, WMKS.CoreWMKS.prototype.updateScreen = function() {
        this.wmksData.rescaleOrResize(!0)
    }, WMKS.CoreWMKS.prototype.canFullScreen = function() {
        return WMKS.UTIL.isFullscreenEnabled()
    }, WMKS.CoreWMKS.prototype.isFullScreen = function() {
        return WMKS.UTIL.isFullscreenNow()
    }, WMKS.CoreWMKS.prototype.sendInputString = function(a) {
        this.wmksData.sendInputString(a)
    }, WMKS.CoreWMKS.prototype.sendKeyCodes = function(a) {
        this.wmksData.sendKeyCodes(a)
    }, WMKS.CoreWMKS.prototype.sendCAD = function() {
        this.wmksData.sendKeyCodes([17, 18, 46])
    }, WMKS.CoreWMKS.prototype.showKeyboard = function() {
        this.wmksData.showKeyboard()
    }, WMKS.CoreWMKS.prototype.hideKeyboard = function() {
        this.wmksData.hideKeyboard()
    }, WMKS.CoreWMKS.prototype.toggleExtendedKeypad = function(a) {
        this.wmksData.toggleExtendedKeypad(a)
    }, WMKS.CoreWMKS.prototype.toggleTrackpad = function(a) {
        this.wmksData.toggleTrackpad(a)
    }, WMKS.CoreWMKS.prototype.toggleRelativePad = function(a) {
        this.wmksData.toggleRelativePad(a)
    }, WMKS.CoreWMKS.prototype.enableInputDevice = function(a) {
        var b = WMKS.CONST.InputDeviceType;
        innerType = null;
        switch (a) {
            case b.KEYBOARD:
                this.wmksData.options.allowMobileKeyboardInput = !0, innerType = WMKS.CONST.TOUCH.FEATURE.SoftKeyboard;
                break;
            case b.EXTENDED_KEYBOARD:
                this.wmksData.options.allowMobileExtendedKeypad = !0, innerType = WMKS.CONST.TOUCH.FEATURE.ExtendedKeypad;
                break;
            case b.TRACKPAD:
                this.wmksData.options.allowMobileTrackpad = !0, innerType = WMKS.CONST.TOUCH.FEATURE.Trackpad
        }
        innerType !== null && (this.wmksData._updateMobileFeature(!1, innerType), this.wmksData._updateMobileFeature(!0, innerType))
    }, WMKS.CoreWMKS.prototype.disableInputDevice = function(a) {
        var b = WMKS.CONST.InputDeviceType;
        switch (a) {
            case b.KEYBOARD:
                this.wmksData.options.allowMobileKeyboardInput = !1, this.wmksData._updateMobileFeature(!1, WMKS.CONST.TOUCH.FEATURE.SoftKeyboard);
                break;
            case b.EXTENDED_KEYBOARD:
                this.wmksData.options.allowMobileExtendedKeypad = !1, this.wmksData._updateMobileFeature(!1, WMKS.CONST.TOUCH.FEATURE.ExtendedKeypad);
                break;
            case b.TRACKPAD:
                this.wmksData.options.allowMobileTrackpad = !1, this.wmksData._updateMobileFeature(!1, WMKS.CONST.TOUCH.FEATURE.Trackpad)
        }
    }, WMKS.CoreWMKS.prototype.setOption = function(a, b) {
        this.wmksData._setOption(a, b)
    }, WMKS.createWMKS = function(a, b) {
        if (!a || !$("#" + a)) throw new Error("Invalid parameter");
        var c = $("#" + a).nwmks(b),
            d = new WMKS.CoreWMKS(c);
        return d
    }, WMKS.buildNumber = "4504321", WMKS.version = "2.1.0"
}();